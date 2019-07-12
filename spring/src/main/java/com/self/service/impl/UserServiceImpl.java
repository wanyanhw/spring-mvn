package com.self.service.impl;

import com.self.dao.UserMapper;
import com.self.entity.UserEntity;
import com.self.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    public List<UserEntity> getAllUsers(String id) {
        return userMapper.getAllUsers(id);
    }

    public void updateUserById(UserEntity userEntity) {
        userMapper.updateUserById(userEntity);
    }
}
