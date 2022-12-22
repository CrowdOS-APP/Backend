package com.crowdos.backend.service;

import com.crowdos.backend.model.token;
import org.springframework.stereotype.Component;

@Component
public interface TokenService{
    token findTokenByUid(Long uid);
    token createToken(token newToken);
    token updateToken(token newToken);
    void deleteTokenByUid(Long uid);
    token findUidByToken(String aToken);
}
