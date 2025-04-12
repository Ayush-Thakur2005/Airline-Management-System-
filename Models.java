import java.util.Date;

public class Models {
    public static class User {
        private int userId;
        private String username;
        private String password;
        private String role;
        private String fullName;
        private String email;
        private boolean active;

        // Constructor, getters, and setters
    }

    public static class Flight {
        private int flightId;
        private String flightNumber;
        private String departureCity;
        private String arrivalCity;
        private Date departureTime;
        private Date arrivalTime;
        private int totalSeats;
        private int availableSeats;
        private double price;
        private String status;

        // Constructor, getters, and setters
    }

    public static class Passenger {
        private int passengerId;
        private String firstName;
        private String lastName;
        private Date dateOfBirth;
        private String gender;
        private String nationality;
        private String passportNumber;
        private String phone;
        private String email;

        // Constructor, getters, and setters
    }

    public static class Booking {
        private int bookingId;
        private int flightId;
        private int passengerId;
        private Date bookingDate;
        private String seatNumber;
        private String status;
        private String paymentStatus;

        // Constructor, getters, and setters
    }
} 