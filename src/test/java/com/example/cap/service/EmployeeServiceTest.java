package com.example.cap.service;

import static org.mockito.Mockito.only;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.cap.model.Employee;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    //SPY
    private EmployeeStorageService storageServiceMock;

    @Captor
    private ArgumentCaptor<Employee> empArgCaptor;
    private EmployeeService serviceUnderTest;

    @BeforeEach
    public void init() {
        serviceUnderTest = new EmployeeService(storageServiceMock);
    }

    @Test
    void createEmployee() {
        String name = UUID.randomUUID().toString();
        String dept = "IT";

        serviceUnderTest.createEmployee(name, dept);

        verify(storageServiceMock, only()).store(empArgCaptor.capture());
        Employee capturedEmp = empArgCaptor.getValue();

        Assertions.assertEquals(100, capturedEmp.getId());
    }

    @Test
    void createMultipleEmployees() {
        int numOfEmployees = 5;

        for (int i = 0; i < numOfEmployees; i++) {
            serviceUnderTest.createEmployee("Emp" + i, "RAND");
        }

        verify(storageServiceMock, times(5)).store(empArgCaptor.capture());
    }

    @Test
    public void getEmployee() {
        int id = 9;
        String name = "Roshan";
        when(storageServiceMock.findById(id))
            .thenReturn(new Employee(id, name, "dept"));

        Employee employee = serviceUnderTest.getEmployee(id);

        Assertions.assertEquals(id, employee.getId());
        Assertions.assertEquals(name, employee.getName());
    }
}