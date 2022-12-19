package com.crowdos.backend.dao;

import com.crowdos.backend.model.event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

public interface EventDao extends JpaRepository<event, Long>, JpaSpecificationExecutor<event>, Serializable {
}
