package com.crowdos.backend.service;

import com.crowdos.backend.model.followlist;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public interface FollowService {
    followlist createFollow(followlist followPair);
    long deleteFollow(Long uid, Long follower);
    Page<followlist> findFollowerByUid(int pagenum, int pagesize, long uid);
    Page<followlist> findFollowingByUid(int pagenum,int pagesize,long uid);
}
