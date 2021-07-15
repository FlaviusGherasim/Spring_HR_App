package com.sda.springhrapp.repository;

import com.sda.springhrapp.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepositoryIf extends CrudRepository<Employee, Integer> {
    List<Employee> deleteEmployeeBySalaryIsBetween(Integer x, Integer y);
    List<Employee> findAll();
    Employee findEmployeeByFirstNameOrLastNameOrEmailOrPhoneNumberOrDepartmentId(String firstName, String lastName, String email, String phoneNumber, Integer departmentId);
}
