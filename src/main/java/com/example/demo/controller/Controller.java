package com.example.demo.controller;

import com.example.demo.constant.Constant;
import com.example.demo.domain.Response;
import com.example.demo.domain.User;
import com.example.demo.domain.UserRequest;
import com.example.demo.service.AyncUserService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/demo")
public class Controller {

    UserService userService;

    AyncUserService ayncUserService;

    @Autowired
    public Controller(UserService userService, AyncUserService ayncUserService){
        this.userService = userService;
        this.ayncUserService = ayncUserService;
    }

    @PostMapping("/syncRegRN")
    public Response syncRegRN(@RequestBody UserRequest user){

        try{
            return userService.regUserRN(new User(user));
        } catch (Exception e){
            return new Response(Constant.FAIL,"FAIL");
        }

    }

    @PostMapping("/syncRegR")
    public Response syncRegR(@RequestBody UserRequest user){
        try{
            return userService.regUserR(new User(user));
        } catch (Exception e){
            return new Response(Constant.FAIL, "FAIL");
        }
    }

    @PostMapping("/syncDeregRN")
    public Response syncDeregRN(@RequestBody UserRequest user){
        try{
            return userService.deregUserRN(user.getId());
        } catch (Exception e){
            return new Response(Constant.FAIL, "FAIL");
        }
    }

    @PostMapping("/syncDeregR")
    public Response sysncDeregR(@RequestBody UserRequest user){
        try{
            return userService.deregUserR(user.getId());
        } catch (Exception e){
            return new Response(Constant.FAIL, "FAIL");
        }
    }

    @PostMapping("/asyncRegRN")
    public Response asyncRegRN(@RequestBody UserRequest user){
        try{
            return ayncUserService.asyncRegUserRN(new User(user)).get(3000, TimeUnit.MILLISECONDS);
        } catch (Exception e){
            return new Response(Constant.FAIL,"FAIL");
        }
    }

    @PostMapping("/asyncRegR")
    public Response asyncRegR(@RequestBody UserRequest user){
        try{
            return ayncUserService.asyncRegUserR(new User(user)).get(3000, TimeUnit.MILLISECONDS);
        } catch (Exception e){
            return new Response(Constant.FAIL,"FAIL");
        }
    }

    @PostMapping("/asyncDeregRN")
    public Response asyncDeregRN(@RequestBody UserRequest user){
        try{
            return ayncUserService.asyncDeregUserRN(user.getId()).get(3000, TimeUnit.MILLISECONDS);
        } catch (Exception e){
            return new Response(Constant.FAIL, "FAIL");
        }
    }

    @PostMapping("/asyncDeregR")
    public Response asyncDeregR(@RequestBody UserRequest user){
        try{
            return ayncUserService.asyncDeregUserR(user.getId()).get(3000,TimeUnit.MILLISECONDS);
        } catch (Exception e){
            return new Response(Constant.FAIL,"FAIL");
        }
    }

    @PostMapping("/regUser")
    public ResponseEntity<Response> regUser(@RequestBody UserRequest user) {
        try {
            return userService.regUser(new User(user));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(Constant.FAIL, "FAIL"));
        }
    }

    @PostMapping("/deregUser")
    public ResponseEntity<Response> deregUser(@RequestBody UserRequest user) {
        try {
            return userService.deregUser(user.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(Constant.FAIL, "FAIL"));
        }
    }
}
