-- Create the database
CREATE DATABASE IF NOT EXISTS airline_management;
USE airline_management;

-- Create users table
CREATE TABLE IF NOT EXISTS users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE
);

-- Create flights table
CREATE TABLE IF NOT EXISTS flights (
    flight_id INT AUTO_INCREMENT PRIMARY KEY,
    flight_number VARCHAR(10) NOT NULL,
    departure_city VARCHAR(50) NOT NULL,
    arrival_city VARCHAR(50) NOT NULL,
    departure_time VARCHAR(20) NOT NULL,
    arrival_time VARCHAR(20) NOT NULL,
    total_seats INT NOT NULL,
    available_seats INT NOT NULL,
    price DOUBLE NOT NULL,
    flight_status VARCHAR(20) NOT NULL DEFAULT 'Scheduled'
);

-- Create passengers table
CREATE TABLE IF NOT EXISTS passengers (
    passenger_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    passport_number VARCHAR(20) NOT NULL UNIQUE,
    address VARCHAR(255),
    nationality VARCHAR(50),
    gender VARCHAR(10),
    date_of_birth VARCHAR(20)
);

-- Create bookings table
CREATE TABLE IF NOT EXISTS bookings (
    booking_id INT AUTO_INCREMENT PRIMARY KEY,
    flight_id INT NOT NULL,
    passenger_id INT NOT NULL,
    booking_date VARCHAR(20) NOT NULL,
    seat_number VARCHAR(10) NOT NULL,
    status VARCHAR(20) NOT NULL,
    total_price DOUBLE NOT NULL,
    ticket_id VARCHAR(20) NOT NULL UNIQUE,
    checked_in BOOLEAN DEFAULT FALSE,
    checkin_time VARCHAR(20),
    payment_status VARCHAR(20) NOT NULL,
    boarding_pass TEXT,
    special_requests TEXT,
    FOREIGN KEY (flight_id) REFERENCES flights(flight_id),
    FOREIGN KEY (passenger_id) REFERENCES passengers(passenger_id)
);

-- Insert default admin user
INSERT INTO users (username, password, role, full_name, email, active) 
VALUES ('admin', 'admin123', 'ADMIN', 'System Administrator', 'admin@airline.com', TRUE);

-- Insert sample staff user
INSERT INTO users (username, password, role, full_name, email, active) 
VALUES ('staff', 'staff123', 'STAFF', 'Staff User', 'staff@airline.com', TRUE);

-- Insert sample flights
INSERT INTO flights (flight_number, departure_city, arrival_city, departure_time, arrival_time, total_seats, available_seats, price, flight_status) VALUES
('AA101', 'New York', 'Los Angeles', '2024-03-15 08:00', '2024-03-15 11:00', 150, 150, 299.99, 'Scheduled'),
('BA202', 'London', 'Paris', '2024-03-16 10:30', '2024-03-16 12:00', 200, 200, 199.99, 'Scheduled'),
('DL303', 'Atlanta', 'Miami', '2024-03-17 14:00', '2024-03-17 16:00', 180, 180, 249.99, 'Scheduled'),
('UA404', 'Chicago', 'Denver', '2024-03-18 09:00', '2024-03-18 11:30', 160, 160, 179.99, 'Scheduled'),
('LH505', 'Frankfurt', 'Berlin', '2024-03-19 13:00', '2024-03-19 14:30', 170, 170, 159.99, 'Scheduled'),
('AF202', 'Paris', 'Rome', '2024-03-20 07:30', '2024-03-20 09:45', 190, 190, 219.99, 'Scheduled'),
('QF118', 'Sydney', 'Melbourne', '2024-03-21 16:00', '2024-03-21 17:30', 200, 200, 179.99, 'Scheduled'),
('EK241', 'Dubai', 'London', '2024-03-22 23:30', '2024-03-23 03:45', 300, 300, 649.99, 'Scheduled'),
('SQ321', 'Singapore', 'Tokyo', '2024-03-23 11:15', '2024-03-23 19:30', 250, 250, 499.99, 'Scheduled'),
('AA567', 'Los Angeles', 'New York', '2024-03-24 14:00', '2024-03-24 22:30', 150, 150, 329.99, 'Scheduled');

