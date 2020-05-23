package com.example.myfirstapplication.model.entities;

public class CurrentAddress {
    String country ;
    String state ;
    String city ;
    String address ;
    String postalCode ;

    public CurrentAddress() {
    }

    public CurrentAddress(String country, String state, String city, String address, String postalCode) {
        this.country = country;
        this.state = state;
        this.city = city;
        this.address = address;
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }


}
