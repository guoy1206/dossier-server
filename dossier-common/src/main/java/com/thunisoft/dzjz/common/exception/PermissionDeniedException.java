package com.thunisoft.dzjz.common.exception;

import com.thunisoft.dzjz.enums.ResultCodeEnum;
import lombok.Getter;

/**
 * 权限异常
 *
 * 作者: guo yao
 * 日期: 2019年11月06日
 * 时间: 下午9:28
 */
public class PermissionDeniedException extends RuntimeException{

    @Getter
    private final ResultCodeEnum resultCode;

    public PermissionDeniedException(String message) {
        super(message);
        this.resultCode = ResultCodeEnum.UN_AUTHORIZED;
    }

    public PermissionDeniedException(ResultCodeEnum resultCode) {
        super(resultCode.getMsg());
        this.resultCode = resultCode;
    }

    public PermissionDeniedException(ResultCodeEnum resultCode, Throwable cause) {
        super(cause);
        this.resultCode = resultCode;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
