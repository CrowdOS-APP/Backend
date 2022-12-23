package com.crowdos.backend.service;

import com.crowdos.backend.model.followlist;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface FollowService {
    Map<String, Object> follow(String token, Long UID, Map<String, String> follow);

    followlist createFollow(followlist followPair);
    long deleteFollow(Long uid, Long follower);
    Page<followlist> findFollowerByUidInPage(int pagenum, int pagesize, long uid);
    Page<followlist> findFollowingByUidInPage(int pagenum, int pagesize, long uid);

    List following(String token);
}
