package com.ashifs.userservice.service;

import java.util.List;

import com.ashifs.userservice.VO.Department;
import com.ashifs.userservice.VO.RestTemplateVO;
import com.ashifs.userservice.entity.User;
import com.ashifs.userservice.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    public User save(User user) {
        return userRepository.save(user);
    }

    public User get(Long userId) {
        return userRepository.findByUserId(userId);
    }

    public RestTemplateVO getUserDepartmentVO(Long userId) {
        RestTemplateVO restTemplateVO = new RestTemplateVO();
        System.out.println("departmentId " + userId);
        User user = get(userId);
        Department department = restTemplate.getForObject(
                "http://DEPARTMENT-SERVICE-EUREKACLIENT/departments/" + user.getDepartmentId(), Department.class);
        restTemplateVO.setUser(user);
        restTemplateVO.setDepartment(department);

        return restTemplateVO;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

}
