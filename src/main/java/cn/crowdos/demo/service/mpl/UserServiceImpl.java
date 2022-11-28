package cn.crowdos.demo.service.mpl;


import cn.crowdos.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public String register(Map<String,String> register) {

//        return email+password+"114514";
        return register.get("email");
    }
}