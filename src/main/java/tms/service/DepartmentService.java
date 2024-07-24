package tms.service;

import java.util.List;

import tms.model.Department;

public interface DepartmentService {
    List<Department> getAllDepartment();
    void saveDepartment(Department department);
    Department getDepartmentById(Long id);
    void deleteDepartmentById(Long id);
}
