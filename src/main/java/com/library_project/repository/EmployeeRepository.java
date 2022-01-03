package com.library_project.repository;

import com.part2.models.soap.emp.Employee;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Innova on 02-10-2017.
 */
@Component
public class EmployeeRepository {

    private static final Map<Integer, Employee> emps = new HashMap<>();

    @PostConstruct
    public void init(){
        Employee emp1 = new Employee();
        emp1.setId(1);
        emp1.setUsername("Suzi");
        emp1.setUserPassword("password");
        emp1.setUserEmail("suzi@com.innova.com");
        emps.put(emp1.getId(), emp1);
    }

    public Employee findEmployee(int id){
        return emps.get(id);
    }
}

