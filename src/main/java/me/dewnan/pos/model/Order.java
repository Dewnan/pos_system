package me.dewnan.pos.model;
import java.util.List;

public class Order {
    private int orderID;
    private Customer customer;
    private double total;
    private List<CartItem> items;

    public Order(int orderID, Customer customer, double total, List<CartItem> items){
        this.orderID = orderID;
        this.customer = customer;
        this.total = total;
        this.items = items;
    }
    //getters for future features.
    public int getOrderID(){
        return orderID;
    }
    public Customer getCustomer(){
        return customer;
    }
    public double getTotal(){
        return total;
    }
    public List<CartItem> getItems() {
        return items;
    }

    @Override
    public String toString(){
        return "Order: " +orderID+" for "+getCustomer()+" Total: "+getTotal();
    }
}