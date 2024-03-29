package com.crowdos.backend.dao;

import com.crowdos.backend.model.comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

public interface CommentDao extends JpaRepository<comment,Long>,JpaSpecificationExecutor<comment>, Serializable {
}
