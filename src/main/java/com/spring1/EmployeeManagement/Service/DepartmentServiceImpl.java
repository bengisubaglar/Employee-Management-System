package com.spring1.EmployeeManagement.Service;

import com.spring1.EmployeeManagement.Entity.Department;
import com.spring1.EmployeeManagement.Repository.DepartmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    private DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository){
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAllByOrderByIdAsc();
    }

    @Override
    public Department findById(int theId) {
        Optional<Department> result = departmentRepository.findById(theId);

        Department department = null;

        if (result.isPresent()) {
            department = result.get();
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Did not find employee id - " + theId);
        }

        return department;
    }

    @Override
    public void save(Department department) {
        departmentRepository.save(department);
    }

    @Override
    public void deleteById(int theId) {
        departmentRepository.deleteById(theId);
    }

    @Override
    public List<Department> findAllSorted(String sortField, Sort.Direction sortDirection) {
        Sort sort = Sort.by(sortDirection, sortField);
        return departmentRepository.findAll(sort);
    }
}
