package com.crowdos.backend.service;

import com.crowdos.backend.model.user;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public interface UserService {
    Boolean register(Map<String,String> register);

    String login(Map<String, String> login);

    user findUserById(Long uid);
    user createUser(user newUser);
    void deleteUserById(Long uid);
    user updateUser(user newUser);
    boolean isEmailPresent(String email);
    user findUserByEmail(String email);

    Map<String, Object> getUserInfo(String token);

    Map<String, Object> updateUserInfo(String token, Map<String, String> signature);

    Map<String, Object> updatePasswd(String token, Map<String, String> passwd);
}
