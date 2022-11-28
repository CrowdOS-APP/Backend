package com.crowdos.backend.service.impl;


import com.crowdos.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private static final long EXPIRE_TIME = 365 * 24 * 60 * 60 * 1000;
    private static final String TOKEN_SECRET = "privateKey";


    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Boolean register(Map<String,String> register) {

//        return email+password+"114514";

        String email = register.get("email");
        String passwd = register.get("passwd");
        if( ){

        }
        return Boolean.TRUE;
    }
}