package com.sda.springhrapp.repository;

import com.sda.springhrapp.model.Department;
import com.sda.springhrapp.model.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DepartmentRepositoryIf extends CrudRepository<Department,Integer> {
    Integer deleteDepartmentByName(String name);
    Integer deleteDepartmentById(Integer id);
    Department findDepartmentByEmployeeList(List<Employee> employeeList);
}
