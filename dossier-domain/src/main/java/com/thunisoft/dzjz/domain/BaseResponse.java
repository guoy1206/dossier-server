package com.thunisoft.dzjz.domain;

import com.thunisoft.dzjz.enums.ResultCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 作者: guo yao
 * 日期: 2019年11月05日
 * 时间: 下午21:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseResponse {

    private String message;
    private Integer status;
    @Builder.Default
    private ResultCodeEnum code = ResultCodeEnum.SUCCESS;

    public boolean isSuccess() {
        return code == ResultCodeEnum.SUCCESS;
    }
}
