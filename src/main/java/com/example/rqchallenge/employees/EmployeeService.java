package com.example.rqchallenge.employees;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Slf4j
public class EmployeeService {
    @Autowired
    private EmployeeRepository repository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<Employee> getAllEmployees() {
        List<Employee> employees = repository.findAll();
        log.info("Found {} employees.", employees.size());
        return employees;
    }

    public Employee createEmployee(Map<String, Object> employeeInput) {
        Employee employee = objectMapper.convertValue(employeeInput, Employee.class);
        employee.setId(UUID.randomUUID().toString());
        repository.save(employee);
        log.info("Created employee {}.", employee);
        return employee;
    }

    public Employee getEmployeeById(String id) {
        Employee employee = repository.findById(id).get();
        log.info("Found employee {} for id {}.", employee, id);
        return employee;
    }

    public Integer getHighestSalaryOfEmployees() {
        Employee employee = repository.findFirstByOrderBySalaryDesc();
        log.info("Found employee with highest salary {}", employee);
        return employee.getSalary();
    }

    public List<Employee> getEmployeesByNameSearch(String searchString) {
        List<Employee> employees = repository.findByNameContainsIgnoreCase(searchString);
        log.info("Found {} employees containing string {}", employees.size(), searchString);
        return employees;
    }

    public List<String> getTopTenHighestEarningEmployeeNames() {
        List<Employee> employees = repository.findFirst10ByOrderBySalaryDesc();
        log.info("Found top {} highest earning employees", employees.size());
        return employees.stream().map(Employee::getName).collect(Collectors.toList());
    }

    public String deleteEmployeeById(String id) {
        Employee employee = getEmployeeById(id);
        repository.deleteById(id);
        return employee.getName();
    }
}
