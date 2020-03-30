package com.example.cap.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.cap.exception.EmployeeNotFoundException;
import com.example.cap.model.Employee;
import org.junit.jupiter.api.Test;

class EmployeeStorageServiceTest {

    private EmployeeStorageService storageUnderTest = new EmployeeStorageService();

    @Test
    void whenEmployeeNotFound_thenThrowException() {
        int empId = 404;

        EmployeeNotFoundException thrown = assertThrows(
            EmployeeNotFoundException.class,
            () -> storageUnderTest.findById(empId),
            "Expected findById() to throw, but it didn't"
        );

        assertTrue(thrown.getMessage().contains(String.format("Employee with id=%d not found", empId)));
    }

    @Test
    void whenEmployeeFound_thenEmployeeReturned() {
        int empId = 200;
        storageUnderTest.store(new Employee(empId, "FN", "DEPT"));

        Employee employee = storageUnderTest.findById(empId);

        assertEquals(empId, employee.getId());
    }
}