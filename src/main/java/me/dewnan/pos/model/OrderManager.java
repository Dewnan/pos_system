package me.dewnan.pos.model;
import me.dewnan.pos.db.DatabaseHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

public class OrderManager {
    private List<Order> orders = new ArrayList<>();
    private int orderID = 0;

    public void placeOrder(Customer customer, List<CartItem> cartItemList){
        double total = 0;

        for (CartItem item : cartItemList){
            total += item.getTotalPrice();
        }
        String insertOrderSQL = "INSERT INTO orders (customer_id, total, date) VALUES (?, ?, datetime('now'))";
        String insertItemSQL = "INSERT INTO order_items (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseHandler.connect()){
            conn.setAutoCommit(false);

            try (PreparedStatement orderQuery = conn.prepareStatement(insertOrderSQL, Statement.RETURN_GENERATED_KEYS)){
                orderQuery.setInt(1, customer.getNic());
                orderQuery.setDouble(2, total);
                orderQuery.executeUpdate();


                ResultSet generatedKeys = orderQuery.getGeneratedKeys();
                if (generatedKeys.next()){
                    int orderId = generatedKeys.getInt(1);

                    try (PreparedStatement itemQuery = conn.prepareStatement(insertItemSQL)){
                        for (CartItem item: cartItemList){
                            itemQuery.setInt(1, orderId);
                            itemQuery.setInt(2, item.getProduct().getId());
                            itemQuery.setInt(3, item.getQuantity());
                            itemQuery.setDouble(4, item.getTotalPrice());
                            itemQuery.executeUpdate();
                        }
                    }
                    conn.commit();
                    System.out.println("Order placed and saved to database.");
                } else {
                    conn.rollback();
                    System.out.println("Failed to retrieve order ID.");
                }
            } catch (Exception e) {
                conn.rollback();
                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void removeOrder(int orderID){
        String deleteItemsSQL = "DELETE FROM order_items WHERE order_id = ?";
        String deleteOrderSQL = "DELETE FROM orders WHERE id = ?";

        try (Connection conn = DatabaseHandler.connect()){
            conn.setAutoCommit(false);

            try (PreparedStatement deleteItemQuery = conn.prepareStatement(deleteItemsSQL);
            PreparedStatement deleteOrderQuery = conn.prepareStatement(deleteOrderSQL)){
                deleteOrderQuery.setInt(1, orderID);
                deleteOrderQuery.executeUpdate();

                deleteItemQuery.setInt(1, orderID);
                deleteItemQuery.executeUpdate();

                conn.commit();
                System.out.println("Order " + orderID + " removed successfully.");
            } catch (Exception e) {
                System.out.println(e);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public void getCartItems() {
        String orderSQL = "SELECT * FROM orders";
        String itemsSQL = "SELECT * FROM order_items WHERE order_id = ?";

        try (Connection conn = DatabaseHandler.connect()){
            PreparedStatement getOrdersQuery = conn.prepareStatement(orderSQL);
            PreparedStatement getItemsQuery = conn.prepareStatement(itemsSQL);

            ResultSet ordersResult = getOrdersQuery.executeQuery();

            while (ordersResult.next()){
                int orderId = ordersResult.getInt("id");
                double total = ordersResult.getDouble("total");
                String date = ordersResult.getString("date");

                System.out.println("Order ID: " + orderId + " | Date: " + date + " | Total: " + total);
                System.out.println("Items:");

                getItemsQuery.setInt(1, orderId);
                ResultSet itemsResult = getItemsQuery.executeQuery();

                while (itemsResult.next()) {
                    int productId = itemsResult.getInt("product_id");
                    int quantity = itemsResult.getInt("quantity");
                    double price = itemsResult.getDouble("price");

                    System.out.println("- Product ID: " + productId + ", Quantity: " + quantity + ", Price: " + price);
                }
                System.out.println("----------------------------");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
