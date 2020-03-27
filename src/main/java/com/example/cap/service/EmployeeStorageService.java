package com.example.cap.service;

import com.example.cap.model.Employee;
import java.util.ArrayList;
import java.util.List;

public class EmployeeStorageService {

    private List<Employee> employeeStorage = new ArrayList<>();

    public Employee store(Employee employee) {
        employeeStorage.add(employee);

        return employee;
    }

}
