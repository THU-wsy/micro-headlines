package com.thuwsy.headlines.utils;

/**
 * ClassName: ResultCodeEnum
 * Package: com.thuwsy.headlines.utils
 * Description:
 *
 * @Author THU_wsy
 * @Create 2023/9/25 13:04
 * @Version 1.0
 */
/**
 * 统一返回结果状态信息类
 *
 */
public enum ResultCodeEnum {

    SUCCESS(200,"success"),
    USERNAME_ERROR(501,"usernameError"),
    PASSWORD_ERROR(503,"passwordError"),
    NOTLOGIN(504,"notLogin"),
    USERNAME_USED(505,"userNameUsed");

    private Integer code;
    private String message;
    private ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    public Integer getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
}

