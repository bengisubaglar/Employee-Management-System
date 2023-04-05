package com.spring1.EmployeeManagement.Repository;

import com.spring1.EmployeeManagement.Entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    public List<Department> findAllByOrderByIdAsc();
}
