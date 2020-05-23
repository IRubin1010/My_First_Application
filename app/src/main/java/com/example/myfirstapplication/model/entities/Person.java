package com.example.myfirstapplication.model.entities;

public class Person {
    protected String name;
    protected String tel;
    protected String email;
    protected String address;

    public Person(String name, String tel, String email, String address) {
        this.name = name;
        this.tel = tel;
        this.email = email;
        this.address = address;
    }
    public Person() {
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
