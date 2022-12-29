package com.crowdos.backend.service.impl;

import com.crowdos.backend.dao.FollowDao;
import com.crowdos.backend.model.followlist;
import com.crowdos.backend.model.token;
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
        var entities=followDao.findAll((Specification<followlist>) (root, query, builder) -> query.where(builder.and(builder.equal(root.get("uid"),uid),builder.equal(root.get("eventid"),follower))).getRestriction());
        if(entities!=null){
            for(var entity:entities){
                followDao.delete(entity);
                cnt++;
            }
        }
        return cnt;
    }

    public boolean checkIfFollowed(long uid,long eventid){
        return followDao.exists((Specification<followlist>) (root, query, criteriaBuilder) -> query.where(criteriaBuilder.and(criteriaBuilder.equal(root.get("uid"),uid),criteriaBuilder.equal(root.get("eventid"),eventid))).getRestriction());
    }

    public Page<followlist> findFollowingEventByUidInPage(int pagenum, int pagesize, long uid){
        PageRequest pageRequest=PageRequest.of(pagenum,pagesize, Sort.Direction.DESC);
        return followDao.findAll((Specification<followlist>) (root, query, builder) -> query.where(builder.equal(root.get("uid"),uid)).getRestriction(), pageRequest);
    }
    public Page<followlist> findEventFollowerByEidInPage(int pagenum, int pagesize, long eid){
        PageRequest pageRequest=PageRequest.of(pagenum,pagesize);
        return followDao.findAll((Specification<followlist>) (root, query, builder) -> query.where(builder.equal(root.get("eventid"),eid)).getRestriction(), pageRequest);
    }
    public List<followlist> findFollowingEventByUid(long uid){
        return followDao.findAll((Specification<followlist>) (root, query, builder) -> query.where(builder.equal(root.get("uid"),uid)).getRestriction());
    }
    public List<followlist> findEventFollowerByEid(long eid){
        return followDao.findAll((Specification<followlist>) (root, query, builder) -> query.where(builder.equal(root.get("eventid"),eid)).getRestriction());
    }

    public Map<String, Object> follow(String token, Long eventID, Map<String, String> follow) {
        Map<String, Object> map = new HashMap<>(1);
        token Token= tokenService.findUidByToken(token);
        if(Token==null){
            map.put("isSucceed",false);
        }else{
            Long uid = Token.getUid();
            Boolean isFollow = Boolean.parseBoolean(follow.get("isFollow"));
            if(isFollow){
                followlist aFollow = new followlist();
                aFollow.setUid(uid);
                aFollow.setEventid(eventID);
                createFollow(aFollow);
            }else{
                deleteFollow(uid,eventID);
            }
            map.put("isSucceed",true);
        }
        return map;
    }
    public List<followlist> following(String token){
        token Token=tokenService.findUidByToken(token);
        if(Token==null){
            return null;
        }else{
            return findFollowingEventByUid(Token.getUid());
        }
    }
}