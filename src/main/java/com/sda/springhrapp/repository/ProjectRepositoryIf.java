package com.sda.springhrapp.repository;

import com.sda.springhrapp.model.Employee;
import com.sda.springhrapp.model.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepositoryIf extends CrudRepository<Project, Integer> {

    List<Project> findAllByBudget(double x);
    Project findProjectByName(String projectName); // returns null
    // List<Project> findProjectsByEmployee_Name(String employeeName); //won't let me choose Employee Name
}
