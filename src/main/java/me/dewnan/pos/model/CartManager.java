package me.dewnan.pos.model;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private List<CartItem> cartItemList = new ArrayList<>();


    public void addToCart(Product product, int quantity){
        if (product.getQuantity() < quantity){
            System.out.println("Not enough stock!");
            return;
        }

        for (CartItem item : cartItemList){
            if (item.getProduct().getId() == product.getId()){
                System.out.println("This item is already in the cart.");
                return;
            }
        }

        CartItem item = new CartItem(product, quantity);
        cartItemList.add(item);
        System.out.println("Added to cart: " + item);
    }


    public void removeFromCart(int productId){
        boolean removed = cartItemList.removeIf(item -> item.getProduct().getId() == productId);
        if (removed) {
            System.out.println("Removed product with ID: " + productId);
        } else {
            System.out.println("Product not found in cart.");
        }
    }

    public void viewCart(){
        if (cartItemList.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }

        System.out.println("Items in your cart:");
        for (CartItem item : cartItemList) {
            System.out.println(item);
        }
    }

    public double getTotal(){
        double total = 0;
        for (CartItem item : cartItemList){
            total += item.getTotalPrice();
        }
        System.out.printf("Total amount: Rs. %.2f%n", total);
        return total;
    }

    public void clearCart(){
        cartItemList.clear();
        System.out.println("Cart cleared.");
    }

    public List<CartItem> getCartItems() {
        return cartItemList;
    }
}
