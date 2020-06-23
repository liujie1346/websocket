package com.websocket.dao;

import com.websocket.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

@Mapper
public interface UserDao {

    @SelectProvider(type = UserProvider.class, method = "findAll")
    List<User> findAll();

}
