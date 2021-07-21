package com.sda.springhrapp.service;

import com.sda.springhrapp.exception.ProjectServiceException;
import com.sda.springhrapp.model.Employee;
import com.sda.springhrapp.model.Project;
import com.sda.springhrapp.repository.EmployeeRepositoryIf;
import com.sda.springhrapp.repository.ProjectRepositoryIf;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProjectService {
    @Autowired
    private ProjectRepositoryIf projectRepositoryIf;
    @Autowired
    private EmployeeRepositoryIf employeeRepositoryIf;

    public Project saveProject(Project project) {
        projectRepositoryIf.save(project);
        log.info("Project saved.");
        return project;
    }

    public List<Project> findAllProjectWithBudget(double x) {
        return projectRepositoryIf.findAllByBudget(x);
    }

    public Project findProjectById(Integer id) {
        return projectRepositoryIf.findById(id).orElseThrow(
                () -> new ProjectServiceException("Something went wrong with finding projects."));

//        if (projectOptional.isPresent()) {
//            return projectOptional.get();
//        } else {
//            throw new ProjectServiceException("Something went wrong with finding projects.");
//        }
    }

    public Project findProjectByName(String projectName)
    {
        return projectRepositoryIf.findProjectByName(projectName);
    }

    public Set<String> findByDepartmentName(String departmentName) {
        List<Employee> employeeList = employeeRepositoryIf.findAllEmployeesByDepartment_Name(departmentName);
        Set<Project> projectSet= new HashSet<>();
        employeeList.stream().map(i->i.getProjects()).forEach(i->projectSet.addAll(i));

//        for (Employee employee: employeeList)
//        {
//            projectSet.addAll(employee.getProjects());
//        }

        return projectSet.stream().map(i->i.getName()).collect(Collectors.toSet());
    }
}
