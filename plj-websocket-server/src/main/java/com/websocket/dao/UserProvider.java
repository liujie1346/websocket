package com.websocket.dao;

public class UserProvider {

    public String findAll() {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM user");
        return sql.toString();
    }

}
