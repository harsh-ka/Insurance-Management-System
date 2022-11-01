package com.example.models;

import java.sql.Date;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public class Employee {
    @NotBlank
    private String employeeId;
    private Date joinDate;
    private Date endDate;

    @NotBlank
    private String firstName;
    private  String middleName;
    private  String lastName;

    @NotBlank
    private String email;

    private String employeeAddress;

    private int admin_id;

    String username;

    public Employee() {
    }

    public Employee(String employeeId, Date joinDate, Date endDate, String firstName, String middleName, String lastName, String email, String employeeAddress, int admin_id, String username) {
        this.employeeId = employeeId;
        this.joinDate = joinDate;
        this.endDate = endDate;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.employeeAddress = employeeAddress;
        this.admin_id = admin_id;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmployeeAddress() {
        return employeeAddress;
    }

    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }




}
