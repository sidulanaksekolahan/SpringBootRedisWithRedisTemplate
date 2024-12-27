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

    /**
     * @CachePut is used to update data in the cache when there is any update in the source database.
     * The @CachePut(value = "employees", key = "#result.id") annotation is used to update the cache
     * with the returned value of the method. Here, the cache is named employees.
     * The key used in the cache will be the ID of the Employee object.
     * For example: employees::1
     *
     * key = "#result.id" - This is a SpEL (Spring Expression Language) expression.
     * - #result refers to the value returned by the method.
     * - #result.id accesses the id property of the returned object.
     * - The key used in the cache will be the ID of the Employee object.
     */
    @Override
    @CachePut(value = "employees", key = "#result.id")
    public Employee saveEmployee(EmployeeDto employeeDto) {
        Employee user = new Employee(employeeDto.getFirstName(), employeeDto.getLastName(), employeeDto.getEmail());

        return employeeRepository.save(user);
    }

    /**
     * @Cacheable is employed to fetch data from the database, storing it in the cache. Upon future invocations,
     * the method retrieves the cached value directly, eliminating the need to execute the method again.
     */
    @Override
    @Cacheable(value = "employees", key = "#id")
    public Employee getEmployeeById(Integer id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundExc("employee with id: " + id + " not found!"));
    }

    @Override
    public List<Employee> getAllEmployees() {

        return employeeRepository.findAll();
    }

    /**
     * @CacheEvict is used for removing stale or unused data from the cache.
     */
    @Override
    @CacheEvict(value = "employees", key = "#id")
    public void deleteEmployeeById(Integer id) {
        Employee user = getEmployeeById(id);
        employeeRepository.delete(user);
    }

    /**
     * @CachePut is used to update data in the cache when there is any update in the source database.
     */
    @Override
    @CachePut(value = "employees", key = "#id")
    public Employee updateEmployee(Integer id, EmployeeDto employeeDto) {
        Employee employee = getEmployeeById(id);

        // update the data
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());

        // save and return the user
        return employeeRepository.save(employee);
    }
}
