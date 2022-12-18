package com.crowdos.backend.service;

import com.crowdos.backend.model.user;

import java.util.Map;

public interface UserService {
    Boolean register(Map<String,String> register);

    String login(Map<String, String> login);

    user findUserById(Long uid);
    user createUser(user newUser);
    void deleteUserById(Long uid);
    user updateUserById(user newUser);
}
