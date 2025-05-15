package me.dewnan.pos.model;
import java.util.List;
import java.util.ArrayList;

public class CustomerManager {
    private List<Customer> customersList = new ArrayList<>();

    public void addCustomer(String name, int nic, String address, int contactNumber) {
        Customer customer = new Customer(name, nic, address, contactNumber);
        customersList.add(customer);
        System.out.println("Customer: " + name + " added.");
    }
    public void removeCustomer (int id) {
        customersList.remove(id);
        System.out.println("Customer: " + id + " removed.");
    }
    public void listCustomers(){
        if (customersList.isEmpty()){
            System.out.println("Customer List is empty");
            return;
        }
        for (Customer c : customersList ) {
            System.out.println(c);
        }
    }
}
