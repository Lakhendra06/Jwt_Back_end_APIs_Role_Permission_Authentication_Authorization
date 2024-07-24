package tms.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import tms.model.Role;
import tms.repository.RoleRepository;
import tms.service.RoleService;

@Controller
public class RoleController {

    @Autowired
    RoleService roleService;
    
    @Autowired
    RoleRepository roleRepository;
    
    // Display list of role in thymeleaf
    /*@GetMapping("/roles") 
    public String viewHomePage(Model model){
        model.addAttribute("listRole", roleService.getAllRole());
        return "role";
    }*/
    
    // Display list of role
    @GetMapping("/roles/datatable") 
    public ResponseEntity<?> Getroles(){
        // inilize model object
		List<Role> obj = roleService.getAllRole();
		
        DataTablesResponse data = new DataTablesResponse(1,(!obj.isEmpty()? obj.size() :0),(!obj.isEmpty()? obj.size() :0),obj);
	    return ResponseEntity.status(HttpStatus.OK)
        .body(data);
    }

    @PostMapping("/roles")
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        // save role to database
        
        role.setCreated_at(LocalDateTime.now().format(DateTimeFormatter.ofPattern(("yyyy-MM-dd hh:mm a"))));

        roleService.saveRole(role);
        return ResponseEntity.status(HttpStatus.OK)
        .body(roleService.getRoleById(1L));
    }

    @PutMapping("/roles/{id}")
    public ResponseEntity<Role> saveRole(@PathVariable Long id, @RequestBody Role role){
        //Optional<Role> dept = roleRepository.findById(id);
        role.setId(id);
        // save role to database
        roleService.saveRole(role);

        return ResponseEntity.status(HttpStatus.OK)
        .body(roleService.getRoleById(1L));
    }

    @DeleteMapping("/roles/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable long id){
        roleService.deleteRoleById(id);
        return ResponseEntity.status(HttpStatus.OK).body(1);
    }
}
