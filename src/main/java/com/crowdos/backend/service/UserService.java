package com.crowdos.backend.service;

import java.util.Map;

public interface UserService {
    Boolean register(Map<String,String> register);

    String login(Map<String, String> login);
}
