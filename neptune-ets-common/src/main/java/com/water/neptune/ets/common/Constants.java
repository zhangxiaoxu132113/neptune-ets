package com.water.neptune.ets.common;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * @author Zhang Miaojie
 * @version v1.0
 * @date 2019/8/5
 */
public class Constants {
	public static Properties props;

	public static String UPLOAD_TEMPORARY_DIRECTORY;
	public static String ZOOKEEPER_SERVERS;
	public static String FFMPEG_PATH;
	public static String FFPROBE_PATH;
	public static String OUTPUT_FILE_PATH;
	public static String CAN_PLAY_FILE_PATH;
	public static String BACK_UP;

	static {
		Resource resource = new ClassPathResource("/config.properties");
		try {
			props = PropertiesLoaderUtils.loadProperties(resource);
			ZOOKEEPER_SERVERS = props.getProperty("zookeeper.server");
			UPLOAD_TEMPORARY_DIRECTORY = props.getProperty("upload.temporary.directory");
			FFMPEG_PATH = props.getProperty("ffmpeg.path");
			FFPROBE_PATH = props.getProperty("ffprobe.path");
			OUTPUT_FILE_PATH = props.getProperty("output.file.path");
			CAN_PLAY_FILE_PATH = props.getProperty("can.play.file.path");
			BACK_UP = props.getProperty("backup.path");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static class Command {
		public static String rsyncFromUploadMachine(String uploadMachineIp, String videoName) {
			return String.format(
					"rsync -artuz --port=873 -R rsync@%s::test1/%s  /backup/ --password-file=/etc/rsync_video_upload.password",
					uploadMachineIp, videoName);
		}
	}
}
