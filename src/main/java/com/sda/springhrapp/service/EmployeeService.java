package com.sda.springhrapp.service;

import com.sda.springhrapp.model.Employee;
import com.sda.springhrapp.model.Project;
import com.sda.springhrapp.repository.EmployeeRepositoryIf;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class EmployeeService {
    @Autowired
    private EmployeeRepositoryIf employeeRepositoryIf;

    public Employee saveEmployee(Employee employee)
    {
        employeeRepositoryIf.save(employee);
        log.info("Employee has been saved.");
        return employee;
    }

    public List<Employee> deleteEmployeesWithSalariesBetween(Integer x, Integer y)
    {
        return employeeRepositoryIf.deleteEmployeeBySalaryIsBetween(x, y);
    }

    public List<Employee> findAll()
    {
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
    public Employee findSpecificEmployee( String firstName,String lastName,
                                          String email, String phoneNumber, Integer id)
    {
        return  employeeRepositoryIf.findEmployeeByFirstNameOrLastNameOrEmailOrPhoneNumberOrDepartmentId(firstName, lastName, email, phoneNumber, id);
    }
}
