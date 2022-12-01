package com.crowdos.backend.repository;

import com.crowdos.backend.model.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTableUid extends JpaRepository<user, Long> {
    @Query(value = "select * from user where uid=?1",nativeQuery = true)
    user finduserByUid(long uid);
}
