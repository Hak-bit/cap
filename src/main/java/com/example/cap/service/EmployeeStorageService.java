package com.example.cap.service;

import com.example.cap.model.Employee;

public interface EmployeeStorageService {

    //CRUD
    //CREATE
    Employee store(Employee employee);

    //READ
    Employee findById(int id);
}
