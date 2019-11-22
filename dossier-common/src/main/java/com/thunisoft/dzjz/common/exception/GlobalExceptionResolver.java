package com.thunisoft.dzjz.common.exception;

import com.thunisoft.dzjz.domain.BaseResponse;
import com.thunisoft.dzjz.enums.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * 全局rest异常处理类
 * <p>
 * 作者: guo yao
 * 日期: 2019年11月05日
 * 时间: 下午20:59
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionResolver {

    // 缺少必要参数异常
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public BaseResponse handleError(MissingServletRequestParameterException e) {
        log.warn("Missing Request Parameter", e);
        String message = String.format("缺少必要参数: %s", e.getParameterName());
        return BaseResponse
                .builder()
                .status(ResultCodeEnum.PARAM_MISSED.getCode())
                .code(ResultCodeEnum.PARAM_MISSED)
                .message(message)
                .build();
    }

    // 方法参数类型不匹配
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public BaseResponse handleError(MethodArgumentTypeMismatchException e) {
        log.warn("Method Argument Type Mismatch", e);
        String message = String.format("方法参数类型不匹配: %s", e.getName());
        return BaseResponse
                .builder()
                .status(ResultCodeEnum.PARAM_TYPE_ERROR.getCode())
                .code(ResultCodeEnum.PARAM_TYPE_ERROR)
                .message(message)
                .build();
    }

    // 方法参数无效
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse handleError(MethodArgumentNotValidException e) {
        log.warn("Method Argument Not Valid", e);
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        String message = String.format("%s:%s", error.getField(), error.getDefaultMessage());
        return BaseResponse
                .builder()
                .code(ResultCodeEnum.PARAM_VALID_ERROR)
                .message(message)
                .build();
    }

    // 绑定错误
    @ExceptionHandler(BindException.class)
    public BaseResponse handleError(BindException e) {
        log.warn("Bind Exception", e);
        FieldError error = e.getFieldError();
        String message = String.format("%s:%s", error.getField(), error.getDefaultMessage());
        return BaseResponse
                .builder()
                .status(ResultCodeEnum.PARAM_BIND_ERROR.getCode())
                .code(ResultCodeEnum.PARAM_BIND_ERROR)
                .message(message)
                .build();
    }

    // 违反约束
    @ExceptionHandler(ConstraintViolationException.class)
    public BaseResponse handleError(ConstraintViolationException e) {
        log.warn("Constraint Violation", e);
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        ConstraintViolation<?> violation = violations.iterator().next();
        String path = ((PathImpl) violation.getPropertyPath()).getLeafNode().getName();
        String message = String.format("%s:%s", path, violation.getMessage());
        return BaseResponse
                .builder()
                .status(ResultCodeEnum.PARAM_VALID_ERROR.getCode())
                .code(ResultCodeEnum.PARAM_VALID_ERROR)
                .message(message)
                .build();
    }

    // 404 错误
    @ExceptionHandler(NoHandlerFoundException.class)
    public BaseResponse handleError(NoHandlerFoundException e) {
        log.error("404 Not Found", e);
        return BaseResponse
                .builder()
                .status(ResultCodeEnum.NOT_FOUND.getCode())
                .code(ResultCodeEnum.NOT_FOUND)
                .message(e.getMessage())
                .build();
    }

    // 信息不可读的
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public BaseResponse handleError(HttpMessageNotReadableException e) {
        log.error("Message Not Readable", e);
        return BaseResponse
                .builder()
                .status(ResultCodeEnum.MSG_NOT_READABLE.getCode())
                .code(ResultCodeEnum.MSG_NOT_READABLE)
                .message(e.getMessage())
                .build();
    }

    // 不支持请求方法
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public BaseResponse handleError(HttpRequestMethodNotSupportedException e) {
        log.error("Request Method Not Supported", e);
        return BaseResponse
                .builder()
                .status(ResultCodeEnum.METHOD_NOT_SUPPORTED.getCode())
                .code(ResultCodeEnum.METHOD_NOT_SUPPORTED)
                .message(e.getMessage())
                .build();
    }

    // 不支持媒体类型
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public BaseResponse handleError(HttpMediaTypeNotSupportedException e) {
        log.error("Media Type Not Supported", e);
        return BaseResponse
                .builder()
                .status(ResultCodeEnum.MEDIA_TYPE_NOT_SUPPORTED.getCode())
                .code(ResultCodeEnum.MEDIA_TYPE_NOT_SUPPORTED)
                .message(e.getMessage())
                .build();
    }

    // 服务异常
    @ExceptionHandler(ServiceException.class)
    public BaseResponse handleError(ServiceException e) {
        log.error("Service Exception", e);
        return BaseResponse
                .builder()
                .status(e.getResultCode().getCode())
                .code(e.getResultCode())
                .message(e.getMessage())
                .build();

    }

    // 请求未授权
    @ExceptionHandler(PermissionDeniedException.class)
    public BaseResponse handleError(PermissionDeniedException e) {
        log.error("Permission Denied", e);
        return BaseResponse
                .builder()
                .status(e.getResultCode().getCode())
                .code(e.getResultCode())
                .message(e.getMessage())
                .build();
    }

    // 内部服务错误
    @ExceptionHandler(Throwable.class)
    public BaseResponse handleError(Throwable e) {
        log.error("Internal Server Error", e);
        return BaseResponse
                .builder()
                .status(ResultCodeEnum.INTERNAL_SERVER_ERROR.getCode())
                .code(ResultCodeEnum.INTERNAL_SERVER_ERROR)
                .message(e.getMessage())
                .build();
    }
}
