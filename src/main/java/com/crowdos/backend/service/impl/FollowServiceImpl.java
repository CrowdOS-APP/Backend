package com.crowdos.backend.service.impl;

import com.crowdos.backend.dao.FollowDao;
import com.crowdos.backend.model.followlist;
import com.crowdos.backend.service.FollowService;
import com.crowdos.backend.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FollowServiceImpl implements FollowService {

    @Autowired
    TokenService tokenService;
    @Autowired
    private FollowDao followDao;

    public followlist createFollow(followlist followPair){
        return followDao.save(followPair);
    }
    public long deleteFollow(Long uid, Long follower){
        int cnt=0;
        var entities=followDao.findAll((Specification<followlist>) (root, query, builder) -> query.where(builder.and(builder.equal(root.get("uid"),uid),builder.equal(root.get("follower"),follower))).getRestriction());
        for(var entity:entities){
            followDao.delete(entity);
            cnt++;
        }
        return cnt;
    }
    public Page<followlist> findFollowerByUidInPage(int pagenum, int pagesize, long uid){
        PageRequest pageRequest=PageRequest.of(pagenum,pagesize, Sort.Direction.DESC);
        return followDao.findAll((Specification<followlist>) (root, query, builder) -> query.where(builder.equal(root.get("uid"),uid)).getRestriction(), pageRequest);
    }
    public Page<followlist> findFollowingByUidInPage(int pagenum, int pagesize, long uid){
        PageRequest pageRequest=PageRequest.of(pagenum,pagesize);
        return followDao.findAll((Specification<followlist>) (root, query, builder) -> query.where(builder.equal(root.get("follower"),uid)).getRestriction(), pageRequest);
    }
    public List<followlist> findAllFollowerByUid(long uid){
        return followDao.findAll((Specification<followlist>) (root, query, builder) -> query.where(builder.equal(root.get("uid"),uid)).getRestriction());
    }
    public List<followlist> findAllFollowingByUid(long uid){
        return followDao.findAll((Specification<followlist>) (root, query, builder) -> query.where(builder.equal(root.get("follower"),uid)).getRestriction());
    }

    public Map<String, Object> follow(String token, Long UID, Map<String, String> follow) {
        Map<String, Object> map = new HashMap<>(1);
        if(tokenService.findUidByToken(token)==null){
            map.put("isSucceed",false);
        }else{
            Long uid = tokenService.findUidByToken(token).getUid();
            Boolean isFollow = Boolean.parseBoolean(follow.get("isFollow"));
            if(isFollow){
                followlist aFollow = new followlist();
                aFollow.setUid(UID);
                aFollow.setFollower(uid);
                createFollow(aFollow);
            }else{
                deleteFollow(UID,uid);
            }
            map.put("isSucceed",true);
        }
        return map;
    }
    public List following(String token){
        if(tokenService.findUidByToken(token)==null){
            return null;
        }else{
            return findAllFollowingByUid(tokenService.findUidByToken(token).getUid());
        }
    }
}