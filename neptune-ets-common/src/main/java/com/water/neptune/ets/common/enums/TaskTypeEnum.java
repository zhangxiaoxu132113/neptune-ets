package com.water.neptune.ets.common.enums;

/**
 * @author zhangmiaojie
 * @version v1.0
 * @date 2019/8/1
 */
public enum TaskTypeEnum {

	/**
	 * 总任务
	 */
	GENERAL_TASK(0, "总任务"),
	/**
	 * 全部转码成功
	 */
	SUB_TASK(1, "子任务");

	TaskTypeEnum(int value, String display) {
		this.value = value;
		this.display = display;
	}

	public int value;
	public String display;
}
