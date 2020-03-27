package com.example.cap.service;

import com.example.cap.model.Employee;
import java.util.concurrent.atomic.AtomicInteger;

//SOLID
public class EmployeeService {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    private final EmployeeStorageService employeeStorageService;

    public EmployeeService(EmployeeStorageService employeeStorageService) {
        this.employeeStorageService = employeeStorageService;
    }

    //testing in isolation
    public void createEmployee(String name, String dept) {
        int id = atomicInteger.addAndGet(100);
        Employee employee = new Employee(id, name, dept);

        employeeStorageService.store(employee);
    }
}
