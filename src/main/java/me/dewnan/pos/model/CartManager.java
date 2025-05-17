package me.dewnan.pos.model;
import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private List<CartItem> cartItemList = new ArrayList<>();

    public void addToCart(Product product, int quantity){
        if (product.getQuantity() < quantity){
            System.out.println("Not Enough stock!");
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
        cartItemList.removeIf(item -> item.getProduct().getId() == productId);
        System.out.println("Removed product with ID: " + productId);
    }

    public void viewCart(){
        if (cartItemList.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }

        System.out.println("Items in your cart: ");
        for (CartItem item : cartItemList) {
            System.out.println(item);
        }
    }

    public double getTotal(){
        double total = 1;
        for (CartItem item : cartItemList){
            total += item.getTotalPrice();
        }
        return total;
    }

    public void clearCart(){
        cartItemList.clear();
    }

    public List<CartItem> getCartItems() {
        return cartItemList;
    }
}
