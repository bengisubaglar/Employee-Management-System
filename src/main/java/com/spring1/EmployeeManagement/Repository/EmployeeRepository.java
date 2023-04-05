package com.spring1.EmployeeManagement.Repository;
import com.spring1.EmployeeManagement.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // add a method to sort by name
    public List<Employee> findAllByOrderByFirstNameAscLastNameAsc();

    // add a method to search by name and last name
    public List<Employee> findByFirstNameContaining(String firstName);

}
