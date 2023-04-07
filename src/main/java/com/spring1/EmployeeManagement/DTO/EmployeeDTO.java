package com.spring1.EmployeeManagement.DTO;

import com.spring1.EmployeeManagement.Entity.Employee;

public class EmployeeDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private int departmentId;
    private String departmentName;

    public EmployeeDTO(){

    }

    public EmployeeDTO(int id, String firstName, String lastName, String email, int departmentId, String departmentName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
    }

    public EmployeeDTO(String firstName, String lastName, String email, String departmentName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.departmentName = departmentName;
    }

    public static EmployeeDTO fromEmployee(Employee employee){
        return new EmployeeDTO(employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartment().getId(),
                employee.getDepartment().getTitle());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
