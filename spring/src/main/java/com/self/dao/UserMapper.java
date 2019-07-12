package com.self.dao;

import com.self.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    List<UserEntity> getAllUsers(String id);
    void updateUserById(UserEntity userEntity);
}
