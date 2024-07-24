package tms.service;

import java.util.List;

import tms.dto.LoginDto;
import tms.model.Employee;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    void saveEmployee(Employee employee);
    Employee getEmployeeById(Long id);
    void deleteEmployeeById(Long id);
    int getEmployeesCount();
    
    String login(LoginDto loginDto);
    Employee getUser();
}
