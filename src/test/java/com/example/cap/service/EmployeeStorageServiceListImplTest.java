package com.example.cap.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.example.cap.exception.EmployeeNotFoundException;
import com.example.cap.model.Employee;
import java.util.function.Function;
import org.junit.jupiter.api.Test;

class EmployeeStorageServiceListImplTest {

    private EmployeeStorageServiceListImpl storageUnderTest = new EmployeeStorageServiceListImpl();

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

    @Test
    public void delete() {
        int empId = 200;
        storageUnderTest.store(new Employee(empId, "FN", "DEPT"));
        Employee employee = storageUnderTest.findById(empId);
        assertEquals(empId, employee.getId());

        storageUnderTest.delete(empId);

        assertThrows(
            EmployeeNotFoundException.class,
            () -> storageUnderTest.findById(empId),
            "Expected findById() to throw, but it didn't"
        );
    }

    private void test() {
        test1(storageUnderTest::findById, 100);
    }

    private void test1(Function<Integer, Employee> fx, int id) {
        fx.apply(id);
    }

    private void ref() {
        throw  new RuntimeException("Testing testing!!!");
    }

    @Test
    public void update() {
        int empId = 200;
        String name = "ChangeIt";
        storageUnderTest.store(new Employee(empId, name, "DEPT"));
        Employee employee = storageUnderTest.findById(empId);
        assertEquals(name, employee.getName());

        String nameChange = "Changed";
        Employee newEmp = new Employee(empId, nameChange, "DEPT");
        storageUnderTest.update(newEmp);
        employee = storageUnderTest.findById(empId);
        assertEquals(nameChange, employee.getName());
    }
}