package com.self.controller;

import com.self.entity.UserEntity;
import com.self.service.UserService;
import com.self.service.impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class UserController {

    private Logger logger = Logger.getLogger("UserController");

    @Resource
    private UserService userService;

    @RequestMapping(value = "getUserInfo")
    public ModelAndView getAllUsers(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();

        String id = request.getParameter("id");
        UserEntity userEntity = new UserEntity();

        // 执行查询语句
        Date first = new Date();
        List<UserEntity> userList = userService.getAllUsers(id);
        logger.info("第一次查询耗时：" + (new Date().getTime() - first.getTime()) + "ms");
        logger.info("第一次查询结果：" + userList.toString());

        // 执行第二次查询语句，如果二级缓存生效，则在控制台应直接查询出数据，不会打印出sql语句
        Date second = new Date();
        List<UserEntity> userList2 = userService.getAllUsers(id);
        logger.info("查询耗时：" + (new Date().getTime() - second.getTime()) + "ms");
        logger.info("第二次查询结果：" + userList2.toString());

        // 执行更新操作
        userEntity.setUsername("哈哈哈");
        userService.updateUserById(userEntity);
        // 执行第三次查询语句，由于查询前已经执行了更新操作，接下来的数据应该是从数据库查出来的，控制台应该打印出sql语句
        Date third = new Date();
        List<UserEntity> userList3 = userService.getAllUsers(id);
        logger.info("查询耗时：" + (new Date().getTime() - third.getTime()) + "ms");
        logger.info("第三次查询结果：" + userList3.toString());


        model.setViewName("produce");
        return model;
    }
}
