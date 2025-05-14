package me.dewnan.pos.model;
import java.util.ArrayList;
import java.util.List;

public class ProductManager {
    private List<Product> products = new ArrayList<>();
    private int id = 1;

    public void addProduct(String name, double price, int quantity) {
        Product product = new Product(id++, name, price, quantity);
        products.add(product);
        System.out.println("Product added: " + product);
    }
    public void listProducts() {
        if (products.isEmpty()) {
            System.out.println("No products found.");
            return;
        }
        for (Product p : products) {
            System.out.println(p);
        }
    }
    public void removeProduct(int id) {
        products.remove(id-1);
    }
}