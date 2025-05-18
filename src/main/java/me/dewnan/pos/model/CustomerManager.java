package me.dewnan.pos.model;

import me.dewnan.pos.db.DatabaseHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerManager {

    String sqlQuery = "INSERT INTO customers (nic, name, address, contactNumber) VALUES (?, ?, ?, ?)";

    public void addCustomer(String name, int nic, String address, int contactNumber){
        try (Connection conn = DatabaseHandler.connect();
            PreparedStatement query = conn.prepareStatement(sqlQuery);) {

            query.setInt(1, nic);
            query.setString(2, name);
            query.setString(3, address);
            query.setInt(4, contactNumber);
            query.executeUpdate();

            System.out.println("Customer added: " + name);

            } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void removeCustomer(int nic) {
        String sqlQuery = "DELETE FROM customers WHERE nic = ?";

        try (Connection conn = DatabaseHandler.connect();
            PreparedStatement query = conn.prepareStatement(sqlQuery);) {

            query.setInt(1, nic);
            query.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void listCustomers(){
        String sqlQuery = "SELECT * FROM customers";

        try (Connection conn = DatabaseHandler.connect();
        PreparedStatement query = conn.prepareStatement(sqlQuery);) {

            ResultSet result = query.executeQuery();

            while(result.next()){
                System.out.println("NIC: "+result.getInt("nic")+
                        ", Name: "+result.getString("name")+
                        ", Address: "+result.getString("address")+
                        ", Contact Number: "+result.getInt("contactNumber"));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}