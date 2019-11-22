package com.thunisoft.dzjz.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.servlet.http.HttpServletResponse;


/**
 * 异常code定义
 * <p>
 * 作者: guo yao
 * 日期: 2019年11月05日
 * 时间: 下午21:13
 */
@Getter
@AllArgsConstructor
public enum ResultCodeEnum {


    SUCCESS(HttpServletResponse.SC_OK, "成功"),
    FAILURE(HttpServletResponse.SC_BAD_REQUEST, "失败"),
    PARAM_MISSED(HttpServletResponse.SC_BAD_REQUEST, "参数丢失"),
    PARAM_VALID_ERROR(HttpServletResponse.SC_BAD_REQUEST, "参数校验错误"),
    PARAM_TYPE_ERROR(HttpServletResponse.SC_BAD_REQUEST, "参数类型错误"),
    PARAM_BIND_ERROR(HttpServletResponse.SC_BAD_REQUEST, "参数绑定错误"),
    NOT_FOUND(HttpServletResponse.SC_NOT_FOUND, "404未找到"),
    MSG_NOT_READABLE(HttpServletResponse.SC_BAD_REQUEST, "无法读取信息"),
    METHOD_NOT_SUPPORTED(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "方法不支持"),
    MEDIA_TYPE_NOT_SUPPORTED(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, "媒体类型不支持"),
    INTERNAL_SERVER_ERROR(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "内部服务错误"),
    UN_AUTHORIZED(HttpServletResponse.SC_UNAUTHORIZED, "请求未授权"),

    // 自定义异常码
    PARAM_NOT_IN_RANGE(Integer.valueOf(823), "参数不在规定的范围内"),
    RESOURCE_NOT_EXIST(Integer.valueOf(810), "资源已经不存在"),
    RESOURCE_DUPLICATE(Integer.valueOf(811), "资源重复存在"),
    RESOURCE_NOT_SUPPORT(Integer.valueOf(812), "不支持该资源"),
    RESOURCE_CONSUME(Integer.valueOf(813), "资源被消耗光，或者资源已达到上线"),
    RESOURCE_EXPIRED(Integer.valueOf(814), "资源已经过期"),
    RESOURCE_INVALID(Integer.valueOf(815), "资源失效，或者未激活"),
    RESOURCE_FORBIDDEN(Integer.valueOf(816), "资源被禁用"),
    RESOURCE_NO_ACCESS(Integer.valueOf(817), "无权访问该资源"),
    RESOURCE_INCOMPLETE(Integer.valueOf(818), "资源信息不完整"),
    ERROR_THRIFT(Integer.valueOf(860), "调用第三方服务失败"),
    INVOKE_API_UNSUPPORTED(Integer.valueOf(850), "不支持此接口"),
    INVOKE_CLIENT_UNSUPPORTED(Integer.valueOf(851), "需要升级客户端");

    final int code;

    final String msg;
}
