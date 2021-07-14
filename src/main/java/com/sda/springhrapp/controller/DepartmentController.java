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
    public ResponseEntity<String> findAllDepartments()
    {
        departmentService.findAll();
        log.info("Department found.");
        log.debug(departmentService.toString());
        return new ResponseEntity<>("Departments found ", HttpStatus.OK);
    }

    @PostMapping("/departments")
    public ResponseEntity<String> createDepartment(@RequestBody Department department) {
        Department response = departmentService.saveDepartment(department);
        if (response.getName() == null || response.getName().isEmpty()) {
            log.warn("Department got saved but it's empty");
            return new ResponseEntity<>("Department got saved but it's empty", HttpStatus.BAD_REQUEST);

        } else {
            log.info(response.toString());
            return new ResponseEntity<>(response.toString(), HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/departments")
    @Transactional
    public ResponseEntity<String> deleteDepartment(@RequestParam(value = "name", required = false) String name,
                                                   @RequestParam(value = "id", required = false) Integer id) {

        Integer resultOfDelete;
        if ((!(isBlank(name)) && id != null) || (isBlank(name) && id == null)) {
            throw new IllegalArgumentException("Please provide one of the options. Name or Id.");
        } else if (!isBlank(name)) {
            resultOfDelete = departmentService.deleteByName(name);
        } else {
            resultOfDelete = departmentService.deleteById(id);
        }
        if (resultOfDelete == 1) {
            return new ResponseEntity<>("Department with id " + id + " has been removed.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Department was not deleted because it does not exist or something went wrong.", HttpStatus.BAD_REQUEST);
        }
    }

    private boolean isBlank(String name) {
        return name == null || name.isEmpty();
    }

    @PutMapping("/departments")
    public ResponseEntity<Department> updateDepartment(@RequestBody Department department) {
        Department updatedDepartment = departmentService.saveDepartment(department);
        return ResponseEntity.ok(updatedDepartment);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> catchIllegalArgumentException(IllegalArgumentException e) {
        return new ResponseEntity<>("Illegal arguments " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
