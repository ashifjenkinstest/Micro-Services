package com.ashifs.departmentservice.service;

import java.util.List;
import java.util.Optional;

import com.ashifs.departmentservice.entity.Department;
import com.ashifs.departmentservice.repository.DepartmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import jdk.internal.org.jline.utils.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public Department save(Department department) {

        return departmentRepository.save(department);
    }

    public Department get(Long id) {

        return departmentRepository.findByDepartmentId(id);
    }

    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

}
