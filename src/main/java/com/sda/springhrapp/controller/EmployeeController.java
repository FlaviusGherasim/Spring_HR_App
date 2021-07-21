package com.sda.springhrapp.controller;

import com.sda.springhrapp.model.Employee;
import com.sda.springhrapp.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "humanresources/api")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("employees/department")
    public ResponseEntity<String> findEmployeesByDepartment(@RequestParam(value = "departmentName") String departmentName) {
        return new ResponseEntity<>(employeeService.findEmployeesByDepartmentName(departmentName).toString(), HttpStatus.OK);
    }

    @GetMapping("/mployees")
    public ResponseEntity<String> findAll() {
        List<Employee> employeeList = employeeService.findAll();
        log.info("Employees found.");
        log.debug(employeeList.toString());
        return new ResponseEntity<>(employeeList.toString(), HttpStatus.OK);
    }

    @GetMapping("/employees/find")
    public ResponseEntity<String> findSpecificEmployee(@RequestParam(value = "firstName", required = false) String firstName,
                                                       @RequestParam(value = "LastName", required = false) String lastName,
                                                       @RequestParam(value = "email", required = false) String email,
                                                       @RequestParam(value = "phoneNumber", required = false) String phoneNumber) {

//        List<Employee> foundEmployee;
//        foundEmployee = employeeService.findSpecificEmployee(firstName, lastName, email, phoneNumber, id);
//        return new ResponseEntity<>(foundEmployee.toString(), HttpStatus.OK);
        if (isBlank(firstName) && isBlank(lastName)) {
            List<Employee> employeeList;
            employeeList = employeeService.findSpecificEmployeeByFirstAndLastName(firstName, lastName);
            return new ResponseEntity<>(employeeList.toString(), HttpStatus.OK);
        } else if (isBlank(email) || isBlank(phoneNumber)) {
            List<Employee> employeeList;
            employeeList = employeeService.findSpecificEmployeeByPhoneNumberOrEmail(email, phoneNumber);
            return new ResponseEntity<>(employeeList.toString(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Something went wrong, check your parameters", HttpStatus.BAD_REQUEST);
        }
    }

    private boolean isBlank(String condition) {
        return (condition != null && !condition.isEmpty());
    }

    @GetMapping("/employees/earliestEmployee")
    public ResponseEntity<String> findEarliestEmployeeByAccount()
    {
        Employee employee= employeeService.findEarliestEmployee();
        return new ResponseEntity<>("earliest employee is "+ employee.toString(), HttpStatus.OK);

    }
//    @GetMapping("/employees/employeesByProjectName")
//    public ResponseEntity<String> findEmployeesByProjectName(@RequestParam String projectName)
//    {
//        List<Employee> employeeList= employeeService.findEmployeesByProjectName(projectName);
//        return new ResponseEntity<>("List is "+ employeeList.toString(), HttpStatus.OK);
//
//    }

    @GetMapping("/employees/employeesByProjectName")
    public ResponseEntity<String> findEmployeesByProjectName(@RequestParam String projectName)
    {
        List<Employee> employeeList= employeeService.findEmployeesByProjectName(projectName);
        return new ResponseEntity<>("List is "+ employeeList.toString(), HttpStatus.OK);

    }

    @GetMapping("/employees/employeeWithHighestProjectCount")
    public ResponseEntity<String> findEmployeeWithHighestProjectCount()
    {
       Employee employee= employeeService.employeeWithHighestProjectCount();
        return new ResponseEntity<>("Employee with highest project count is "+ employee.toString(), HttpStatus.OK);

    }

@GetMapping("/employees/employeesOrderedByAge")
    public ResponseEntity<String> orderEmployeesByAgeInReverse()
    {
        List<Employee> employeeList= employeeService.employeesOrderedByAge();
        return new ResponseEntity<>("List is "+ employeeList.toString(), HttpStatus.OK);
    }


    @PostMapping("/employees")
    public ResponseEntity<String> createEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        log.info(employee.toString());
        return new ResponseEntity<>(employee.toString(), HttpStatus.CREATED);
    }

    @DeleteMapping("/employees/deleteBySalaryBetween")
    @Transactional
    public ResponseEntity<String> deleteEmployee(@RequestParam(value = "minSalary") Integer minSalary,
                                                 @RequestParam(value = "maxSalary") Integer maxSalary) {

        List<Employee> deletedEmployee;
        deletedEmployee = employeeService.deleteEmployeesWithSalariesBetween(minSalary, maxSalary);
        return new ResponseEntity<>("Employees with salary between the specific values were isekaied: " + deletedEmployee, HttpStatus.OK);
    }

    @PutMapping("/employees")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        Employee updatedEmployee = employeeService.saveEmployee(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    @PutMapping("/employees/assignProjects")
    public ResponseEntity<String> assignEmployeeToProject(@RequestParam(value = "employeeId") Integer employeeId,
                                                          @RequestParam(value = "projectId") Integer projectId) {
        employeeService.assignEmployeeToProject(employeeId, projectId);
        return new ResponseEntity<>("Employee with id " + employeeId + " assigned to project with id " + projectId, HttpStatus.OK);
    }

}
