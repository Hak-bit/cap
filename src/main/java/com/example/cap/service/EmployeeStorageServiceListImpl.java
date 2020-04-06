package com.example.cap.service;

import com.example.cap.exception.EmployeeNotFoundException;
import com.example.cap.model.Employee;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class EmployeeStorageServiceListImpl implements EmployeeStorageService {

    private List<Employee> employeeStore = new ArrayList<>();

    public Employee store(Employee employee) {
        employeeStore.add(employee);

        return employee;
    }

    public List<Employee> findAll() {
        return new ArrayList<>(employeeStore);
    }

    @Override
    public void delete(int id) {
        Iterator<Employee> iter = employeeStore.iterator();
        while(iter.hasNext()) {
            Employee emp = iter.next();
            if (emp.getId() == id) {
                iter.remove();
            }
        }
    }

    public Employee findById(int id) {
        return employeeStore.stream()
            .filter(this::filterNull)
            .filter(filterById(id))
            .findFirst()
            .orElseThrow(() ->
                new EmployeeNotFoundException(String.format("Employee with id=%d not found", id)));
    }

    private boolean filterNull(Employee employee) {
        return Objects.nonNull(employee);
    }

    //Predicate, Function, Consumer, Supplier
    private Predicate<Employee> filterById(int id) {
        return emp -> emp.getId() == id;
    }
}