-- Insert sample passengers
INSERT INTO passengers (first_name, last_name, email, phone, passport_number, address, nationality, gender, date_of_birth) VALUES
('John', 'Doe', 'john.doe@email.com', '+1-555-123-4567', 'P12345678', '123 Main St, New York, NY', 'American', 'Male', '1980-05-15'),
('Jane', 'Smith', 'jane.smith@email.com', '+1-555-987-6543', 'P87654321', '456 Park Ave, Boston, MA', 'American', 'Female', '1985-10-20'),
('Robert', 'Johnson', 'robert.j@email.com', '+44-20-1234-5678', 'P11223344', '10 Downing St, London, UK', 'British', 'Male', '1975-03-25'),
('Emily', 'Williams', 'emily.w@email.com', '+33-1-2345-6789', 'P55667788', '15 Rue de Rivoli, Paris, France', 'French', 'Female', '1990-07-12'),
('Michael', 'Brown', 'michael.b@email.com', '+49-30-1234-5678', 'P99001122', 'Friedrichstrasse 123, Berlin, Germany', 'German', 'Male', '1982-11-30'),
('Emma', 'Garcia', 'emma.g@email.com', '+34-91-555-7777', 'P33445566', 'Gran Via 56, Madrid, Spain', 'Spanish', 'Female', '1988-02-10'),
('James', 'Wilson', 'james.w@email.com', '+1-555-222-3333', 'P77889900', '789 Oak St, Chicago, IL', 'American', 'Male', '1979-08-22'),
('Sofia', 'Martinez', 'sofia.m@email.com', '+52-55-5555-1234', 'P12131415', 'Av. Reforma 222, Mexico City, Mexico', 'Mexican', 'Female', '1992-04-17'),
('Liu', 'Wei', 'liu.w@email.com', '+86-10-8888-7777', 'E12345678', 'Chaoyang District, Beijing, China', 'Chinese', 'Male', '1986-09-05'),
('Sara', 'Patel', 'sara.p@email.com', '+91-22-3333-4444', 'Z98765432', '10 Marine Drive, Mumbai, India', 'Indian', 'Female', '1993-12-08');

-- Generate sample ticket IDs
SET @ticket1 = 'ABC-123456';
SET @ticket2 = 'DEF-234567';
SET @ticket3 = 'GHI-345678';
SET @ticket4 = 'JKL-456789';
SET @ticket5 = 'MNO-567890';

-- Insert sample bookings
INSERT INTO bookings (flight_id, passenger_id, booking_date, seat_number, status, total_price, ticket_id, checked_in, checkin_time, payment_status, special_requests) VALUES
(1, 1, '2024-03-10', '12A', 'Confirmed', 299.99, @ticket1, FALSE, NULL, 'Completed', 'Vegetarian meal'),
(2, 2, '2024-03-11', '15B', 'Confirmed', 199.99, @ticket2, FALSE, NULL, 'Completed', 'Window seat preference'),
(3, 3, '2024-03-12', '8C', 'Confirmed', 249.99, @ticket3, TRUE, '2024-03-17 10:30:00', 'Completed', 'Wheelchair assistance'),
(4, 4, '2024-03-13', '20D', 'Checked-in', 179.99, @ticket4, TRUE, '2024-03-18 07:45:00', 'Completed', ''),
(5, 5, '2024-03-14', '10E', 'Cancelled', 159.99, @ticket5, FALSE, NULL, 'Refunded', 'Extra baggage');

-- Update available seats after bookings
UPDATE flights SET available_seats = available_seats - 1 WHERE flight_id IN (1, 2, 4);
UPDATE flights SET available_seats = available_seats - 1 WHERE flight_id = 3;
-- Flight 5 is cancelled, so seat is available again

