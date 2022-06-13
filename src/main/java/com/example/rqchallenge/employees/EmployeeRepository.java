package com.example.rqchallenge.employees;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface EmployeeRepository extends JpaRepository<Employee, String> {
    Employee findFirstByOrderBySalaryDesc();
    List<Employee> findFirst10ByOrderBySalaryDesc();
    List<Employee> findByNameContainsIgnoreCase(String searchString);
}
