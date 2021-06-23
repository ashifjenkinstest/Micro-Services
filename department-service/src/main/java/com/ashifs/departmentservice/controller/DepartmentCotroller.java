package com.ashifs.departmentservice.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import com.ashifs.departmentservice.entity.Department;
import com.ashifs.departmentservice.service.DepartmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jdk.internal.org.jline.utils.Log;
import lombok.val;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/departments")
public class DepartmentCotroller {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/")
    public List<Department> getDepartments() {
        // log.info("Inside DepartmentCotroller.saveDepartment");
        // System.out.println(department.toString());
        return departmentService.getAll();
    }

    @PostMapping("/")
    public Department saveDepartment(@RequestBody Department department) {
        // log.info("Inside DepartmentCotroller.saveDepartment");
        System.out.println(department.toString());
        return departmentService.save(department);
    }

    @GetMapping("/{id}")
    public Department getDepartment(@PathVariable("id") Long departmentId) {

        System.out.println("departmentId " + departmentId);

        return departmentService.get(departmentId);

    }

}
