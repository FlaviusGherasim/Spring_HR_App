package com.sda.springhrapp.repository;

import com.sda.springhrapp.model.Employee;
import com.sda.springhrapp.model.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
@Component
public interface EmployeeRepositoryIf extends CrudRepository<Employee, Integer> {
    Integer deleteEmployeeBySalaryIsBetween(Integer x, Integer y);
    List<Employee> findEmployeeByProjectsEquals(Set<Project> projectSet);
}
