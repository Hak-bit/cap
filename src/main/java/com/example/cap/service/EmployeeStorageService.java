package com.example.cap.service;

import com.example.cap.model.Employee;
import java.util.List;

public interface EmployeeStorageService {

    //CRUD
    //CREATE
    Employee store(Employee employee);

    //READ
    Employee findById(int id);

    List<Employee> findAll();

    //DELETE
    void delete(int id);
}
