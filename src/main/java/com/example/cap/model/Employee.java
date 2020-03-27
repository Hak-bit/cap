package com.example.cap.model;

//id, name, dept
public final class Employee {
    private final int id;
    private final String name;
    private final String dept;

    public Employee(int id, String name, String dept) {
        this.id = id;
        this.name = name;
        this.dept = dept;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDept() {
        return dept;
    }
}
