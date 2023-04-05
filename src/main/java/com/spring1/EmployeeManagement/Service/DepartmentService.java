package com.spring1.EmployeeManagement.Service;

import com.spring1.EmployeeManagement.Entity.Department;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface DepartmentService {
    List<Department> findAll();

    Department findById(int theId);

    void save(Department department);

    void deleteById(int theId);

    List<Department> findAllSorted(String sortField, Sort.Direction sortDirection);
}
