package com.self.service.impl;

import com.self.dao.UserDAO;
import com.self.entity.UserEntity;
import com.self.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDAO userDAO;

    public List<UserEntity> getAllUsers(String id) {
        return userDAO.getAllUsers(id);
    }

    public void updateUserById(UserEntity userEntity) {
        userDAO.updateUserById(userEntity);
    }
}
