package com.water.neptune.ets.common;

/**
 * @author Zhang Miaojie
 * @version v1.0
 * @date 2019/7/30
 */
public class VideoConstant {

    public static final String TEMPORARY_CATALOGUE = "F:\\resources\\test\\output";

    public static class Command {
        /**
         * 截图【1，输入的视频文件 2，截图图片的秒数 3，图片宽度 4，图片高度 5，输出的图片】
         */
        public static String SCREENSHOT = "ffmpeg -i %s -y -f image2 -ss %s -t 0.001 -s %s*%s %s";

        /**
         * 获取视频信息【1，输入的视频文件】
         */
        public static String GET_VIDEO_INFO = "ffprobe -v quiet -print_format json -show_format -show_streams %s";
    }
}
