package me.dewnan.pos.model;

public class Customer {
    private String name;
    private int nic;
    private String address;
    private int contactNumber;

    public Customer (String name, int nic, String address, int contactNumber){
        this.name = name;
        this.nic = nic;
        this.address = address;
        this.contactNumber = contactNumber;
    }

    //getters
    public String getName(){
        return name;
    }
    public int getNic(){
        return nic;
    }
    public String getAddress(){
        return address;
    }
    public int getContactNumber(){
        return contactNumber;
    }

    @Override
    public String toString(){
        return name + " - " + nic + " - " + address + " - " + contactNumber;
    }
}

