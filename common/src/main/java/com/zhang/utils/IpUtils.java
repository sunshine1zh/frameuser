package com.zhang.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.regex.Pattern;

/*
 * .
 *
 * @author: <a href="mailto:zhangtopsun@foxmail.com">zhanghq</a>
 * @date: 2022/11/15 15:10
 * @version: 1.0
 */
public class IpUtils {

  /*
   * Regular expression of the IP address.
   * IP地址的正则表达式.
   */
  public static final String IP_REGEX =
      "((\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3})";

  public static final Pattern IPV4 =
      Pattern.compile(
          "\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)"
              + "\\d+|1\\d\\d|2[0-4]\\d"
              + "|25[0-5])\\.("
              + "(?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b");

  private static volatile String cachedIpAddress;

  /*
   * Obtain the local IP address.
   * 获取本机IP地址.
   *
   * Limited access to external IP addresses.
   * It may also be linked to the router's final IP address.
   * 有限获取外网IP地址.
   * 也有可能是链接着路由器的最终IP地址.
   * @return
   */
  public static String getIp() {
    if (null != cachedIpAddress) {
      return cachedIpAddress;
    }
    Enumeration<NetworkInterface> netInterfaces;
    try {
      netInterfaces = NetworkInterface.getNetworkInterfaces();
    } catch (final SocketException ex) {
      throw new RuntimeException(ex);
    }
    String localIpAddress = null;
    while (netInterfaces.hasMoreElements()) {
      NetworkInterface netInterface = netInterfaces.nextElement();
      Enumeration<InetAddress> ipAddresses = netInterface.getInetAddresses();
      while (ipAddresses.hasMoreElements()) {
        InetAddress ipAddress = ipAddresses.nextElement();
        if (isPublicIpAddress(ipAddress)) {
          String publicIpAddress = ipAddress.getHostAddress();
          cachedIpAddress = publicIpAddress;
          return publicIpAddress;
        }
        if (isLocalIpAddress(ipAddress)) {
          localIpAddress = ipAddress.getHostAddress();
        }
      }
    }
    cachedIpAddress = localIpAddress;
    return localIpAddress;
  }

  private static boolean isPublicIpAddress(final InetAddress ipAddress) {
    return !ipAddress.isSiteLocalAddress()
        && !ipAddress.isLoopbackAddress()
        && !isV6IpAddress(ipAddress);
  }

  private static boolean isLocalIpAddress(final InetAddress ipAddress) {
    return ipAddress.isSiteLocalAddress()
        && !ipAddress.isLoopbackAddress()
        && !isV6IpAddress(ipAddress);
  }

  private static boolean isV6IpAddress(final InetAddress ipAddress) {
    return ipAddress.getHostAddress().contains(":");
  }

  /*
   * Gets the name of the local Host.
   * 获取本机Host名称.
   *
   * @return
   */
  public static String getHostName() {
    try {
      return InetAddress.getLocalHost().getHostName();
    } catch (final UnknownHostException ex) {
      throw new RuntimeException(ex);
    }
  }

  /*
   * ip last two digit.
   *
   * @param ip ip
   * @return ip last two digit
   */
  public static long lastTwoIpToLong(final String ip) {
    if (IPV4.matcher(ip).matches()) {
      int lastIndex = ip.lastIndexOf(".");
      String lastIp = ip.substring(lastIndex + 1);
      int lastTwoIndex = ip.lastIndexOf(".", lastIndex - 1);
      String lastTwoIp = ip.substring(lastTwoIndex + 1, lastIndex);
      long lastTwoIpNumber = Long.parseLong(lastTwoIp);
      long lastIpNumber = Long.parseLong(lastIp);
      return lastTwoIpNumber << 8 | lastIpNumber;
    } else {
      return 0L;
    }
  }
}
