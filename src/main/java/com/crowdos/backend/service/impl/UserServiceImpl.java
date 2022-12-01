package com.crowdos.backend.service.impl;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.crowdos.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    private static final long EXPIRE_TIME = 365L * 24 * 60 * 60 * 1000;
    private static final String TOKEN_SECRET = "privateKey";

    public static String createToken(String userId) {
        try {
            // 设置过期时间
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            // 私钥和加密算法
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            // 设置头部信息
            Map<String, Object> header = new HashMap<>(2);
            header.put("Type", "Jwt");
            header.put("alg", "HS256");
            // 返回token字符串
            return JWT.create()
                    .withHeader(header)
                    .withClaim("userId", userId)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Boolean register(Map<String, String> register) {

//        return email+password+"114514";

        String email = register.get("email");
        String passwd = register.get("passwd");
        System.out.println("out: " + createToken(email));
//        if( ){
//
//        createToken(getUserUid())
//        }
        return Boolean.TRUE;
    }
    public String login(Map<String, String> login) {


        String email = login.get("email");
        String passwd = login.get("passwd");
//      String token = getToken(email,passwd);
        String token = "eyJUeXBlIjoiSnd0IiwidHlwIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJleHAiOjE2NzExOTY0MTQsInVzZXJJZCI6InQuYm1zaXJ1bXBAcXEuY29tIn0.TPtwnBptWbYTPX-IHDN-Tp3K5Ef7hYXYtk3dFyBAi2c";
        //token 为空字符串意味着登陆失败
        return token;
    }
}