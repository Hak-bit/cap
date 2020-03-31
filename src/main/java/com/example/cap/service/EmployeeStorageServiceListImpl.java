package com.example.cap.service;

import com.example.cap.exception.EmployeeNotFoundException;
import com.example.cap.model.Employee;
import java.util.ArrayList;
import java.util.List;

public class EmployeeStorageServiceListImpl implements EmployeeStorageService {

    private List<Employee> employeeStore = new ArrayList<>();

    public Employee store(Employee employee) {
        employeeStore.add(employee);

        return employee;
    }

    public Employee findById(int id) {
        for (int i = 0; i < employeeStore.size(); i++) {
            Employee emp = employeeStore.get(i);
            if (emp.getId() == id) {
                return emp;
            }
        }

        /*employeeStore.stream()
            .filter(e -> e.getId() == id)
            .findFirst()
            .orElseThrow(() -> new EmployeeNotFoundException(String.format("Employee with id=%d not found", id)));*/

        throw new EmployeeNotFoundException(String.format("Employee with id=%d not found", id));
    }
}
