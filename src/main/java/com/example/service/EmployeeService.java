package com.example.service;

import com.example.model.Employee;
import com.example.model.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    Employee saveEmployee(EmployeeDto employeeDto);

    Employee getEmployeeById(Integer id);

    List<Employee> getAllEmployees();

    void deleteEmployeeById(Integer id);

    Employee updateEmployee(Integer id, EmployeeDto employeeDto);
}
