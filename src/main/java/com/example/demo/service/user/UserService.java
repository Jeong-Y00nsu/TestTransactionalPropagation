package com.example.demo.service.user;

import com.example.demo.domain.Response;
import com.example.demo.domain.User;
import com.example.demo.domain.UserRequest;
import com.example.demo.mapper.user.UserMapper;
import com.example.demo.service.terms.TermsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class UserService {

    final UserMapper userMapper;
    final TermsService termsService;

    @Autowired
    public UserService(UserMapper userMapper, TermsService termsService){
        this.userMapper = userMapper;
        this.termsService = termsService;
    }

    public abstract Response regUserTransactional(UserRequest userRequest, boolean isException);
    public abstract Response regUserNonTransactional(UserRequest userRequest, boolean isException);
}
