package com.self.service;

import com.self.entity.UserEntity;

import java.util.List;

public interface UserService {
    List<UserEntity> getAllUsers(String id);
    void updateUserById(UserEntity userEntity);
}
