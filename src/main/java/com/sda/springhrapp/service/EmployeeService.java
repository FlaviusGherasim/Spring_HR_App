package com.sda.springhrapp.service;

import com.sda.springhrapp.model.Employee;
import com.sda.springhrapp.model.Project;
import com.sda.springhrapp.repository.EmployeeRepositoryIf;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class EmployeeService {
    @Autowired
    private EmployeeRepositoryIf employeeRepositoryIf;

    public Employee saveEmployee(Employee employee)
    {
        employeeRepositoryIf.save(employee);
        log.info("Employee has been saved.");
        return employee;
    }

    public Integer deleteEmployeesWithSalariesBetween(Integer x, Integer y)
    {
        return employeeRepositoryIf.deleteEmployeeBySalaryIsBetween(x, y);
    }

    public List<Employee> findAllEmployeesByProjects(Set<Project> projectSet)
    {
        return employeeRepositoryIf.findEmployeeByProjectsEquals(projectSet);
    }
}
