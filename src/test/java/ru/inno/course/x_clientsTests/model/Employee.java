package ru.inno.course.x_clientsTests.model;

import lombok.Data;
import lombok.Getter;

@Data
public class Employee {
    private int id = 0;
    private String firstName = "April";
    private String middleName = "Rosa";
    private String lastName = "O'Nil";
    private int companyId;
    private String email = "ninja@gmail.com";
    private String phone = "777-777";
    private String birthdate = "2024-03-17T20:50:57.525Z";
    private boolean isActive = true;

    public Employee() {
    }

    public Employee(String firstName) {
        this.firstName = firstName;
    }

    public Employee(String firstName, String middleName, String lastName, int companyId, String email, String phone, boolean isActive) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.companyId = companyId;
        this.email = email;
        this.phone = phone;
        this.isActive = isActive;
    }

    public String getJsonString(int idCompany) {
        return "{\"firstName\": \"" + this.firstName + "\"," +
                " \"middleName\": \"" + this.middleName + "\"," +
                " \"lastName\": \"" + this.lastName + "\"," +
                " \"companyId\":" + idCompany + "," +
                " \"email\": \"" + this.email + "\"," +
                " \"phone\": \"" + this.phone + "\"," +
                " \"birthdate\": \"" + this.birthdate + "\"," +
                " \"isActive\": \"" + this.isActive + "\"}";
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getCompanyId() {
        return companyId;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
