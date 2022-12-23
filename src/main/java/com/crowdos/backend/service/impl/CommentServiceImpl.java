package com.crowdos.backend.service.impl;

import com.crowdos.backend.dao.CommentDao;
import com.crowdos.backend.model.comment;
import com.crowdos.backend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;

    public comment findCommentByCid(Long commentid) {
        Optional<comment> entity = commentDao.findById(commentid);
        return entity.orElse(null);
    }

    public comment createComment(comment newComment) {
        return commentDao.save(newComment);
    }

    public comment updateComment(comment newComment) {
        return commentDao.save(newComment);
    }

    public void deleteCommentByCid(Long commentid) {
        commentDao.deleteById(commentid);
    }

    public Page<comment> getCommentInPage(int pagenum, int pagesize, long id, boolean isroot) {
        PageRequest pageRequest = PageRequest.of(pagenum, pagesize, Sort.Direction.DESC);
        if (isroot) {
            return commentDao.findAll((Specification<comment>) (root, query, builder) -> query.where(builder.equal(root.get("eventid"), id)).getRestriction(), pageRequest);
        } else {
            return commentDao.findAll((Specification<comment>) (root, query, builder) -> query.where(builder.equal(root.get("commentid"), id)).getRestriction(), pageRequest);
        }
    }

    public List<comment> getCommentInPage(long id, boolean isroot) {
        if (isroot) {
            return commentDao.findAll((Specification<comment>) (root, query, builder) -> query.where(builder.equal(root.get("eventid"), id)).getRestriction());
        } else {
            return commentDao.findAll((Specification<comment>) (root, query, builder) -> query.where(builder.equal(root.get("commentid"), id)).getRestriction());
        }
    }
}
