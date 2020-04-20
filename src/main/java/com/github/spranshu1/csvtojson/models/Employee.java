package com.github.spranshu1.csvtojson.models;

import java.util.List;

public class Employee {

    private String name;

    private String email;

    private Address address;

    private Department department;

    private List<Integer> phone;

    public Employee(String name, String email, Address address, Department department) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Integer> getPhone() {
        return phone;
    }

    public void setPhone(List<Integer> phone) {
        this.phone = phone;
    }
}
