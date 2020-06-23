package com.websocket.service;

import com.websocket.pojo.User;

import java.util.List;

public interface UserService {

    List<User> findAll() throws Exception;

}
