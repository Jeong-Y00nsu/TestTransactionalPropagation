package com.example.demo.filter;


import com.example.demo.constant.Constant;
import com.example.demo.domain.User;
import com.example.demo.domain.UserRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.stream.Collectors;

public class UserFilter implements Filter {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String requestUrl = ((HttpServletRequest)request).getRequestURI();
        String json = ((HttpServletRequest)request).getReader().lines().collect(Collectors.joining());
        UserRequest userRequest = objectMapper.readValue(json,UserRequest.class);

        if(requestUrl == null) return;
        switch (requestUrl){
            case Constant.USER_REG_ASYNC_RN:
            case Constant.USER_REG_ASYNC_R:
            case Constant.USER_REG_SYNC_RN:
            case Constant.USER_REG_SYNC_R:
                // 검증
                if(!isValidationRegParam(userRequest)) return;
                break;
            case Constant.USER_DEREG_ASYNC_RN:
            case Constant.USER_DEREG_ASYNC_R:
            case Constant.USER_DEREG_SYNC_RN:
            case Constant.USER_DEREG_SYNC_R:
                //검증
                if(!isValidationaDeregParam(userRequest.getId())) return;
                break;
            default:
                break;
        }
        chain.doFilter(request, response);
    }

     public boolean isValidationRegParam(UserRequest user){
         if(user==null){
             return false;
         }else if(user.getId()==null || user.getId().isBlank() || user.getId().isEmpty()){
             return false;
         }else if(user.getPw()==null || user.getPw().isBlank() || user.getPw().isEmpty()){
             return false;
         }else if(user.getName()==null || user.getName().isBlank() || user.getName().isEmpty()){
             return false;
         }
         return true;
     }

     public boolean isValidationaDeregParam(String userId){
        if(userId == null || userId.isEmpty() || userId.isBlank()) return false;
        return true;
     }
}
