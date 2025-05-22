package me.dewnan.pos.model;

import  java.util.List;
import java.util.ArrayList;

import me.dewnan.pos.db.DatabaseHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProductManager {

    public void addProduct(String name, double price, int quantity) {
        String sqlQuery = "INSERT INTO products (id, name, price, quantity) VALUES (?, ?, ?,?)";

        try (Connection conn = DatabaseHandler.connect();
             PreparedStatement query = conn.prepareStatement(sqlQuery);){

            query.setString(2, name);
            query.setDouble(3, price);
            query.setInt(4,quantity);
            query.executeUpdate();
            System.out.println(name+ " added to the system.");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void removeProduct(int id) {
        String sqlQuery = "DELETE FROM products WHERE id = ?";

        try (Connection conn = DatabaseHandler.connect();
             PreparedStatement query = conn.prepareStatement(sqlQuery);){
            query.setInt(1, id);
            query.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public List<Product> listProducts() {
        List<Product> products = new ArrayList<>();

        String sqlQuery = "SELECT * FROM products";

        try (Connection conn = DatabaseHandler.connect();
             PreparedStatement query = conn.prepareStatement(sqlQuery)) {

            ResultSet result = query.executeQuery();

            while(result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                double price = result.getDouble("price");
                int quantity = result.getInt("quantity");

                Product product = new Product(id, name, price, quantity);
                products.add(product);

                // Print for display (optional)
                System.out.println("ID: " + id + ", Name: " + name + ", Price of a unit: " + price + ", Available Quantity: " + quantity);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return products;
    }


    public Product getProductByName(String name) {
        List<Product> products = listProducts();
        for (Product product : products) {
            if (product.getProd_name().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }
}