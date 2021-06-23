package com.ashifs.userservice.VO;

import com.ashifs.userservice.entity.User;

public class RestTemplateVO {
    private User user;
    private Department department;

    public RestTemplateVO() {
    }

    public RestTemplateVO(Department department, User user) {
        this.department = department;
        this.user = user;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "RestTemplateVO [department=" + department + ", user=" + user + "]";
    }

}
