package com.crowdos.backend.dao;

import com.crowdos.backend.model.token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

public interface TokenDao extends JpaRepository<token,Long>, JpaSpecificationExecutor<token>, Serializable {}
