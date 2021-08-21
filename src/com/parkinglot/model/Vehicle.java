package com.parkinglot.model;

public class Vehicle {
    private String registrationNumber;
    private Integer driverAge;

    public Vehicle(String registrationNumber, Integer driverAge) {
        this.registrationNumber = registrationNumber;
        this.driverAge = driverAge;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public Integer getDriverAge() {
        return driverAge;
    }
}
