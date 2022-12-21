package com.crowdos.backend.service;

import com.crowdos.backend.model.comment;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public interface CommentService {
    comment findCommentByCid(Long commentid);
    comment createComment(comment newComment);
    comment updateComment(comment newComment);
    void deleteCommentByCid(Long commentid);
    Page<comment> getCommentInPage(int pagenum, int pagesize, long id, boolean isroot);
}