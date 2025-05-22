package me.dewnan.pos;
import java.util.Scanner;

import me.dewnan.pos.model.*;

public class Navigation {
    private Scanner scanner  = new Scanner(System.in);
    private ProductManager pm = new ProductManager();
    private CustomerManager cm = new CustomerManager();
    private CartManager cartmanager = new CartManager();
    private OrderManager om = new OrderManager();

    public void showMainMenu(){
        while (true){
            System.out.println("--------------------");
            System.out.println("~~~ Main Menu ~~~");
            System.out.println("1. Manage Inventory");
            System.out.println("2. Manage Customers");
            System.out.println("3. Cart");
            System.out.println("4. Exit");
            System.out.println("--------------------");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            switch (choice){
                case 1:
                    inventoryMenu();
                    break;
                case 2:
                    customerMenu();
                    break;
                case 3:
                    cartMenu();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid Choice");
            }
        }

    }
    private void inventoryMenu(){
        while (true){
            System.out.println("--------------------");
            System.out.println("~~~ Inventory Menu ~~~");
            System.out.println("1. Add Product");
            System.out.println("2. List Products");
            System.out.println("3. Remove Product");
            System.out.println("4. Back");
            System.out.println("--------------------");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1:
                    System.out.print("Enter Product Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter the Price of a single unit: ");
                    double price = scanner.nextDouble();

                    System.out.print("Enter Quantity: ");
                    int quantity = scanner.nextInt();

                    pm.addProduct(name, price, quantity);
                    break;
                case 2:
                    pm.listProducts();
                    break;
                case 3:
                    int id = scanner.nextInt();
                    pm.removeProduct(id);
                    break;
                case 4:
                    System.out.println("Going Back.");
                    return;
            }
        }
    }

    private void customerMenu(){
        while (true){
            System.out.println("--------------------");
            System.out.println("~~~ Customer Menu ~~~");
            System.out.println("1. Add a Customer");
            System.out.println("2. Remove a Customer");
            System.out.println("3. List Customers");
            System.out.println("4. Back");
            System.out.println("--------------------");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1:
                    System.out.print("Enter Customer Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter NIC Number: ");
                    int nic = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter Address: ");
                    String address = scanner.nextLine();

                    System.out.print("Enter Contact Number: ");
                    int contact = scanner.nextInt();

                    cm.addCustomer(name, nic, address, contact);
                    break;
                case 2:
                    System.out.print("Enter the NIC Number: ");
                    int removeNic = scanner.nextInt();
                    cm.removeCustomer(removeNic);
                    scanner.nextLine();
                    break;
                case 3:
                    cm.listCustomers();
                    break;
                case 4:
                    System.out.println("Going Back..");
                    return;
            }
        }
    }

    private void cartMenu(){
        System.out.println("Cart menu opened.");
        while (true){
            System.out.println("--------------------");
            System.out.println("~~~ Manage your Cart ~~~");
            System.out.println("1. Add Item");
            System.out.println("2. Remove Item");
            System.out.println("3. List Items in cart");
            System.out.println("4. Check out (Need to develop)");
            System.out.println("--------------------");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1:
                    System.out.print("Enter Item: ");
                    String Item = scanner.nextLine();

                    System.out.print("How many items: ");
                    int quantity = scanner.nextInt();

                    Product product = pm.getProductByName(Item);
                    cartmanager.addToCart(product, quantity);
                    break;
                case 2:
                    System.out.print("Enter the Product ID: ");
                    int productId = scanner.nextInt();
                    cartmanager.removeFromCart(productId);
                case 3:
                    cartmanager.viewCart();
                case 4:
                    if (cartmanager.getCartItems().isEmpty()) {
                        System.out.println("Cart is empty. Cannot checkout.");
                        break;
                    }

                    // 2. Ask for customer NIC
                    System.out.print("Enter NIC number for the customer: ");
                    int nic = scanner.nextInt();
                    scanner.nextLine();

                    // 3. Find the customer
                    Customer customer = cm.getCustomerByNIC(nic);
                    if (customer == null) {
                        System.out.println("Customer not found. Please add the customer first.");
                        break;
                    }

                    // 4. Place the order
                    om.placeOrder(customer, cartmanager.getCartItems());

                    // 5. Clear the cart
                    cartmanager.clearCart();
                    break;
            }
        }
    }
}