package com.spring1.EmployeeManagement.Controller;

import com.spring1.EmployeeManagement.DTO.EmployeeDTO;
import com.spring1.EmployeeManagement.Entity.Employee;
import com.spring1.EmployeeManagement.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }



    // add mapping for "/list"
    @GetMapping("/list")
    public String listEmployees(Model theModel) {

        // get the employees from the database
        List<EmployeeDTO> employeeDTOList = employeeService.findAllDTO();

        // add to the spring model
        theModel.addAttribute("employees",employeeDTOList );

        return "employees/list-employees";
    }
    @GetMapping("/showAddForm")
    public String showAddForm(Model theModel){

        // create model attribute to bind form data
        Employee theEmployee = new Employee();
        theModel.addAttribute("employee",theEmployee);

        return "employees/employee-form.html";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee){

        // save the employee
        employeeService.save(employee);

        // use a redirect to prevent duplicate savings
        return "redirect:/employees/list";
    }

    @GetMapping("/showUpdateForm")
    public String showUpdateForm(@RequestParam("employeeId") int id, Model theModel){

        // get the employee from the service
        Employee employee = employeeService.findById(id);

        // set employee in the model to prepopulate the form (placeholders)
        theModel.addAttribute("employee",employee);

        // send over to the form
        return "employees/employee-form";
    }
    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int id){

        // delete the employee
        employeeService.deleteById(id);

        // redirect to employee list
        return "redirect:/employees/list";
    }


    @GetMapping("/search")
    public String search(@RequestParam("firstName") String firstName, Model model) {
        List<Employee> employeeList = employeeService.findByFirstNameContaining(firstName);
        model.addAttribute("employees", employeeList);
        return "employees/search-employees";
    }

    @GetMapping("/list/sort")
    public String sortEmployees(@RequestParam String sortField, @RequestParam String sortDirection, Model model){

        List<EmployeeDTO> employeeList = employeeService.findAllSortedDTO(sortField, Sort.Direction.fromString(sortDirection));
        model.addAttribute("employees",employeeList);
        return "employees/list-employees";

    }

}
