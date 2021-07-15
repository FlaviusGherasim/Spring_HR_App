package com.sda.springhrapp.repository;

import com.sda.springhrapp.model.Department;
import com.sda.springhrapp.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepositoryIf extends CrudRepository<Department,Integer> {
    Integer deleteDepartmentByName(String name);
    Integer deleteDepartmentById(Integer id);
    List<Department> findAll();
    Department findByName(String name);
    Department findByIdIs(Integer id);
}
