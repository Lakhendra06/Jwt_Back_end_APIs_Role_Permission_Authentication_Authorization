package tms.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import tms.model.Authority;
import tms.model.Employee;
import tms.repository.EmployeeRepository;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByUserName(username);
        if(employee == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(employee.getUserName(), employee.getPassword(), getAuthority(employee.getAuthorities()));
    }

    private List<SimpleGrantedAuthority> getAuthority(Set<Authority> authorities) {
        return authorities.stream().map(authoritie -> new SimpleGrantedAuthority(authoritie.getName())).collect(Collectors.toUnmodifiableList());
    }
}