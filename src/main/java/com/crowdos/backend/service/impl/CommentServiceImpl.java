package com.crowdos.backend.service.impl;

import com.crowdos.backend.dao.CommentDao;
import com.crowdos.backend.model.comment;
import com.crowdos.backend.model.token;
import com.crowdos.backend.service.CommentService;
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
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    TokenService tokenService;
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

    public Page<comment> getAllCommentInPage(int pagenum, int pagesize, long eid) {
        return commentDao.findAll((Specification<comment>) (root, query, builder) -> query.where(builder.equal(root.get("eventid"), eid)).getRestriction(), PageRequest.of(pagenum, pagesize, Sort.Direction.DESC));
    }

    public List<comment> getAllCommentInList(long eid) {
        return commentDao.findAll((Specification<comment>) (root, query, builder) -> query.where(builder.equal(root.get("eventid"), eid)).getRestriction());
    }

    public Page<comment> getUserCommentInPage(int pagenum, int pagesize, long uid) {
        return commentDao.findAll((Specification<comment>) (root, query, builder) -> query.where(builder.equal(root.get("uid"), uid)).getRestriction(), PageRequest.of(pagenum, pagesize, Sort.Direction.DESC));
    }

    public List<comment> getUserCommentInList(long uid) {
        return commentDao.findAll((Specification<comment>) (root, query, builder) -> query.where(builder.equal(root.get("uid"), uid)).getRestriction());
    }

    public Map<String, Object> postComment(String token, String eventId, Map<String, String> comment){
        Map map = new HashMap<>(1);
        var Token=tokenService.findUidByToken(token);
        if(Token==null){
            map.put("isSucceed",false);
        }else{
            Long uid = Token.getUid();
            comment aComment = new comment();
            aComment.setContent(comment.get("comment"));
            map.put("isSucceed",true);
        }
        return map;
    }

    public List<comment> myComment(String aToken){
        token Token=tokenService.findUidByToken(aToken);
        if(Token==null){
            return null;
        }else{
            Long uid = Token.getUid();
            return getUserCommentInList(uid);
        }
    }
}
