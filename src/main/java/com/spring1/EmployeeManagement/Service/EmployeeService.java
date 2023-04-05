package com.spring1.EmployeeManagement.Service;

import com.spring1.EmployeeManagement.Entity.Employee;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(int theId);

    void save(Employee theEmployee);

    void deleteById(int theId);

    List<Employee> findByFirstNameContaining(String firstName);

    List<Employee> findAllSorted(String sortField, Sort.Direction sortDirection);
}
