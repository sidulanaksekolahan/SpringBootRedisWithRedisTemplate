package com.example.service;

import com.example.handler.EmployeeNotFoundExc;
import com.example.model.Employee;
import com.example.model.EmployeeDto;
import com.example.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    @CachePut(value = "users", key = "#result.id")
    public Employee saveEmployee(EmployeeDto employeeDto) {
        Employee user = new Employee(employeeDto.getFirstName(), employeeDto.getLastName(), employeeDto.getEmail());
        return employeeRepository.save(user);
    }

    @Override
    @Cacheable(value = "users", key = "#id")
    public Employee getEmployeeById(Integer id) {
        Optional<Employee> employee = employeeRepository.findById(id);

        return employee.orElse(null);
    }

    @Override
    public List<Employee> getAllEmployees() {

        List<Employee> employees = new ArrayList<>();

        employeeRepository.findAll().forEach(employees::add); // Add all employees to the list

        return employees;
    }

    @Override
    @CacheEvict(value = "users", key = "#id")
    public void deleteEmployeeById(Integer id) {
        Employee user = getEmployeeById(id);
        if (user != null) {
            employeeRepository.delete(user);
        } else {
            throw new EmployeeNotFoundExc("employee with id: " + id + " not found!");
        }
    }

    @Override
    @CachePut(value = "users", key = "#id")
    public Employee updateEmployee(Integer id, EmployeeDto employeeDto) {
        Employee employee = getEmployeeById(id);
        if (employee == null) {
            throw new EmployeeNotFoundExc("employee with id: " + id + " not found!");
        }

        // update the data
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());

        // save and return the user
        return employeeRepository.save(employee);
    }
}
