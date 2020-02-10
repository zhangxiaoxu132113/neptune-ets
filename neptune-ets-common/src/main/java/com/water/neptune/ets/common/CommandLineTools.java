package com.water.neptune.ets.common;

import com.water.neptune.ets.common.exception.ServiceException;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


/**
 * @author zhangmiaojie
 */
public class CommandLineTools {
    private static String OS = System.getProperty("os.name").toLowerCase().startsWith("windows") ? "windows"
            : "linux";

    public static String getOS() {
        return OS;
    }

    /**
     * 在单独的进程中执行指定的字符串命令
     *
     * @param cmd 字符串命令
     * @return
     */
    public static String execCommand(String cmd) throws IOException,
            InterruptedException, ServiceException {
        return execCommand(cmd, "");
    }

    public static String execCommand(String cmd, String pathname) throws IOException, InterruptedException, ServiceException {
        return execCommand(cmd, pathname, new ArrayList<String>());
    }

    public static String execCommand(String cmd, String pathname, List<String> filterList) throws IOException, InterruptedException, ServiceException {
        String[] cmds;
        if (OS.equals("windows")) {
            cmds = new String[]{"cmd.exe", "/c", cmd};
        } else {
            cmds = new String[]{"/bin/sh", "-c", cmd};
        }
        return execCommand(cmds, pathname, filterList);
    }

    /**
     * 在单独的进程中执行指定命令集合。
     *
     * @param cmds 指定的命令 加上参数
     * @return
     */
    private static String execCommand(String[] cmds, String pathname, List<String> filterList)
            throws IOException, InterruptedException, ServiceException {
        Process process = null;
        int result = 0;
        StringBuilder resultBuilder = new StringBuilder("");
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(cmds);
            processBuilder.redirectErrorStream(true);
            if (!StringUtils.isEmpty(pathname)) {
                processBuilder.directory(new File(pathname));
            }

            process = processBuilder.start();
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
            String output = null;
            while (null != (output = br.readLine())) {
                resultBuilder.append(output + "\n");
            }
            throwOnError(process);
        } catch (Exception e) {
            throw new ServiceException("Command: " + cmds[2] + ";\n Output: " + resultBuilder.toString() + ";\n Exception: " + e.getMessage());
        }
        return resultBuilder.toString();
    }

    public static void throwOnError(Process p) throws IOException {
        try {
            int exitValue = ProcessUtil.waitForWithTimeout(p, 1, TimeUnit.SECONDS);
            // TODO In java 8 use waitFor(long timeout, TimeUnit unit)
            if (exitValue != 0) {
                throw new IOException("command returned non-zero <" + exitValue + "> exit status. Check stdout.");
            }
        } catch (TimeoutException e) {
            throw new IOException("Timed out waiting for command to finish.");
        }
    }
}
