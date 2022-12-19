package com.crowdos.backend.service.impl;

import com.crowdos.backend.dao.TokenDao;
import com.crowdos.backend.model.token;
import com.crowdos.backend.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {
    @Autowired
    private TokenDao tokenDao;
    public token findTokenByUid(Long uid){
        return tokenDao.findById(uid).orElse(null);
    }

    public token createToken(token newToken){
        return tokenDao.save(newToken);
    }

    public token updateToken(token newToken){
        return tokenDao.save(newToken);
    }

    public void deleteTokenByUid(Long uid){
        tokenDao.deleteById(uid);
    }

}
