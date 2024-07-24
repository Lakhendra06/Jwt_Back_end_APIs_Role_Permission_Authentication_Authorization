package tms.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/") 
    public String viewPage(){
        //return "auth-login-basic";
        return "index";
    }

    @GetMapping("/login") 
    public String viewLoginPage(){
        
        return "login";
    }

    @GetMapping("/logout") 
    public String viewLogoutPage(){
        return "logout";
    }

    @GetMapping("/under-maintenance") 
    public String viewPage2(){
        return "pages-misc-under-maintenance";
    }
    
    // will return only with @RestController
    @GetMapping("/getCurrentDate")
    public String GetDate(){

        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(("yyyy-MM-dd hh:mm a")));
        //return "String";
    }
}
