package com.example.demo.domain;

import lombok.Data;

import java.io.Serializable;
import java.sql.Date;

@Data
public class User implements Serializable {

    private String id;
    private String pw;

    private String name;

    private Date regDt;
    private Date deregDt;

    public User(){}
    public User(String id, String pw, String name, Date regDt, Date deregDt) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.regDt = regDt;
        this.deregDt = deregDt;
    }

    public User(UserRequest userRequest){
        this.id = userRequest.getId();
        this.pw = userRequest.getPw();
        this.name = userRequest.getName();
    }
}
