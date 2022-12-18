package com.crowdos.backend.dao;

import com.crowdos.backend.model.emails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

public interface EmailDao extends JpaRepository<emails,String>, JpaSpecificationExecutor<emails>, Serializable{

}
