package com.sda.springhrapp.repository;

import com.sda.springhrapp.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface EmployeeRepositoryIf extends CrudRepository<Employee, Integer> {
    List<Employee> deleteEmployeeBySalaryIsBetween(Integer x, Integer y);
    List<Employee> findAll();

    //todo Make 2 methods, one with firstName & lastName, one with email, phoneNumber
    //firstName & lastName return a list as they are not unique identifiers
    //email, phoneNumber return a single Employee as they ARE unique identifiers
    List<Employee> findEmployeeByFirstNameAndLastName(String firstName, String lastName);
    List<Employee> findEmployeeByEmailOrPhoneNumber(String email, String phoneNumber);

    List<Employee> findAllByDepartment_Id(Integer id);

    List<Employee> findAllEmployeesByDepartment_Name(String departmentName);

    Employee findEmployeeByAccount_CreationDate(Date creationDate);

    List<Employee> findAllByProjects_Name(String projectName);
//    List<Employee> findEmployeesByProjectsId(Integer projectId);

}
