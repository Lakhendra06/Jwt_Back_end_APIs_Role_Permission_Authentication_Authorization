package tms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tms.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository <Employee, Long>{
    
    List<Employee> findByEmail(String email);
    
    @Query(value = "SELECT count(*) FROM employees",nativeQuery = true)
	public int getEmployeesCount();

    Employee findByUserName(String username);

}
