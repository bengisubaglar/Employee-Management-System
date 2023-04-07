package com.spring1.EmployeeManagement.Service;


import com.spring1.EmployeeManagement.Entity.User;

public interface UserService {

    User findByUsername(String username);

}
