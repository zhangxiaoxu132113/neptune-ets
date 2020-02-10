package com.water.neptune.ets.common;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/**
 * @author Zhang Miaojie
 * @version v1.0
 * @date 2019/7/31
 */
public class NetUtil {
    public static String getRemoteIP(HttpServletRequest request) {
        String UNKNOWN = "unknown";
        String ip = request.getHeader("x-forwarded-for");
        if (StringUtils.isNotBlank(ip) && !ip.startsWith("unknown")) {
            ip = ip.split(",")[0];
        } else {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static String getHostIP() {

        Enumeration<NetworkInterface> allNetInterfaces = null;
        String resultIP = null;
        try {
            allNetInterfaces = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        InetAddress ip = null;
        while (allNetInterfaces.hasMoreElements()) {
            NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
            Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
            while (addresses.hasMoreElements()) {
                ip = (InetAddress) addresses.nextElement();
                if (ip != null && ip instanceof Inet4Address) {
                    if (resultIP == null) {
                        resultIP = ip.getHostAddress();
                    }

                }
            }
        }
        return resultIP;
    }
}
