package me.dewnan.pos;
import me.dewnan.pos.model.ProductManager;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("POS System Started!");
        ProductManager pm = new ProductManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add Product");
            System.out.println("2. List Products");
            System.out.println("3. Remove Product");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int input = scanner.nextInt();
            scanner.nextLine();

            switch (input) {
                case 1:
                    System.out.println("Enter Product Name: ");
                    String name = scanner.nextLine();

                    System.out.println("Enter the Price of a single unit: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.println("Enter quantity: ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine();

                    pm.addProduct(name, price, quantity);
                    break;

                case 2:
                    pm.listProducts();
                    break;

                case 3:
                    System.out.println("Enter the item id: ");
                    int id = scanner.nextInt();
                    pm.removeProduct(id);
                    break;

                case 4:
                    System.out.println("Exiting POS System.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}