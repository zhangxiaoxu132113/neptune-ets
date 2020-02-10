package com.water.neptune.ets.common;

import com.google.gson.Gson;
import com.water.neptune.ets.common.exception.ServiceException;

import java.io.File;
import java.io.IOException;

/**
 * @author Zhang Miaojie
 * @version v1.0
 * @date 2019/7/30
 */
public class FFmpegUtils {

    public static void main(String[] args) throws InterruptedException, IOException, ServiceException {
        screenshot("/Users/zhangmiaojie/Documents/test/test.mp4",
                "/Users/zhangmiaojie/Documents/test/pic/123.jpg", 19);
    }

    /**
     * 获取视频信息详情
     *
     * @param inputFile 文件的路径
     * @return
     * @throws InterruptedException
     * @throws ServiceException
     * @throws IOException
     */
    public static VideoInfo getVideoInfo(String inputFile) throws InterruptedException, ServiceException, IOException {
        if (!checkfile(inputFile)) {
            throw new ServiceException(inputFile + " is not file");
        }
        String command = String.format(VideoConstant.Command.GET_VIDEO_INFO, inputFile);
        String s = CommandLineTools.execCommand(command);
        Gson gson = new Gson();
        return gson.fromJson(s, VideoInfo.class);
    }

    /**
     * 获取视频截图
     *
     * @param inputFile 视频文件的路径
     * @param outputFile 图片文件的输出路径
     * @param seconds 获取视频的第几秒截图
     * @return
     * @throws InterruptedException
     * @throws IOException
     * @throws ServiceException
     */
    public static String screenshot(String inputFile, String outputFile, int seconds)
            throws InterruptedException, IOException, ServiceException {
        return screenshot(inputFile, outputFile, true, 0, 0, seconds);
    }

    /**
     * 视频截图
     *
     * @param inputFile
     * @param outputFile
     * @param isOriginalSize
     * @param width
     * @param height
     * @param seconds
     * @return
     * @throws InterruptedException
     * @throws ServiceException
     * @throws IOException
     */
    public static String screenshot(String inputFile, String outputFile, boolean isOriginalSize, int width, int height,
                                    double seconds) throws InterruptedException, ServiceException, IOException {
        VideoInfo videoInfo = getVideoInfo(inputFile);
        if (isOriginalSize) {
            height = videoInfo.getStreams().get(0).getCoded_height();
            width = videoInfo.getStreams().get(0).getCoded_width();
        }
        Double duration = Double.valueOf(videoInfo.getFormat().getDuration());
        if (seconds > duration) {
            throw new ServiceException("截取的时间不能大于视频播放长度");
        }
        String command = String.format(VideoConstant.Command.SCREENSHOT, inputFile, seconds, width, height, outputFile);
        return CommandLineTools.execCommand(command);
    }

    private static boolean checkfile(String path) {
        File file = new File(path);
        return file.isFile();
    }

}
