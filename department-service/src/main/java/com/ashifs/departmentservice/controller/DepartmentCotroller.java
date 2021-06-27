package com.ashifs.departmentservice.controller;

import java.util.Arrays;
import java.util.List;

import com.ashifs.departmentservice.entity.Department;
import com.ashifs.departmentservice.service.DepartmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

//@Slf4j
@Log4j2
@RestController
@RequestMapping("/departments")
public class DepartmentCotroller {

    // private static final org.slf4j.Logger logger =
    // org.slf4j.LoggerFactory.getLogger(DepartmentCotroller.class);
    private static final org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager
            .getLogger(DepartmentCotroller.class);

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/")
    public List<Department> getDepartments() {
        // log.info("Inside DepartmentCotroller.saveDepartment");
        // System.out.println(department.toString());
        log.info("Enter getDepartments");
        log.info("Exit getDepartments");
        return departmentService.getAll() == null
                ? Arrays.asList(new Department(999999, "departmentName", "departmentAddress", "departmentCode"))
                : departmentService.getAll();
    }

    @PostMapping("/")
    public Department saveDepartment(@RequestBody Department department) {
        // log.info("Inside DepartmentCotroller.saveDepartment");
        log.info("Enter saveDepartment");
        log.info(department.toString());
        log.info("Exit saveDepartment");
        return departmentService.save(department);
    }

    @GetMapping("/{id}")
    public Department getDepartment(@PathVariable("id") Long departmentId) {
        log.info("Enter getDepartment");
        log.info("departmentId " + departmentId);
        log.info("Exit getDepartment");
        return departmentService.get(departmentId) == null
                ? new Department(999999, "departmentName", "departmentAddress", "departmentCode")
                : departmentService.get(departmentId);

    }

}
