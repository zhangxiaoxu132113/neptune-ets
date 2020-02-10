package com.water.neptune.ets.common;

/**
 * @author Zhang Miaojie
 * @version v1.0
 * @date 2019/7/31
 */
public class FileUtil {

	public static String getFileSuffixName(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".") + 1);
	}

    public static String getFileName(String fileName) {
        return fileName.substring(0, fileName.lastIndexOf("."));
    }
}
