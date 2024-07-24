package tms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import tms.dto.DataTablesResponse;
import tms.model.Department;
import tms.repository.DepartmentRepository;
import tms.service.DepartmentService;

@Controller
public class DepartmentController {
    
    @Autowired
    DepartmentService departmentService;
    @Autowired
    DepartmentRepository departmentRepository;

    // Display list of department in thymeleaf
    /*@GetMapping("/departments") 
    public String viewHomePage(Model model){
        model.addAttribute("listDepartment", departmentService.getAllDepartment());
        return "department";
    }*/
    
    // Display list of department
    @GetMapping("/departments/datatable") 
    public ResponseEntity<?> GetDepartments(){
        // inilize model object
		List<Department> obj = departmentService.getAllDepartment();
		
        DataTablesResponse data = new DataTablesResponse(1,(!obj.isEmpty()? obj.size() :0),(!obj.isEmpty()? obj.size() :0),obj);
	    return ResponseEntity.status(HttpStatus.OK)
        .body(data);
    }

    @PostMapping("/departments")
    public ResponseEntity<Department> saveDepartment(@RequestBody Department department){
        // save department to database
        departmentService.saveDepartment(department);
        return ResponseEntity.status(HttpStatus.OK)
        .body(departmentService.getDepartmentById(1L));
    }

    @PutMapping("/departments/{id}")
    public ResponseEntity<Department> saveDepartment(@PathVariable Long id, @RequestBody Department department){
        //Optional<Department> dept = departmentRepository.findById(id);
        department.setId(id);
        // save department to database
        departmentService.saveDepartment(department);

        return ResponseEntity.status(HttpStatus.OK)
        .body(departmentService.getDepartmentById(1L));
    }

    @DeleteMapping("/departments/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable long id){
        departmentService.deleteDepartmentById(id);
        return ResponseEntity.status(HttpStatus.OK).body(1);
    }
    
}
