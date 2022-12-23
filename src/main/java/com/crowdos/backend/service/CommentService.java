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
    Page<comment> getCommentInPage(int pagenum, int pagesize, long id, boolean isroot);
    List<comment> getCommentInList(long id, boolean isroot);

    Map<String, Object> postComment(String token, String eventId, Map<String, String> comment);
}
