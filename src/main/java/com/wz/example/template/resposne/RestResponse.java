package com.wz.example.template.resposne;

import lombok.Data;

@Data
public class RestResponse {

    private Integer code;

    private Object data;

    private String message;

    private String errorDesc;

    public RestResponse() {
        this.code = 200;
        this.message = "处理成功";
    }

    public RestResponse(Object data) {
        this.code = 200;
        this.message = "处理成功";
        this.data = data;
    }

    public RestResponse(Integer code, Object data, String message, String errorDesc) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.errorDesc = errorDesc;
    }

    public static RestResponse error(String errorDesc) {
        return new RestResponse(500, null, "处理异常", errorDesc);
    }

    public static RestResponse error(String message, String errorDesc) {
        return new RestResponse(500, null, message, errorDesc);
    }
}
