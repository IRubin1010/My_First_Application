package com.example.myfirstapplication.model.entities;


public class Package {

    protected CurrentAddress currentAddress;
    protected Person person;
    protected String packageType;
    protected String fragile;
    protected String weight;
    protected String keyId;

    public Package(CurrentAddress currentAddress, Person person, String packageType, String fragile, String weight, String keyId) {
        this.currentAddress = currentAddress;
        this.packageType = packageType;
        this.fragile = fragile;
        this.weight = weight;
        this.person = person;
        this.keyId = keyId;
    }

    public Package() {
    }

    public CurrentAddress getCurrentAddress() {
        return currentAddress;
    }

    public void setCurrentAddress(CurrentAddress currentAddress) {
        this.currentAddress = currentAddress;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public String getFragile() {
        return fragile;
    }

    public void setFragile(String fragile) {
        this.fragile = fragile;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }
}

