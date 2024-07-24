package tms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import tms.model.Employee;
import tms.service.EmployeeService;

@RestController
public class UserController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<Employee> userProfile() {
        return ResponseEntity.ok(employeeService.getUser());
    }

    @GetMapping("/string")
    public ResponseEntity<?> getString(){
        return ResponseEntity.ok("It is String.");
    }
}
