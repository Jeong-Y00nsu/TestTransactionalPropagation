package com.example.demo.controller;

import com.example.demo.constant.Constant;
import com.example.demo.constant.PropagationOption;
import com.example.demo.domain.Response;
import com.example.demo.domain.UserRequest;
import com.example.demo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/propagation")
public class PropagationUserController {

    UserService userServiceRequired;
    UserService userServiceRequiresNew;
    UserService userServiceNested;
    UserService userServiceMandatory;
    UserService userServiceSupported;
    UserService userServiceNotSupported;
    UserService userServiceNever;

    @Autowired
    public PropagationUserController(@Qualifier("userServiceRequired")UserService userServiceRequired, @Qualifier("userServiceRequiresNew")UserService userServiceRequiresNew,
                                     @Qualifier("userServiceNested")UserService userServiceNested, @Qualifier("userServiceMandatory")UserService userServiceMandatory,
                                     @Qualifier("userServiceSupported")UserService userServiceSupported, @Qualifier("userServiceNotSupported")UserService userServiceNotSupported,
                                     @Qualifier("userServiceNever")UserService userServiceNever){
        this.userServiceRequired = userServiceRequired;
        this.userServiceRequiresNew = userServiceRequiresNew;
        this.userServiceNested = userServiceNested;
        this.userServiceMandatory = userServiceMandatory;
        this.userServiceSupported = userServiceSupported;
        this.userServiceNotSupported = userServiceNotSupported;
        this.userServiceNever = userServiceNever;
    }

    @PostMapping("/regUser")
    public Response regUser(@RequestBody UserRequest user){
        try{
            UserService userService = switchService(user.getPropagationOption());
            if(userService==null) throw new RuntimeException("잘못된 전파 속성");
            if(user.isTransactional()) return userService.regUserTransactional(user, user.isException());
            else return userService.regUserNonTransactional(user, user.isException());
        }catch (Exception e){
            return new Response(Constant.FAIL,"실패");
        }
    }

    private UserService switchService(PropagationOption option){
        switch (option){
            case REQUIRED:
                return this.userServiceRequired;
            case REQUIRES_NEW:
                return this.userServiceRequiresNew;
            case NESTED:
                return this.userServiceNested;
            case MANDATORY:
                return this.userServiceMandatory;
            case SUPPORTS:
                return this.userServiceSupported;
            case NOT_SUPPORTED:
                return this.userServiceNotSupported;
            case NEVER:
                return this.userServiceNever;
            default:
                return null;
        }
    }
}
