package com.crowdos.backend.dao;

import com.crowdos.backend.model.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

public interface UserDao extends JpaRepository<user,Long>,JpaSpecificationExecutor<user>, Serializable {

}
