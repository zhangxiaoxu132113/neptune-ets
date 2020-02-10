package com.water.neptune.ets.common.enums;

/**
 * @author 张淼洁
 */
public enum CodeStatusEnum {
    /**
     * 调用成功 - 10000
     */
    INVOKE_SUCCESS(1000, "调用成功", "", ""),

    /**
     * 服务不可用 - 20000
     */
    APP_UNKNOW_ERROR(2000, "服务暂不可用（业务系统不可用）", "app.unknow.error", "网络异常,请稍后重试"),
    AOP_UNKNOW_ERROR(2000, "服务暂不可用（网关自身的未知错误）", "aop.unknow.error", "网络异常,请稍后重试"),
    AOP_API_DOWNLINE(2000, "服务不可用", "aop.api.downline", "接口已下线"),


    /**
     * 授权权限不足 - 20001
     */
    AOP_INVALID_API_ACCESS(20001, "授权权限不足", "aop.invalid.api.access", "未授权当前接口"),
    AOP_INVALID_TOKEN(20001, "授权权限不足", "aop.invalid.token", "无效的应用授权令牌"),
    AOP_INVALID_APP(20001, "授权权限不足", "aop.invalid.app", "应用已停止授权"),

    /**
     * 缺少必选参数 - 40001
     */
    AOP_MISSING_METHOD(40001, "缺少必选参数", "aop.missing.method", "缺少方法名参数"),
    AOP_MISSING_SIGNATURE(40001, "缺少必选参数", "aop.missing.signature", "缺少签名参数"),
    AOP_MISSING_PUBLICKEY(40001, "缺少必选参数", "aop.missing.publickey", "未上传公钥配置"),
    AOP_MISSING_APPID(40001, "缺少必选参数", "aop.missing.appid", "缺少appId参数"),
    AOP_MISSING_TIMESTAMP(40001, "缺少必选参数", "aop.missing.timestamp", "缺少时间戳参数"),
    AOP_MISSING_VERSION(40001, "缺少必选参数", "aop.missing.version", "缺少版本参数"),
    APP_INVALID_PARAMETER(40002, "缺少必选参数", "app.invalid.parameter", "缺少请求参数"),
    APP_INVALID_PARAMETER_NOT_NULL(40002, "缺少必选参数", "app.invalid.parameter.not.null", "请求参数content不能为空"),

    /**
     * 非法的参数 - 40002
     */
    AOP_INVALID_SIGNATURE(40002, "非法的参数", "aop.invalid.signature", "无效签名"),
    AOP_INVALID_PARAMETER(40002, "非法的参数", "app.invalid.parameter", "参数无效"),
    AOP_INVALID_NOT_SUPPORT(40002, "非法的参数", "aop.invalid.not.support", "本接口不支持第三方代理调用"),
    AOP_INVALID_TIMESTAMP(40002, "非法的参数", "aop.invalid.timestamp", "无效的时间戳"),
    AOP_INVALID_APP_NOT_FOUND(40002, "非法的参数", "aop.invalid.app.not.found", "找不到应用"),

    /**
     * 自定义错误信息 - 50000
     */
    APP_CUSTOM_EXCEPTION(50000, "调用失败", "app.custom.exception", "");
    CodeStatusEnum(int code, String msg, String sub_code, String sub_msg) {
        this.code = code;
        this.msg = msg;
        this.sub_code = sub_code;
        this.sub_msg = sub_msg;
    }

    @Override
    public String toString() {
        return "CodeStatusEnum{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", sub_code='" + sub_code + '\'' +
                ", sub_msg='" + sub_msg + '\'' +
                '}';
    }

    public int code;
    public String msg;
    public String sub_code;
    public String sub_msg;

}
