
package com.mechat.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Pattern;

public class HostWithPortValidator {

    private static final String IPV4_PATTERN = "^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
    private static final String IPV6_PATTERN = "^(?:[0-9a-fA-F]{1,4}:){7}[0-9a-fA-F]{1,4}$|^::1$|^([0-9a-fA-F]{1,4}:){1,7}:$";

    private static final Pattern IPV4_REGEX = Pattern.compile(IPV4_PATTERN);
    private static final Pattern IPV6_REGEX = Pattern.compile(IPV6_PATTERN);

    public static boolean isValidHostWithPort(String input) {
        if (input == null || !input.contains(":")) {
            return false;
        }

        int lastColonIndex = input.lastIndexOf(":");
        String host = input.substring(0, lastColonIndex);
        String portStr = input.substring(lastColonIndex + 1);

        // Validate port
        try {
            int port = Integer.parseInt(portStr);
            if (port < 0 || port > 65535) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }

        // If the host is in square brackets (IPv6), remove the brackets
        if (host.startsWith("[") && host.endsWith("]")) {
            host = host.substring(1, host.length() - 1);
        }

        // Validate IPv4 address
        if (IPV4_REGEX.matcher(host).matches()) {
            return true;
        }

        // Validate IPv6 address
        if (IPV6_REGEX.matcher(host).matches()) {
            return true;
        }

        // Validate domain name or localhost
        if (host.equalsIgnoreCase("localhost")) {
            return true;
        }

        // Reject invalid hostnames like "1" or "1.1"
        if (host.matches("^\\d+(\\.\\d+)*$")) {
            return false;
        }

        // Validate domain name via DNS resolution
        try {
            InetAddress.getByName(host);
            return true;
        } catch (UnknownHostException e) {
            return false;
        }
    }
}
