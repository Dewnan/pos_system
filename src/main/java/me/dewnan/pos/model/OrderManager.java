package me.dewnan.pos.model;
import java.util.List;
import java.util.ArrayList;

public class OrderManager {
    private List<Order> orders = new ArrayList<>();
    private int orderID = 1;


    public void placeOrder(Customer customer, List<CartItem> cartItemList){
        double total = 0;

        for (CartItem item : cartItemList){
            total += item.getTotalPrice();
        }
        Order order = new Order(orderID++, customer, total, cartItemList);
        orders.add(order);
        System.out.println("Order Placed Successfully!");
        System.out.println(order);
    }

    public void removeOrder(Order order){
        orders.remove(order);
    }
    public void getCartItems() {
        if (orders.isEmpty()) {
            System.out.println("No orders available.");
            return;
        }

        for (Order order : orders) {
            System.out.println("Order ID: " + order.getOrderID());
            for (CartItem item : order.getItems()) {
                System.out.println("- " + item);
            }
            System.out.println("Total: $" + order.getTotal());
            System.out.println("-------------------------");
        }
    }
}
