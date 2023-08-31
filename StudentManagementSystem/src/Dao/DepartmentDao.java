package Dao;

import Model.Department;

import java.util.List;

public interface DepartmentDao {
    boolean addDepartment(Department department);

    List<Department> getAllDepartments();

    Department getDepartmentName(String name);

    Department getDepartmentById(Integer id);

    Boolean updateDepartment(Department department);
    Boolean deleteDepartment(Integer id);


}
