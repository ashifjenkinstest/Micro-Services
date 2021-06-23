package com.ashifs.userservice.controller;

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

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public User saveUser(@RequestBody User user) {
        // log.info("Inside DepartmentCotroller.saveDepartment");
        System.out.println(user.toString());
        return userService.save(user);
    }

    @GetMapping("/")
    public List<User> getUsers() {
        // log.info("Inside DepartmentCotroller.saveDepartment");
        // System.out.println(user.toString());
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public RestTemplateVO getUserDepartment(@PathVariable("id") Long userId) {

        return userService.getUserDepartmentVO(userId);

    }

}
