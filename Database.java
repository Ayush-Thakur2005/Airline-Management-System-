import java.sql.*;
import javax.swing.*;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/airline_management";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void initialize() {
        try (Connection conn = getConnection()) {
            // Create users table
            String createUsersTable = "CREATE TABLE IF NOT EXISTS users (" +
                "user_id INT AUTO_INCREMENT PRIMARY KEY, " +
                "username VARCHAR(50) NOT NULL UNIQUE, " +
                "password VARCHAR(255) NOT NULL, " +
                "role VARCHAR(20) NOT NULL, " +
                "full_name VARCHAR(100) NOT NULL, " +
                "email VARCHAR(100) NOT NULL, " +
                "active BOOLEAN NOT NULL DEFAULT TRUE)";
            
            // Create flights table
            String createFlightsTable = "CREATE TABLE IF NOT EXISTS flights (" +
                "flight_id INT AUTO_INCREMENT PRIMARY KEY, " +
                "flight_number VARCHAR(10) NOT NULL UNIQUE, " +
                "departure_city VARCHAR(50) NOT NULL, " +
                "arrival_city VARCHAR(50) NOT NULL, " +
                "departure_time DATETIME NOT NULL, " +
                "arrival_time DATETIME NOT NULL, " +
                "total_seats INT NOT NULL, " +
                "available_seats INT NOT NULL, " +
                "price DECIMAL(10,2) NOT NULL, " +
                "status VARCHAR(20) NOT NULL DEFAULT 'SCHEDULED')";
            
            // Create passengers table
            String createPassengersTable = "CREATE TABLE IF NOT EXISTS passengers (" +
                "passenger_id INT AUTO_INCREMENT PRIMARY KEY, " +
                "first_name VARCHAR(50) NOT NULL, " +
                "last_name VARCHAR(50) NOT NULL, " +
                "date_of_birth DATE NOT NULL, " +
                "gender VARCHAR(10) NOT NULL, " +
                "nationality VARCHAR(50) NOT NULL, " +
                "passport_number VARCHAR(20) NOT NULL UNIQUE, " +
                "phone VARCHAR(20) NOT NULL, " +
                "email VARCHAR(100) NOT NULL)";
            
            // Create bookings table
            String createBookingsTable = "CREATE TABLE IF NOT EXISTS bookings (" +
                "booking_id INT AUTO_INCREMENT PRIMARY KEY, " +
                "flight_id INT NOT NULL, " +
                "passenger_id INT NOT NULL, " +
                "booking_date DATETIME NOT NULL, " +
                "seat_number VARCHAR(10) NOT NULL, " +
                "status VARCHAR(20) NOT NULL DEFAULT 'CONFIRMED', " +
                "payment_status VARCHAR(20) NOT NULL DEFAULT 'PENDING', " +
                "FOREIGN KEY (flight_id) REFERENCES flights(flight_id), " +
                "FOREIGN KEY (passenger_id) REFERENCES passengers(passenger_id))";

            Statement stmt = conn.createStatement();
            stmt.execute(createUsersTable);
            stmt.execute(createFlightsTable);
            stmt.execute(createPassengersTable);
            stmt.execute(createBookingsTable);

            // Insert default admin user if not exists
            String checkAdmin = "SELECT COUNT(*) FROM users WHERE username = 'admin'";
            ResultSet rs = stmt.executeQuery(checkAdmin);
            rs.next();
            if (rs.getInt(1) == 0) {
                String insertAdmin = "INSERT INTO users (username, password, role, full_name, email) " +
                    "VALUES ('admin', 'admin123', 'ADMIN', 'System Administrator', 'admin@airline.com')";
                stmt.execute(insertAdmin);
            }

            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, 
                "Error initializing database: " + e.getMessage(),
                "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
} 