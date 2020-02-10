package com.water.neptune.ets.common.exception;

import com.water.neptune.ets.common.enums.CodeStatusEnum;
import lombok.Data;

/**
 * @author ZhangMiaojie
 */
@Data
public class ServiceException extends Exception {
    private int code;
    private String msg;
    private String sub_code;
    private String sub_msg;

    public ServiceException() {
        this.initCause((Throwable) null);
    }

    public ServiceException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ServiceException(String errorMsg) {
        this(CodeStatusEnum.APP_CUSTOM_EXCEPTION.code, CodeStatusEnum.APP_CUSTOM_EXCEPTION.msg,
                CodeStatusEnum.APP_CUSTOM_EXCEPTION.sub_code, errorMsg);
    }

    public ServiceException(Throwable throwable) {
        super(throwable);
    }

    public ServiceException(CodeStatusEnum codeStatusEnum) {
        this(codeStatusEnum.code, codeStatusEnum.msg, codeStatusEnum.sub_msg, codeStatusEnum.sub_msg);
    }

    public ServiceException(int code, String msg, String sub_code, String sub_msg) {
        this.code = code;
        this.msg = msg;
        this.sub_code = sub_code;
        this.sub_msg = sub_msg;
    }
}
