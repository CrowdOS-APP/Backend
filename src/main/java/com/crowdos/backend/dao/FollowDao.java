package com.crowdos.backend.dao;

import com.crowdos.backend.model.followlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

public interface FollowDao extends JpaRepository<followlist,Long>, JpaSpecificationExecutor<followlist>, Serializable {
}
