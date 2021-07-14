package com.sda.springhrapp.repository;

import com.sda.springhrapp.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepositoryIf extends CrudRepository<Employee, Integer> {
    Integer deleteEmployeeBySalaryIsBetween(Integer x, Integer y);
}
