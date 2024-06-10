package com.example.demo.service.user;

import com.example.demo.constant.Constant;
import com.example.demo.domain.Response;
import com.example.demo.domain.User;
import com.example.demo.domain.UserRequest;
import com.example.demo.mapper.user.UserMapper;
import com.example.demo.service.terms.TermsService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Qualifier("userServiceMandatory")
public class UserServiceMandatory extends UserService{
    public UserServiceMandatory(UserMapper userMapper, @Qualifier("termsServiceMandatory")TermsService termsService) {
        super(userMapper, termsService);
    }

    @Override
    @Transactional
    public Response regUserTransactional(UserRequest userRequest, boolean isException) {
        userMapper.insertRegUser(new User(userRequest));
        termsService.agreeTerms(userRequest.getAgreeTerms(),!isException);
        if(isException) throw new RuntimeException();
        return new Response(Constant.OK,"성공");
    }

    @Override
    public Response regUserNonTransactional(UserRequest userRequest, boolean isException) {
        userMapper.insertRegUser(new User(userRequest));
        termsService.agreeTerms(userRequest.getAgreeTerms(),!isException);
        if(isException) throw new RuntimeException();
        return new Response(Constant.OK,"성공");
    }
}
