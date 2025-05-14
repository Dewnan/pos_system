package me.dewnan.pos.model;
import java.util.ArrayList;
import java.util.List;

public class ProductManager {
    private List<Product> productsList = new ArrayList<>();
    private int id = 1;

    public void addProduct(String name, double price, int quantity) {
        Product product = new Product(id++, name, price, quantity);
        productsList.add(product);
        System.out.println("Product added: " + product);
    }
    public void listProducts() {
        if (productsList.isEmpty()) {
            System.out.println("No products found.");
            return;
        }
        for (Product p : productsList) {
            System.out.println(p);
        }
    }
    public void removeProduct(int id) {
        productsList.remove(id-1);
    }
}