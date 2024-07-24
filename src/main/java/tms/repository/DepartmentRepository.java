package tms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tms.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository <Department, Long>{
    
    //List<Department> findByDepartment(String department);
    
}
