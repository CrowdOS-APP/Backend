package com.crowdos.backend.service.impl;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.crowdos.backend.dao.UserDao;
import com.crowdos.backend.model.token;
import com.crowdos.backend.model.user;
import com.crowdos.backend.service.TokenService;
import com.crowdos.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Autowired
    TokenService tokenService;

    @Override
    public Boolean register(Map<String, String> register) {

//        return email+password+"114514";

        String email = register.get("email");
        String passwd = register.get("passwd");
//        System.out.println("out: " + createToken(email));
        if(findUserByEmail(email)==null){
            user aUser = new user();
            aUser.setEmail(email);
            aUser.setPasswd(passwd);
            createUser(aUser);
            token Token=new token();
            Token.setUid(aUser.getUid());
            Token.setToken(createToken(aUser.getEmail()));
            tokenService.createToken(Token);
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }
    public String login(Map<String, String> login) {

        String email = login.get("email");
        String passwd = login.get("passwd");
        user aUser = findUserByEmail(email);
        if(aUser == null ) return "";
        else if(aUser.getPasswd().equals(passwd)) {
            var someToken=tokenService.findTokenByUid(aUser.getUid());
            if(someToken==null){
                token Token=new token();
                Token.setUid(aUser.getUid());
                Token.setToken(createToken(aUser.getEmail()));
                tokenService.createToken(Token);
                return Token.getToken();
            }
            else return someToken.getToken();
        }else return "";
    }

    public Map<String, Object> getUserInfo(String token) {
        Map<String, Object> map = new HashMap<>(2);
        Long uid = tokenService.findUidByToken(token).getUid();
        user aUser = findUserById(uid);
        map.put("username",aUser.getName());
        map.put("signature",aUser.getSignature());
        map.put("UID",uid);
        return map;
    }

    public Map<String, Object> updateUserInfo(String token, Map<String, String> signature){
        Map<String, Object> map = new HashMap<>(1);
        if(tokenService.findUidByToken(token)==null){
            map.put("isSucceed",false);
        }else{
            Long uid = tokenService.findUidByToken(token).getUid();
            user aUser = findUserById(uid);
            aUser.setSignature(signature.get("signature"));
            updateUser(aUser);
            map.put("isSucceed",true);
        }
        return map;
    }
    public Map<String, Object> updatePasswd(String token, Map<String, String> passwd){
        Map<String, Object> map = new HashMap<>(1);
        if(tokenService.findUidByToken(token)==null){
            map.put("isSucceed",false);
        }else{
            Long uid = tokenService.findUidByToken(token).getUid();
            user aUser = findUserById(uid);
            if(!aUser.getPasswd().equals(passwd.get("oldPasswd"))){
                map.put("isSucceed",false);
            }else{
                aUser.setPasswd(passwd.get("newPasswd"));
                updateUser(aUser);
                map.put("isSucceed",true);
            }
        }
        return map;
    }
    //Write in DB --Yuki
    @Autowired
    private UserDao userDao;

    // Find
    public user findUserById(Long uid){
        return userDao.findById(uid).isPresent() ? userDao.findById(uid).get():null;
    }


    // Create
    public user createUser(user newUser){
        return userDao.save(newUser);
    }

    // Delete
    public void deleteUserById(Long uid){
        userDao.deleteById(uid);
    }

    // Update
    public user updateUser(user newUser){
        return userDao.save(newUser);
    }

    public boolean isEmailPresent(String email){
        Optional<user> entity=userDao.findOne((Specification<user>) (root, query, builder) -> query.where(builder.equal(root.get("email"),email)).getRestriction());
        return entity.isPresent();
    }
    public user findUserByEmail(String email){
        return userDao.findOne((Specification<user>) (root, query, builder) -> query.where(builder.equal(root.get("email"),email)).getRestriction()).orElse(null);
    }

    public List<user> searchUserByString(String keyword){
        return userDao.findAll((Specification<user>) (root, query, criteriaBuilder) -> query.where(criteriaBuilder.like(root.get("name"),keyword)).getRestriction());
    }
}