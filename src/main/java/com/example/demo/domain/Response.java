package com.example.demo.domain;

import com.example.demo.constant.Constant;
import lombok.Data;

@Data
public class Response {

    private String result;
    private String msg;

    public Response(){}
    public Response(String result, String msg){
        this.result = result;
        this.msg = msg;
    }
}
