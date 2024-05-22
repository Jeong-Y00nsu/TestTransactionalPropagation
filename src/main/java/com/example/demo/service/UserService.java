package com.example.demo.service;

import com.example.demo.constant.Constant;
import com.example.demo.domain.Response;
import com.example.demo.domain.User;
import com.example.demo.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService {
    Logger log = LoggerFactory.getLogger(UserService.class);

    UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = Exception.class )
    public Response regUserRN(User user) throws Exception{
        try{
            boolean check = this.proccess(Arrays.asList("a","b","c","d"));
            userMapper.insertRegUser(user);
            return new Response(Constant.OK,"OK");
        } catch(Exception e){
            throw new Exception(e);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public Response regUserR(User user) throws Exception{
        try{
            boolean check = this.proccess(Arrays.asList("a","b","c","d"));
            userMapper.insertRegUser(user);
            return new Response(Constant.OK,"OK");
        } catch (Exception e){
            throw new Exception(e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class )
    public Response deregUserRN(String userId) throws Exception{
        try{
            boolean check = this.proccess(Arrays.asList("a","b","c","d"));
            userMapper.updateDeregUser(userId);
            throw new Exception();
            //return new Response(Constant.OK, "OK");
        } catch (Exception e){
            throw new Exception(e);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public Response deregUserR(String userId) throws Exception{
        try{
            boolean check = this.proccess(Arrays.asList("a","b","c","d"));
            userMapper.updateDeregUser(userId);
            throw new Exception();
            //return new Response(Constant.OK, "OK");
        } catch (Exception e){
            throw new Exception(e);
        }
    }

    public List<User> selectAllUser(){
        return userMapper.selectAllUser();
    }

    public void deleteUser(){
        userMapper.deleteUser();
    }

    public boolean proccess(List<String> list){
        for(String str : list){
            if(str.contains("a")) return true;
        }
        return false;
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public ResponseEntity<Response> regUser(User user) throws Exception {
        try {
            userMapper.insertRegUser(user);
            return ResponseEntity.ok(new Response(Constant.OK, "OK"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(Constant.FAIL, "FAIL"));
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public ResponseEntity<Response>  deregUser(String userId) throws Exception {
        try {
            userMapper.updateDeregUser(userId);
            throw new Exception();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Response(Constant.FAIL, "FAIL"));
        }
    }
}
