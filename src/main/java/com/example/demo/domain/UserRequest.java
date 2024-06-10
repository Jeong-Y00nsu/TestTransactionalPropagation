package com.example.demo.domain;

import com.example.demo.constant.PropagationOption;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private String id;
    private String pw;
    private String name;

    private List<Term> agreeTerms;

    private PropagationOption propagationOption;
    private boolean isException;
    private boolean isTransactional;
}
