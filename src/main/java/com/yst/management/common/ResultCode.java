package com.yst.management.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCode {

    // 成功提示码
    SUCCESS(200,"成功"),

    // 失败提示码
    FAILURE(400,"失败");

    // 状态码
    private final Integer code;
    // 提示信息
    private final String msg;
}