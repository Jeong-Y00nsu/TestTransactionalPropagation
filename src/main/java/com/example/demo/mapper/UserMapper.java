package com.example.demo.mapper;

import com.example.demo.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {

    int insertRegUser(@Param("user") User user);

    int updateDeregUser(@Param("id") String id);

    User selectUser(@Param("id") String id);

    List<User> selectAllUser();
    void deleteUser();
}
