package com.employee.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private static int nextId = 1;
    private int id;
    private String name;
    private String department;
    private double salary;

    public Employee(String name, String department, double salary) {
        this.id = nextId++;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }
}
