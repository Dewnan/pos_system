package me.dewnan.pos.model;

public class Product {

    private int id;
    private String prod_name;
    private double price;
    private int quantity;

    public Product (int id, String prod_name, double price, int quantity) {
        this.id  = id;
        this.prod_name = prod_name;
        this.price = price;
        this.quantity = quantity;
    }

    //Getters
    public int getId() {
        return id;
    }
    public String getProd_name() {
        return prod_name;
    }
    public double getPrice() {
        return price;
    }
    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString(){
        return id + " - " + prod_name + " - $" + price + " - Stock: " + quantity;
    }
}