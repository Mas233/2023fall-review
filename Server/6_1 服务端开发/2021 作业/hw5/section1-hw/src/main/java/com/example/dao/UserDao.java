package com.example.dao;

import com.example.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @Author stormbroken
 * Create by 2021/04/04
 * @Version 1.0
 **/

public interface UserDao extends JpaRepository<User, Long> {
    @Query(value = "select count(*) from t_user where user_name = ?1 and password = ?2", nativeQuery=true)
    Object getMatchCount(String userName, String password);

    @Query(value = "select * from t_user where user_name = ?1",nativeQuery=true)
    User findUserByUserName(String username);
}
