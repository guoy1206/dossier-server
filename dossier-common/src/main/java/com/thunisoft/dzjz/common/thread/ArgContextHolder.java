package com.thunisoft.dzjz.common.thread;

import lombok.NoArgsConstructor;

/**
 * 参数上下文
 * <p>
 * 作者: guo yao
 * 日期: 2019年11月18日
 * 时间: 下午15:33
 */
@NoArgsConstructor
public class ArgContextHolder {
    private static ThreadLocal<Object> localParams = ThreadLocal.withInitial(Object::new);


    public static void setParams(Object params) {
        localParams.set(params);
    }

    public static Object getParams() {
        return localParams.get();
    }

    public static void removeParams() {
        localParams.remove();
    }
}
