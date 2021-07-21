package com.sda.springhrapp.service;

import com.sda.springhrapp.exception.EmployeeServiceException;
import com.sda.springhrapp.exception.ProjectServiceException;
import com.sda.springhrapp.model.Account;
import com.sda.springhrapp.model.Employee;
import com.sda.springhrapp.model.Project;
import com.sda.springhrapp.repository.AccountRepositoryIf;
import com.sda.springhrapp.repository.EmployeeRepositoryIf;
import com.sda.springhrapp.repository.ProjectRepositoryIf;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmployeeService {
    @Autowired
    private EmployeeRepositoryIf employeeRepositoryIf;
    @Autowired
    private AccountRepositoryIf accountRepositoryIf;
    @Autowired
    private ProjectRepositoryIf projectRepositoryIf;

    public Employee saveEmployee(Employee employee) {
        //todo custom EmployeeServiceException when save meets exceptions
        if (employee.getId() == null) {
            Account newAccount = new Account(Date.valueOf(LocalDate.now()));
            employee.setAccount(accountRepositoryIf.save(newAccount));
        } else {
            employee.setAccount(employee.getAccount());
        }
        Employee employeeSaved = employeeRepositoryIf.save(employee);
        log.info("Employee has been saved.");
        return employeeSaved;
    }
    //todo Add new method updateEmployee
    // Maybe using something like this Employee existingEmployee= employeeRepositoryIf.findById(employee.getId());

    public List<Employee> deleteEmployeesWithSalariesBetween(Integer x, Integer y) {
        return employeeRepositoryIf.deleteEmployeeBySalaryIsBetween(x, y);
    }

    public List<Employee> findAll() {
        return employeeRepositoryIf.findAll();
    }

//    @Query(value = "SELECT Employee FROM Employee e where " +
//            "e.firstName is not null or e.lastName is not null or" +
//            " e.email is not null or e.phoneNumber is not null")
//    public Employee findSpecificEmployee(@Param("firstName") String firstName,
//                                         @Param("lastName") String lastName,
//                                         @Param("email") String email,
//                                         @Param("phoneNumber") String phoneNumber)
//    {
//
//       return employeeRepositoryIf.findEmployeeByFirstNameOrLastNameOrEmailOrPhoneNumber(Sort.unsorted());
//    }

    public List<Employee> findSpecificEmployeeByFirstAndLastName(String firstName, String lastName) {
        return employeeRepositoryIf.findEmployeeByFirstNameAndLastName(firstName, lastName);

    }

    public List<Employee> findSpecificEmployeeByPhoneNumberOrEmail(String email, String phoneNumber) {
        return employeeRepositoryIf.findEmployeeByEmailOrPhoneNumber(email, phoneNumber);
    }

    private boolean isBlank(String condition) {
        return condition != null && !condition.isEmpty();
    }

    public List<Employee> findEmployeesByDepartmentName(String departmentName) {
        return employeeRepositoryIf.findAllEmployeesByDepartment_Name(departmentName);
    }

    public Employee findEarliestEmployee() {
        List<Employee> employeeList = employeeRepositoryIf.findAll();
        List<Date> dateList = new ArrayList<>();
        for (Employee employee : employeeList) {
            Date accountDate = employee.getAccount().getCreationDate();
            dateList.add(accountDate);
        }
        Date earliestEmployee = Collections.min(dateList);

        return employeeRepositoryIf.findEmployeeByAccount_CreationDate(earliestEmployee);
    }

    public List<Employee> findEmployeesByProjectName(String projectName)
    {
        List<Employee> employeeList= employeeRepositoryIf.findAllByProjects_Name(projectName);
        if(!employeeList.isEmpty())
        {
            return employeeList;
        }
        else {
            throw new ProjectServiceException("No employees.");
        }
    }

//    public List<Employee> findEmployeesByProjectName(String projectName) {
//        Project project = projectRepositoryIf.findProjectByName(projectName);
//        if (project.getName().equals(projectName)) {
//            return employeeRepositoryIf.findEmployeesByProjectsId(project.getId());
//        } else {
//            return null;
//        }
//    }

    public void assignEmployeeToProject(Integer employeeId, Integer projectId) {
        Employee employee = employeeRepositoryIf.findById(employeeId).orElseThrow(() -> new EmployeeServiceException("Employee not found."));
        Project project = projectRepositoryIf.findById(projectId).orElseThrow(() -> new ProjectServiceException("Project not found"));

        employee.getProjects().add(project);
        project.getEmployees().add(employee);
        employeeRepositoryIf.save(employee);
        projectRepositoryIf.save(project);

//          EITHER THIS OR .orElseThrow
//        if (project.isPresent() && employee.isPresent()) {
//            employee.get().getProjects().add(project.get());
//            project.get().getEmployees().add(employee.get());
//            employeeRepositoryIf.save(employee.get());
//            projectRepositoryIf.save(project.get());
//        }

    }
    public List<Employee> employeesOrderedByAge()
    {
       List<Employee> employeeList = employeeRepositoryIf.findAll();
       return employeeList.stream().sorted(((o1, o2) -> o2.getDateOfBirth().compareTo(o1.getDateOfBirth()))).collect(Collectors.toList());
    }

    public Employee employeeWithHighestProjectCount()
    {
        List<Employee> employeeList=employeeRepositoryIf.findAll();
        List<Integer> sizeList = new ArrayList<>();
        for (Employee employee:employeeList)
        {
            sizeList.add(employee.getProjects().size());
        }
        int count= sizeList.indexOf(Collections.max(sizeList));
        return employeeList.get(count);
    }

//    public List<Employee> employeeWithHighestProjectCount2()
//    {
//        HashMap<String, Integer> employeesMap= new HashMap<>();
//        List<Employee> employeeList=employeeRepositoryIf.findAll();
//
//        for (Employee employee:employeeList)
//        {
//            employeesMap.put(employee.getFirstName()+employee.getLastName(),employee.getProjects().size());
//        }
//    }

}
