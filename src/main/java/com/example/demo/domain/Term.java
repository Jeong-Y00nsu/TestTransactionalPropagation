package com.example.demo.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Term {

    private String userId;
    private String serviceType;
    private String termsCode;

    private Date agreedDt;
    private Date withdrawDt;

    public Term(){}

    public Term(String userid, String serviceType, String termsCode, Date agreedDt, Date withdrawDt){
        this.userId = userId;
        this.serviceType = serviceType;
        this.termsCode = termsCode;
        this.agreedDt = agreedDt;
        this.withdrawDt = withdrawDt;
    }

}
