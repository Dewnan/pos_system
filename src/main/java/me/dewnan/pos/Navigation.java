package me.dewnan.pos;
import java.util.Scanner;

import me.dewnan.pos.model.CartManager;
import me.dewnan.pos.model.ProductManager;
import me.dewnan.pos.model.CustomerManager;

public class Navigation {
    private Scanner scanner  = new Scanner(System.in);
    private ProductManager pm = new ProductManager();
    private CustomerManager cm = new CustomerManager();
    private CartManager cartmanager = new CartManager();

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
                    inventoryMenue();
                    break;
                case 2:
                    customerMenue();
                    break;
                case 3:
                    cartMenu();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid Chioce");
            }
        }

    }
    private void inventoryMenue(){
        while (true){
            System.out.println("--------------------");
            System.out.println("~~~ Inventory Menue ~~~");
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

    private void customerMenue(){
        while (true){
            System.out.println("--------------------");
            System.out.println("~~~ Customer Menue ~~~");
            System.out.println("1. Add Custermer");
            System.out.println("2. Remove Customer");
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
                    int id = scanner.nextInt();
                    cm.removeCustomer(id);
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

                    System.out.println("How many items: ");
                    int quantity = scanner.nextInt();
                case 2:
                    //  Remove Item
                case 3:
                    //  List Items
                case 4:
                    //  Checkout
            }
        }
    }
}
