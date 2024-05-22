package com.example.demo.service;

import com.example.demo.domain.Response;
import com.example.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class AyncUserService {

    @Autowired
    UserService userService;

    @Async("defaultTaskExecutor")
    public CompletableFuture<Response> asyncRegUserRN(User user) throws Exception{
        try {
            return CompletableFuture.completedFuture(userService.regUserRN(user));
        } catch (Exception e){
            throw new Exception(e);
        }
    }

    @Async("defaultTaskExecutor")
    public CompletableFuture<Response> asyncRegUserR(User user) throws Exception {
        try{
            return CompletableFuture.completedFuture(userService.regUserR(user));
        } catch (Exception e){
            throw new Exception(e);
        }
    }

    @Async("defaultTaskExecutor")
    public CompletableFuture<Response> asyncDeregUserRN(String userId) throws Exception{
        try{
            return CompletableFuture.completedFuture(userService.deregUserRN(userId));
        } catch (Exception e){
            throw new Exception(e);
        }
    }

    @Async("defaultTaskExecutor")
    public CompletableFuture<Response> asyncDeregUserR(String userId) throws Exception {
        try{
            return CompletableFuture.completedFuture(userService.deregUserR(userId));
        } catch (Exception e){
            throw new Exception(e);
        }
    }
}
