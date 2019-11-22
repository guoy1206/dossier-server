package com.thunisoft.dzjz.common.exception;

import com.thunisoft.dzjz.enums.ResultCodeEnum;
import lombok.Getter;

/**
 * 服务自定义异常
 * <p>
 * 作者: guo yao
 * 日期: 2019年11月05日
 * 时间: 下午21:08
 */
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 2359767895161832954L;

    @Getter
    private final ResultCodeEnum resultCode;

    public ServiceException(String message) {
        super(message);
        this.resultCode = ResultCodeEnum.FAILURE;
    }

    public ServiceException(ResultCodeEnum resultCode) {
        super(resultCode.getMsg());
        this.resultCode = resultCode;
    }

    public ServiceException(ResultCodeEnum resultCode, String msg) {
        super(msg);
        this.resultCode = resultCode;
    }

    public ServiceException(ResultCodeEnum resultCode, Throwable cause) {
        super(cause);
        this.resultCode = resultCode;
    }

    public ServiceException(String msg, Throwable cause) {
        super(msg, cause);
        this.resultCode = ResultCodeEnum.FAILURE;
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

}
