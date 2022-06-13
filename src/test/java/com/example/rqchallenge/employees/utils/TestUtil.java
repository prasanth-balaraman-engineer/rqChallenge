package com.example.rqchallenge.employees.utils;

import com.example.rqchallenge.employees.Employee;

import java.util.List;

public class TestUtil {
    private static final List<Employee> employees = List.of(
            new Employee("01", "emp01", 1000, 20),
            new Employee("02", "emp02", 2000, 21),
            new Employee("03", "emp03", 3000, 22),
            new Employee("04", "emp04", 4000, 23),
            new Employee("05", "emp05", 5000, 24),
            new Employee("06", "emp06", 6000, 25),
            new Employee("07", "emp07", 7000, 26),
            new Employee("08", "emp08", 8000, 27),
            new Employee("09", "emp09", 9000, 28),
            new Employee("10", "emp10", 10000, 29),
            new Employee("11", "emp11", 11000, 30),
            new Employee("12", "emp12", 12000, 31),
            new Employee("13", "emp13", 13000, 32),
            new Employee("14", "emp14", 14000, 33)
    );

    public static List<Employee> getTestEmployees() {
        return employees;
    }
}
