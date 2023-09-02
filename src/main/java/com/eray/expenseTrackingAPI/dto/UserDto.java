package com.eray.expenseTrackingAPI.dto;

public class UserDto {
    private String name;

    private String surname;

    private String country;

    private String city;

    private String address;


    public UserDto(String name, String surname, String country, String city, String address) {
        this.name = name;
        this.surname = surname;
        this.country = country;
        this.city = city;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