-- Create indexes for better performance
CREATE INDEX idx_flight_number ON flights(flight_number);
CREATE INDEX idx_flight_status ON flights(flight_status);
CREATE INDEX idx_passport_number ON passengers(passport_number);
CREATE INDEX idx_booking_date ON bookings(booking_date);
CREATE INDEX idx_ticket_id ON bookings(ticket_id);
CREATE INDEX idx_payment_status ON bookings(payment_status);

-- Create a view for flight availability
CREATE OR REPLACE VIEW flight_availability AS
SELECT 
    f.flight_id,
    f.flight_number,
    f.departure_city,
    f.arrival_city,
    f.departure_time,
    f.arrival_time,
    f.available_seats,
    f.total_seats,
    f.price,
    f.flight_status
FROM flights f
WHERE f.available_seats > 0 AND f.flight_status = 'Scheduled';

-- Create a view for booking details
CREATE OR REPLACE VIEW booking_details AS
SELECT 
    b.booking_id,
    b.ticket_id,
    f.flight_number,
    f.departure_city,
    f.arrival_city,
    f.departure_time,
    f.arrival_time,
    CONCAT(p.first_name, ' ', p.last_name) AS passenger_name,
    p.passport_number,
    b.booking_date,
    b.seat_number,
    b.status,
    b.checked_in,
    b.checkin_time,
    b.payment_status,
    b.total_price,
    b.special_requests
FROM bookings b
JOIN flights f ON b.flight_id = f.flight_id
JOIN passengers p ON b.passenger_id = p.passenger_id;

-- Create a view for daily bookings
CREATE OR REPLACE VIEW daily_bookings_summary AS
SELECT 
    b.booking_date,
    COUNT(*) AS total_bookings,
    SUM(CASE WHEN b.payment_status = 'Completed' THEN b.total_price ELSE 0 END) AS total_revenue,
    SUM(CASE WHEN b.status = 'Cancelled' THEN 1 ELSE 0 END) AS cancelled_bookings,
    SUM(CASE WHEN b.checked_in = TRUE THEN 1 ELSE 0 END) AS checked_in_passengers
FROM bookings b
GROUP BY b.booking_date;

-- Create a view for flight occupancy
CREATE OR REPLACE VIEW flight_occupancy AS
SELECT 
    f.flight_id,
    f.flight_number,
    f.departure_city,
    f.arrival_city,
    f.departure_time,
    f.total_seats,
    f.available_seats,
    (f.total_seats - f.available_seats) AS occupied_seats,
    ((f.total_seats - f.available_seats) / f.total_seats * 100) AS occupancy_percentage
FROM flights f;

-- Create a stored procedure for daily bookings report
DELIMITER //
CREATE PROCEDURE generate_daily_bookings_report(IN report_date VARCHAR(20))
BEGIN
    SELECT 
        b.booking_id,
        b.ticket_id,
        f.flight_number,
        CONCAT(p.first_name, ' ', p.last_name) AS passenger_name,
        b.seat_number,
        b.status,
        b.payment_status,
        b.total_price
    FROM bookings b
    JOIN flights f ON b.flight_id = f.flight_id
    JOIN passengers p ON b.passenger_id = p.passenger_id
    WHERE b.booking_date LIKE CONCAT(report_date, '%')
    ORDER BY b.booking_id;
END //
DELIMITER ;

-- Create a stored procedure for passenger manifest
DELIMITER //
CREATE PROCEDURE generate_passenger_manifest(IN flight_id_param INT)
BEGIN
    SELECT 
        b.seat_number,
        CONCAT(p.first_name, ' ', p.last_name) AS passenger_name,
        p.passport_number,
        p.nationality,
        b.status,
        b.checked_in,
        b.checkin_time
    FROM bookings b
    JOIN passengers p ON b.passenger_id = p.passenger_id
    WHERE b.flight_id = flight_id_param
      AND b.status != 'Cancelled'
    ORDER BY b.seat_number;
END //
DELIMITER ; 