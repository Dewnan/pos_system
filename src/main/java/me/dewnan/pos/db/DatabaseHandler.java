package me.dewnan.pos.db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHandler {
    private static final String DB_URL = "jdbc:sqlite:pos.db";

    public static Connection connect(){
        try {
            Connection conn = DriverManager.getConnection(DB_URL);
            return conn;
        } catch (SQLException e) {
            System.out.println("Database Connection Failed!");
            e.printStackTrace(); // Add this to see the real cause
            return null;
        }
    }

    public static void initialize(){

        var createInventoryTQ = "CREATE TABLE IF NOT EXISTS products (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL," +
                "price REAL NOT NULL," +
                "quantity INTEGER NOT NULL);";

        var createCustomerTQ = "CREATE TABLE IF NOT EXISTS customers (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL," +
                "nic INTEGER," +
                "address TEXT NOT NULL," +
                "contactNumber INTEGER NOT NULL);";

        var createOrdersTQ = "CREATE TABLE IF NOT EXISTS orders (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "customer_id INTEGER," +
                "total REAL NOT NULL," +
                "date TEXT NOT NULL," +
                "FOREIGN KEY(customer_id) REFERENCES customers(id));";

        var createCartTQ = "CREATE TABLE IF NOT EXISTS order_items (" +
                "    id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "    order_id INTEGER NOT NULL," +
                "    product_id INTEGER NOT NULL," +
                "    quantity INTEGER NOT NULL," +
                "    price REAL NOT NULL," +
                "    FOREIGN KEY(order_id) REFERENCES orders(id)," +
                "    FOREIGN KEY(product_id) REFERENCES products(id));";

        try (Connection connection = connect();){
            if (connection == null){
                System.out.println("Failed to initialize database: no connection.");
                return;
            }

            Statement query = connection.createStatement();

            query.execute(createInventoryTQ);
            query.execute(createCustomerTQ);
            query.execute(createOrdersTQ);
            query.execute(createCartTQ);
            System.out.println("Database initialized.");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
