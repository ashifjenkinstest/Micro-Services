package com.ashifs.departmentservice.repository;

import java.util.Optional;

import com.ashifs.departmentservice.entity.Department;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Department findByDepartmentId(Long id);

}
