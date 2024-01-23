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

APDEV=wlp2s0
INFDEV=wlp0s20f0u2

usage()
{
  echo "Usage: rogue-ap <ssid> <channel>"
  exit 1
}

check_required()
{
        which hostapd &> /dev/null
        if [ $? -ne 0 ]; then
                echo "Error: hostapd not installed."
                exit 1
        fi

        which dnsmasq &> /dev/null
        if [ $? -ne 0 ]; then
                echo "Error: dnsmasq not installed."
                exit 1
        fi
}

cleanup()
{
  echo "*** Cleaning up ***"
  echo

  # Stop the AP.
  pkill hostapd &> /dev/null
  echo "hostapd stopped"

  # Stop the DHCP & DNS
  pkill dnsmasq &> /dev/null
  echo "dnmasq stopped"

  # restore the firewall configuration by reloading it and restarting the
  # daemon.
  firewall-cmd --reload &> /dev/null
  systemctl restart firewalld &> /dev/null
  rm -f /etc/sysctl.d/a-sysctl.conf
  echo "Firewall configuration restored."

  systemctl start NetworkManager  # Re-enable network manager.
  echo "Network manager restarted"

  rm -r dnsmasq.conf hostapd.conf &> /dev/null
  echo "Cleaned up configuration files; preserving log.txt."
}

create_hostapd_conf()
{
  # Remove the file
  rm hostapd.conf &> /dev/null

  # create the contents
  echo "interface=${APDEV}" >> hostapd.conf
  echo "ssid=$1" >> hostapd.conf
  echo "channel=$2" >> hostapd.conf
  echo "hw_mode=g" >> hostapd.conf
  echo "macaddr_acl=0" >> hostapd.conf
  echo "ignore_broadcast_ssid=0" >> hostapd.conf
}

create_dnsmasq_conf()
{
  # remove the old file.
  rm dnsmasq.conf &> /dev/null

  # write the contents of the configuration file.

  # Set the interface.
  echo "interface=${APDEV}" >> dnsmasq.conf

  # Set up the DHCP address pool.
  # The range of addresses is 192.168.1.2 -- 192.168.1.30
  # The lease time on the address is 12 hours.
  echo "dhcp-range=10.0.0.2,10.0.0.100,12h" >> dnsmasq.conf

  # Set the netmask to 255.255.255.0
  echo "dhcp-option=1,255.255.255.0" >> dnsmasq.conf

  # Set the gateway IP address (option 3)
  echo "dhcp-option=3,10.0.0.1" >> dnsmasq.conf

  # Set the DHCP default DNS (option 6) to this machine.
  # dnsmasq can also perform necessary DNS lookups. Note: this
  # allows us to serve as a man-in-the-middle controlling how
  # DNS lookups are processed.
  echo "dhcp-option=6,10.0.0.1" >> dnsmasq.conf

  # Fix the address of the router to the the machine NIC in question.
  MADDR=`cat /sys/class/net/${APDEV}/address`
  echo "dhcp-host=${MADDDR},iptime,10.0.0.1,infinite" >> dnsmasq.conf

  # Set the back up DNS server, if we can't answer the question ourself.
  # In this case, we are going to rely on the Google DNS server (8.8.8.8).
  echo "server=8.8.8.8" >> dnsmasq.conf

  # Don't serve up the hosts file on this machine.
  echo "no-hosts" >> dnsmasq.conf

  # Override any hosts you want by populating the myhosts file.
  # The format is IP address tab hostname. For example,
  # 10.0.0.1  google.com
  echo "addn-hosts=myhosts" >> dnsmasq.conf

  # Log the DNS and DHCP queries to the screen.
  echo "log-dhcp" >> dnsmasq.conf
  echo "log-queries" >> dnsmasq.conf

  # The address that we should listen on.
  echo "listen-address=127.0.0.1" >> dnsmasq.conf

}

configure_firewall()
{
  echo "Configuring firewall and forwarding traffic from ${APDEV} to ${INFDEV}."

  # Move both devices to the trusted domain.
  firewall-cmd --zone=trusted --change-interface=${APDEV} &> /dev/null
  firewall-cmd --zone=trusted --change-interface=${INFDEV} &> /dev/null

  # Turn on ip masquerading in the defualt zone.
  #
  firewall-cmd --zone=trusted --add-masquerade &> /dev/null
  echo "net.ipv4.ip_forward = 1" > /etc/sysctl.d/a-sysctl.conf
  sysctl -p

  #
  # Turn on port forwarding in the default zone and move packets coming in on the
  # AP interface and redirecting them to the peer device for processing. This
  # allows our AP to serve up an internet connection.
  #
  firewall-cmd --direct --add-rule ipv4 nat POSTROUTING 0 -o ${INFDEV} -j MASQUERADE &> /dev/null
  firewall-cmd --direct --add-rule ipv4 filter FORWARD 0 -i ${APDEV} -o ${INFDEV} -j ACCEPT &> /dev/null
  firewall-cmd --direct --add-rule ipv4 filter FORWARD 0 -i ${INFDEV} -o ${APDEV} -m state --state RELATED,ESTABLISHED -j ACCEPT &> /dev/null
}

configure_card()
{
  echo "Configuring ${APDEV} interface."

  # Down the interface
  ip link set down ${APDEV} &> /dev/null

  # Remove all lingering addresses
  ip addr flush dev ${APDEV} &> /dev/null

  # Set this to be the gateway address.
  ip addr add 10.0.0.1/24 dev ${APDEV} &> /dev/null

  # Turn the interface on.
  ip link set up ${APDEV} &> /dev/null

  # Set up the new route.
  ip route add 10.0.0.0/24 via 10.0.0.1 &> /dev/null
}

ctrl_c()
{
  cleanup 0
}

# Print the usage message if we don't have the correct number of
# arguments.
if [ $# -ne 2 ]; then
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

# Install a new trap handler. This signal handler must be installed no earlier
# otherwise we can not guarantee that the ip_forwarding has been affected.
trap ctrl_c INT

# Stop the network manager service
systemctl stop NetworkManager &> /dev/null

# Configure card
configure_card

# build hostapd.conf file
create_hostapd_conf $1 $2

# Start up hostapd
hostapd -B hostapd.conf

# Setup the firewall to serve the connections.
configure_firewall

# Run dnsmasq
create_dnsmasq_conf

echo "Sending all dnsmasq output to log.txt use tail -f log.txt to follow."
dnsmasq -C dnsmasq.conf -d &> log.txt &

echo "Please enter a letter to stop."
read key

cleanup
