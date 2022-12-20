package com.crowdos.backend.service.impl;

import com.crowdos.backend.dao.FollowDao;
import com.crowdos.backend.model.followlist;
import com.crowdos.backend.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class FollowServiceImpl implements FollowService {
    @Autowired
    private FollowDao followDao;

    public followlist createFollow(followlist followPair){
        return followDao.save(followPair);
    }
    public long deleteFollow(Long uid, Long follower){
        return followDao.delete((Specification<followlist>) (root, query, builder) -> query.where(builder.and(builder.equal(root.get("uid"),uid),builder.equal(root.get("follower"),follower))).getRestriction());
    }
    public Page<followlist> findFollowerByUid(int pagenum,int pagesize,long uid){
        PageRequest pageRequest=PageRequest.of(pagenum,pagesize, Sort.Direction.DESC);
        return followDao.findAll((Specification<followlist>) (root, query, builder) -> query.where(builder.equal(root.get("uid"),uid)).getRestriction(), pageRequest);
    }
    public Page<followlist> findFollowingByUid(int pagenum,int pagesize,long uid){
        PageRequest pageRequest=PageRequest.of(pagenum,pagesize);
        return followDao.findAll((Specification<followlist>) (root, query, builder) -> query.where(builder.equal(root.get("following"),uid)).getRestriction(), pageRequest);
    }
}