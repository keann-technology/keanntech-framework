package com.keanntech.framework.admin.exception;

import com.keanntech.framework.common.web.ResultJson;
import lombok.Getter;

/**
 * @author miaoqingfu
 * @date 2022年01月26日 2:41 下午
 */
@Getter
public class CustomException extends RuntimeException{

    private ResultJson resultJson;

    public CustomException(ResultJson resultJson) {
        this.resultJson = resultJson;
    }

}
