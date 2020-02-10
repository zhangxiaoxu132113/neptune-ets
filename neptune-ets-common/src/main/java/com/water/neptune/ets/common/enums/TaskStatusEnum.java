package com.water.neptune.ets.common.enums;

/**
 * @author zhangmiaojie
 * @version v1.0
 * @date 2019/8/1
 */
public enum TaskStatusEnum {
	/**
	 * 未同步
	 */
	NO_RSYNCING(0, "未同步"),
	/**
	 * 同步中
	 */
	RSYNCING(1, "同步中"),
	/**
	 * 同步完成
	 */
	RSYNCING_FINISH(2, "同步完成"),
	/**
	 * 正在转码
	 */
	TRANSCODING(3, "正在转码"),
	/**
	 * 转码完成
	 */
	TRANSCODE_FINISH(4, "转码完成"),
	/**
	 * 发布中
	 */
	PUBLISHING(5, "发布中"),
	/**
	 * 发布完成
	 */
	PUBLISH_FINISH(6, "发布完成"),
	/**
	 * 任务挂起
	 */
	HANG(7, "任务挂起"),
	/**
	 * 同步失败
	 */
	RSYNCING_ERROR(-1, "同步失败"),
	/**
	 * 转码失败
	 */
	TRANSCODE_ERROR(-2, "转码失败"),
	/**
	 * 发布失败
	 */
	PUBLISH_ERROR(-3, "发布失败");

	TaskStatusEnum(int value, String display) {
		this.value = value;
		this.display = display;
	}

	public int value;
	public String display;

	public static String getDisplay(int value) {
		for (TaskStatusEnum taskStatusEnum : TaskStatusEnum.values()) {
			if (taskStatusEnum.value == value) {
				return taskStatusEnum.display;
			}
		}
		return null;
	}
}
