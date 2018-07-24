package com.arun.service;

import com.arun.model.Employee;
import org.springframework.stereotype.Service;

/**
 * Created by Adwiti on 7/23/2018.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private Employee employee = new Employee(10, "Arun");

    @Override
    public Employee getEmployee(Integer id) {
        return employee;
    }
}
