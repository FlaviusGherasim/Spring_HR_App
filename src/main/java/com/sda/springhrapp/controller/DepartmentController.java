package com.sda.springhrapp.controller;

import com.sda.springhrapp.model.Department;
import com.sda.springhrapp.model.Employee;
import com.sda.springhrapp.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/humanresources/api")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/departments")
    public ResponseEntity<String> findDepartmentsWithEmployees(@RequestBody List<Employee> employeeList) // Don't think it's alright, can't think of a different way to use a list
    {
        Department department=departmentService.findDepartmentWithEmployeeList(employeeList);
        log.info("Department found.");
        log.debug(departmentService.toString());
        return new ResponseEntity<>("Department found "+ department, HttpStatus.OK);
    }

    @PostMapping("/departments")
    public ResponseEntity<String> createAccount(@RequestBody Department department)
    {
        departmentService.saveDepartment(department);
        log.info(department.toString());
        return new ResponseEntity<>(department.toString(), HttpStatus.CREATED);
    }

    @DeleteMapping("/departments")
    @Transactional
    public ResponseEntity<String> deleteDepartment(@RequestParam(value = "name", required = false) String name,
                                                   @RequestParam(value = "id", required = false) Integer id) {

        if ((!(isBlank(name)) && id != null) || (isBlank(name) && id == null)) {
            throw new IllegalArgumentException("Please provide one of the options. Name or Id.");
        } else if (!isBlank(name)) {
            departmentService.deleteByName(name);
        } else {
            departmentService.deleteById(id);
        }
        return new ResponseEntity<>("Department with id " + id + " has been removed.", HttpStatus.OK);
    }

    private boolean isBlank(String name) {
        return name == null || name.isEmpty();
    }

    @PutMapping("/departments")
    public ResponseEntity<Department> updateDepartment(@RequestBody Department department)
    {
        Department updatedDepartment= departmentService.saveDepartment(department);
        return ResponseEntity.ok(updatedDepartment);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> catchIllegalArgumentException(IllegalArgumentException e) {
        return new ResponseEntity<>("Illegal arguments " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
