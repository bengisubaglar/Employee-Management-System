package com.spring1.EmployeeManagement.Service;

import com.spring1.EmployeeManagement.DTO.EmployeeDTO;
import com.spring1.EmployeeManagement.Entity.Employee;
import com.spring1.EmployeeManagement.Repository.DepartmentRepository;
import com.spring1.EmployeeManagement.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;


    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAllByOrderByFirstNameAscLastNameAsc();
    }

    @Override
    public Employee findById(int theId) {
        Optional<Employee> result = employeeRepository.findById(theId);

        Employee theEmployee = null;

        if (result.isPresent()) {
            theEmployee = result.get();
        }
        else {
            // we didn't find the employee
            throw new RuntimeException("Did not find employee id - " + theId);
        }

        return theEmployee;
    }

    @Override
    public void save(Employee theEmployee) {
        employeeRepository.save(theEmployee);
    }

    @Override
    public void deleteById(int theId) {
        employeeRepository.deleteById(theId);
    }

    @Override
    public List<Employee> findByFirstNameContaining(String firstName) {
        return employeeRepository.findByFirstNameContaining(firstName);
    }

    @Override
    public List<Employee> findAllSorted(String sortField, Sort.Direction sortDirection) {
        Sort sort = Sort.by(sortDirection, sortField);
        return employeeRepository.findAll(sort);
    }

    @Override
    public List<EmployeeDTO> findAllDTO() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        for(Employee employee: employees ){
            employeeDTOS.add(EmployeeDTO.fromEmployee(employee));
        }
        return employeeDTOS;
    }

    @Override
    public List<Employee> findByDepartmentId(int departmentId) {
        return employeeRepository.findByDepartmentId(departmentId);
    }

    @Override
    public List<EmployeeDTO> findAllSortedDTO(String sortField, Sort.Direction sortDirection) {
        Sort sort = Sort.by(sortDirection, sortField);
        List<Employee> employees = employeeRepository.findAll(sort);
        List<EmployeeDTO> employeeDTOs = new ArrayList<>();
        for (Employee employee : employees) {
            employeeDTOs.add(EmployeeDTO.fromEmployee(employee));
        }
        return employeeDTOs;
    }
}