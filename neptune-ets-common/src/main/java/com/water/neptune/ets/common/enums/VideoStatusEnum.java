package com.water.neptune.ets.common.enums;

/**
 * @author zhangmiaojie
 * @version v1.0
 * @date 2019/7/31
 */
public enum VideoStatusEnum {
	/**
	 * 开始上传
	 */
	PLOAD_START(0, "开始上传"),
	/**
	 * 上传完成
	 */
	UPLOAD_FINISH(1, "上传完成"),
	/**
	 * 已经添加转码任务
	 */
	ADD_TRANSCODE(2, "已经添加转码任务"),
	/**
	 * 全部转码成功
	 */
	COMPLETE_FINISH(3, "全部转码成功");

	VideoStatusEnum(int value, String display) {
		this.value = value;
		this.display = display;
	}

	public int value;
	public String display;

	public static String getDisplay(int value) {
		for (VideoStatusEnum videoStatusEnum : VideoStatusEnum.values()) {
			if (videoStatusEnum.value == value) {
				return videoStatusEnum.display;
			}
		}
		return null;
	}
}
