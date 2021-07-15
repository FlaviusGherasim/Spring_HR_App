package com.sda.springhrapp.controller;

import com.sda.springhrapp.model.Account;
import com.sda.springhrapp.model.Employee;
import com.sda.springhrapp.model.Project;
import com.sda.springhrapp.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@Slf4j
@RequestMapping(value = "humanresources/api")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
//todo FindAll Employees, find specific employees.


    @GetMapping("/employees")
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
                                                       @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
                                                       @RequestParam(value = "departmentId", required = false) Integer id) {

        Employee foundEmployee;
        foundEmployee = employeeService.findSpecificEmployee(firstName, lastName, email, phoneNumber, id);
        return new ResponseEntity<>(foundEmployee.toString(), HttpStatus.OK);
    }

    //todo Assign Employee to projects, add ID for employees and project
    @PostMapping("/employees")
    public ResponseEntity<String> createEmployee(@RequestBody Employee employee) {
        employeeService.saveEmployee(employee);
        log.info(employee.toString());
        return new ResponseEntity<>(employee.toString(), HttpStatus.CREATED);
    }

    @DeleteMapping("/employees")
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

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> catchIllegalArgumentException(IllegalArgumentException e) {
        return new ResponseEntity<>("Illegal arguments " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
