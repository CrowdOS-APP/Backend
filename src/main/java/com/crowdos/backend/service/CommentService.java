package com.crowdos.backend.service;

import com.crowdos.backend.model.comment;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface CommentService {
    comment findCommentByCid(Long commentid);
    comment createComment(comment newComment);
    comment updateComment(comment newComment);
    void deleteCommentByCid(Long commentid);
    Page<comment> getAllCommentInPage(int pagenum, int pagesize, long eid);
    List<comment> getAllCommentInList(long eid);
    Page<comment> getUserCommentInPage(int pagenum, int pagesize, long uid);
    List<comment> getUserCommentInList(long uid);

    Map<String, Object> postComment(String token, String eventId, Map<String, String> comment);

    List<comment> myComment(String token);
}
