package ru.inno.course.x_clientsTests.model;

import lombok.Data;

@Data
public class Company {
    private String name = "Ninja Turtles";
    private String description = "Teenage Mutant Ninja Turtles";

    public Company() {
    }

    public Company(String name, String description) {
        this.name = name;
        this.description = "description";
    }

    public String getJsonString() {
        return "{\"name\": \"" + this.name + "\", \"middleName\": \"" + this.description + "\"}";
    }
}