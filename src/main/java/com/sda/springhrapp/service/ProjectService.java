package com.sda.springhrapp.service;

import com.sda.springhrapp.model.Project;
import com.sda.springhrapp.repository.ProjectRepositoryIf;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProjectService {
    @Autowired
    private ProjectRepositoryIf projectRepositoryIf;

    public Project saveProject(Project project)
    {
        projectRepositoryIf.save(project);
        log.info("Project saved.");
        return project;
    }

    public List<Project> findAllProjectWithBudget(double x)
    {
       return projectRepositoryIf.findAllByBudget(x);
    }
}
