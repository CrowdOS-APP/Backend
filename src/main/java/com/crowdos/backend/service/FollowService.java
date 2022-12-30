package com.crowdos.backend.service;

import com.crowdos.backend.model.followlist;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface FollowService {
    Map<String, Object> follow(String token, Long eventID, Map<String, String> follow);

    followlist createFollow(Long uid,Long eventid);
    long deleteFollow(Long uid, Long eventid);
    boolean checkIfFollowed(long uid,long eventid);
    Page<followlist> findFollowingEventByUidInPage(int pagenum, int pagesize, long uid);
    Page<followlist> findEventFollowerByEidInPage(int pagenum, int pagesize, long uid);

    List<followlist> following(String token);
}
