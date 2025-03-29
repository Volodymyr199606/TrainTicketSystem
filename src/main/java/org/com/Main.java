package org.com;

import org.com.database.DatabaseConnection;


import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.getConnection()) {
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}