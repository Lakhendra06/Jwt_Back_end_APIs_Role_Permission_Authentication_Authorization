package tms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import tms.dto.DataTablesResponse;
import tms.model.Employee;
import tms.service.EmployeeService;

@Controller
//@RequestMapping("v1")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    
    // Display list of department
    @GetMapping("/employees/count") 
    public ResponseEntity<?> getEmployeesCount(){
        // inilize model object
        int obj = employeeService.getEmployeesCount();
        return ResponseEntity.status(HttpStatus.OK)
        .body(obj);
    }
    
    // Display list of employees in thymeleaf
    /*@GetMapping("/employees") 
    public String viewHomePage(Model model){
        model.addAttribute("listEmployees", employeeService.getAllEmployees());
        return "employee/employee";
    }*/
    
    // Display list of department
    @GetMapping("/employees/datatable") 
    public ResponseEntity<?> GetDepartments(){
        // inilize model object
		List<Employee> obj = employeeService.getAllEmployees();
		
        DataTablesResponse data = new DataTablesResponse(1,(!obj.isEmpty()? obj.size() :0),(!obj.isEmpty()? obj.size() :0),obj);
	    return ResponseEntity.status(HttpStatus.OK)
        .body(data);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long id){
        // get employee from the service
        Employee employee = employeeService.getEmployeeById(id);
        return ResponseEntity.status(HttpStatus.OK).body(employee);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable long id){
        employeeService.deleteEmployeeById(id);
        return ResponseEntity.status(HttpStatus.OK).body(1);
    }
}
