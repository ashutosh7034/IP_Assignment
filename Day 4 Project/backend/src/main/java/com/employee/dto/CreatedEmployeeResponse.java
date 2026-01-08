package com.employee.dto;

public class CreatedEmployeeResponse {
    private EmployeeDTO employee;
    private String username;
    private String temporaryPassword;

    public CreatedEmployeeResponse() {}

    public CreatedEmployeeResponse(EmployeeDTO employee, String username, String temporaryPassword) {
        this.employee = employee;
        this.username = username;
        this.temporaryPassword = temporaryPassword;
    }

    public EmployeeDTO getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDTO employee) {
        this.employee = employee;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTemporaryPassword() {
        return temporaryPassword;
    }

    public void setTemporaryPassword(String temporaryPassword) {
        this.temporaryPassword = temporaryPassword;
    }
}
