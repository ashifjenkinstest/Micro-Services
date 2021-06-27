package com.ashifs.userservice.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ashifs.userservice.VO.Department;
import com.ashifs.userservice.VO.RestTemplateVO;
import com.ashifs.userservice.entity.User;
import com.ashifs.userservice.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users/")
public class UserController {

    private static final org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager
            .getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public User saveUser(@RequestBody User user) {
        // log.info("Inside DepartmentCotroller.saveDepartment");
        logger.debug("Enter saveUser");
        System.out.println(user.toString());
        logger.debug("Exit saveUser");
        return userService.save(user);
    }

    @GetMapping("/")
    public List<User> getUsers() {
        // log.info("Inside DepartmentCotroller.saveDepartment");
        // System.out.println(user.toString());
        logger.debug("Enter getUsers ");
        logger.debug("Exit getUsers ");

        List<User> users = new ArrayList<>();

        User user = new User(999999L, "firstName", "lastName", "email", 999999L);

        users.add(user);

        if (userService.getAll() != null) {
            System.out.println("ASHIFSARKAR not null" + userService.getAll());
        } else {
            System.out.println("ASHIFSARKAR null" + users);
        }
        return userService.getAll() == null ? users : userService.getAll();
    }

    @GetMapping("/{id}")
    public RestTemplateVO getUserDepartment(@PathVariable("id") Long userId) {
        logger.debug("Enter getUserDepartment");

        logger.debug("Exit getUserDepartment");
        return userService.getUserDepartmentVO(userId) == null
                ? new RestTemplateVO(new Department(999999, "departmentName", "departmentAddress", "departmentCode"),
                        new User(userId, "firstName", "lastName", "email", userId))
                : userService.getUserDepartmentVO(userId);

    }

}
