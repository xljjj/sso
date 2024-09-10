package com.cqu.sso.mapper;

import com.cqu.sso.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    User queryUserByName(String username);  //通过用户名查找用户

    void addUser(User user);  //添加用户
}
