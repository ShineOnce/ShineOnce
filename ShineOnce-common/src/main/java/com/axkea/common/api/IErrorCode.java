package com.axkea.common.api;

/**
 * @Author Axkea
 * @Date 2023/10/25/025 17:43
 * @Description 封装API的错误码
 */
public interface IErrorCode {
    long getCode();

    String getMessage();
}
