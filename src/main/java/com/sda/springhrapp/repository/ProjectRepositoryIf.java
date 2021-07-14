package com.sda.springhrapp.repository;

import com.sda.springhrapp.model.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepositoryIf extends CrudRepository<Project, Integer> {

    List<Project> findAllByBudget(double x);
}
