package com.spring1.EmployeeManagement.Controller;

import com.spring1.EmployeeManagement.DTO.EmployeeDTO;
import com.spring1.EmployeeManagement.Entity.Department;
import com.spring1.EmployeeManagement.Entity.Employee;
import com.spring1.EmployeeManagement.Service.DepartmentService;
import com.spring1.EmployeeManagement.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/departments")
public class DepartmentController {
    private DepartmentService departmentService;
    private EmployeeService employeeService;

    @Autowired
    public DepartmentController(DepartmentService departmentService,EmployeeService employeeService){
        this.departmentService = departmentService;
        this.employeeService = employeeService;

    }

    // add mapping for "/list"
    @GetMapping("/list")
    public String listDepartments(Model theModel) {

        // get the departments from the database
        List<Department> departmentList = departmentService.findAll();

        // add to the spring model
        theModel.addAttribute("departments",departmentList );

        return "employees/list-departments";
    }

    @GetMapping("/showAddForm")
    public String showAddForm(Model theModel){

        // create model attribute to bind form data
        Department theDepartment = new Department();
        theModel.addAttribute("departments",theDepartment);

        return "employees/department-form.html";
    }

    @PostMapping("/save")
    public String saveDepartment(@ModelAttribute("department") Department department){

        // save the department
        departmentService.save(department);

        // use a redirect to prevent duplicate savings
        return "redirect:/departments/list";
    }

    @GetMapping("/showUpdateForm")
    public String showUpdateForm(@RequestParam("departmentId") int id, Model theModel){

        // get the department from the service
        Department department = departmentService.findById(id);

        // set department in the model to prepopulate the form (placeholders)
        theModel.addAttribute("departments",department);

        // send over to the form
        return "employees/department-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("departmentId") int id){

        // delete the department
        departmentService.deleteById(id);

        // redirect to department list
        return "redirect:/departments/list";
    }
    @GetMapping("/list/sort")
    public String sortDepartments(@RequestParam String sortField, @RequestParam String sortDirection, Model model){
        List<Department> departmentList = departmentService.findAllSorted(sortField, Sort.Direction.fromString(sortDirection));
        model.addAttribute("departments",departmentList);
        return "employees/list-departments";
    }

    @GetMapping("/employees")
    public String findEmployeesByDepartmentId(@RequestParam("departmentId") int departmentId, Model model){

        List<Employee> employees = employeeService.findByDepartmentId(departmentId);

        // logging the returned data to console
        System.out.println("Employees in department " + departmentId + ": " + employees.toString());

        model.addAttribute("employees",employees);
        return "employees/list-employees-by-dep";
    }
}