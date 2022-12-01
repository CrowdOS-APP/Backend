package com.crowdos.backend.repository;

import com.crowdos.backend.model.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepositoryUid extends JpaRepository<user,Long> {
    @Query(value = "select * from user where uid=?1",nativeQuery = true)
    user findUserbyId(Long uid);
    user findByUid(Long uid);
}
