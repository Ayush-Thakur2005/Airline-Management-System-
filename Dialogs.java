import java.awt.*;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.table.*;

public class Dialogs {
    // Flight Dialogs
    public static class AddFlightDialog extends JDialog {
        private JTextField flightNumberField, departureCityField, arrivalCityField, totalSeatsField, priceField;
        private JSpinner departureDateSpinner, departureTimeSpinner, arrivalDateSpinner, arrivalTimeSpinner;
        private JButton saveButton, cancelButton;
        
        public AddFlightDialog(Frame parent) {
            super(parent, "Add Flight", true);
            // Initialize components and layout
            setSize(500, 400);
            setLocationRelativeTo(parent);
            
            JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
            mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            
            // Form panel
            JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
            
            formPanel.add(new JLabel("Flight Number:"));
            flightNumberField = new JTextField();
            formPanel.add(flightNumberField);
            
            formPanel.add(new JLabel("Departure City:"));
            departureCityField = new JTextField();
            formPanel.add(departureCityField);
            
            formPanel.add(new JLabel("Arrival City:"));
            arrivalCityField = new JTextField();
            formPanel.add(arrivalCityField);
            
            formPanel.add(new JLabel("Total Seats:"));
            totalSeatsField = new JTextField();
            formPanel.add(totalSeatsField);
            
            formPanel.add(new JLabel("Price:"));
            priceField = new JTextField();
            formPanel.add(priceField);
            
            mainPanel.add(formPanel, BorderLayout.CENTER);
            
            // Button panel
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            
            saveButton = new JButton("Save");
            saveButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Flight added successfully!"));
            
            cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(e -> dispose());
            
            buttonPanel.add(saveButton);
            buttonPanel.add(cancelButton);
            mainPanel.add(buttonPanel, BorderLayout.SOUTH);
            
            add(mainPanel);
        }
    }

    public static class EditFlightDialog extends JDialog {
        private JComboBox<String> flightSelector;
        private JTextField flightNumberField, departureCityField, arrivalCityField, totalSeatsField, priceField;
        private JSpinner departureDateSpinner, departureTimeSpinner, arrivalDateSpinner, arrivalTimeSpinner;
        private JButton saveButton, cancelButton;
        
        public EditFlightDialog(Frame parent) {
            super(parent, "Edit Flight", true);
            // Initialize components and layout
            setSize(500, 450);
            setLocationRelativeTo(parent);
            
            // TODO: Implement full dialog functionality
        }
    }

    public static class ViewFlightsDialog extends JDialog {
        private JTable flightsTable;
        private JScrollPane scrollPane;
        private JButton editButton, deleteButton, refreshButton, closeButton;
        
        public ViewFlightsDialog(Frame parent) {
            super(parent, "View Flights", true);
            // Initialize components and layout
            setSize(800, 600);
            setLocationRelativeTo(parent);
            
            JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
            mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            
            // Table panel
            String[] columnNames = {"Flight Number", "Departure", "Arrival", "Date", "Time", "Seats", "Price"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            
            // Add sample data
            model.addRow(new Object[]{"AA123", "New York", "Los Angeles", "2023-04-15", "08:00", "150", "$350"});
            model.addRow(new Object[]{"UA456", "Chicago", "Miami", "2023-04-16", "10:30", "180", "$420"});
            
            flightsTable = new JTable(model);
            flightsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            flightsTable.getTableHeader().setReorderingAllowed(false);
            
            scrollPane = new JScrollPane(flightsTable);
            mainPanel.add(scrollPane, BorderLayout.CENTER);
            
            // Button panel
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            
            editButton = new JButton("Edit");
            editButton.addActionListener(e -> {
                int selectedRow = flightsTable.getSelectedRow();
                if (selectedRow != -1) {
                    String flightNumber = (String) flightsTable.getValueAt(selectedRow, 0);
                    JOptionPane.showMessageDialog(this, "Edit flight: " + flightNumber);
                } else {
                    JOptionPane.showMessageDialog(this, "Please select a flight to edit.");
                }
            });
            
            deleteButton = new JButton("Delete");
            deleteButton.addActionListener(e -> {
                int selectedRow = flightsTable.getSelectedRow();
                if (selectedRow != -1) {
                    String flightNumber = (String) flightsTable.getValueAt(selectedRow, 0);
                    int confirm = JOptionPane.showConfirmDialog(this, 
                        "Are you sure you want to delete flight " + flightNumber + "?", 
                        "Confirm Delete", 
                        JOptionPane.YES_NO_OPTION);
                    
                    if (confirm == JOptionPane.YES_OPTION) {
                        ((DefaultTableModel) flightsTable.getModel()).removeRow(selectedRow);
                        JOptionPane.showMessageDialog(this, "Flight deleted successfully!");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Please select a flight to delete.");
                }
            });
            
            refreshButton = new JButton("Refresh");
            refreshButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Refreshing flight data..."));
            
            closeButton = new JButton("Close");
            closeButton.addActionListener(e -> dispose());
            
            buttonPanel.add(editButton);
            buttonPanel.add(deleteButton);
            buttonPanel.add(refreshButton);
            buttonPanel.add(closeButton);
            mainPanel.add(buttonPanel, BorderLayout.SOUTH);
            
            add(mainPanel);
        }
    }

    // Passenger Dialogs
    public static class AddPassengerDialog extends JDialog {
        private JTextField firstNameField, lastNameField, nationalityField, passportField, phoneField, emailField;
        private JComboBox<String> genderComboBox;
        private JSpinner dobSpinner;
        private JButton saveButton, cancelButton;
        
        public AddPassengerDialog(Frame parent) {
            super(parent, "Add Passenger", true);
            // Initialize components and layout
            setSize(500, 400);
            setLocationRelativeTo(parent);
            
            JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
            mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            
            // Form panel
            JPanel formPanel = new JPanel(new GridLayout(8, 2, 10, 10));
            
            formPanel.add(new JLabel("First Name:"));
            firstNameField = new JTextField();
            formPanel.add(firstNameField);
            
            formPanel.add(new JLabel("Last Name:"));
            lastNameField = new JTextField();
            formPanel.add(lastNameField);
            
            formPanel.add(new JLabel("Gender:"));
            genderComboBox = new JComboBox<>(new String[]{"Male", "Female", "Other"});
            formPanel.add(genderComboBox);
            
            formPanel.add(new JLabel("Nationality:"));
            nationalityField = new JTextField();
            formPanel.add(nationalityField);
            
            formPanel.add(new JLabel("Passport:"));
            passportField = new JTextField();
            formPanel.add(passportField);
            
            formPanel.add(new JLabel("Phone:"));
            phoneField = new JTextField();
            formPanel.add(phoneField);
            
            formPanel.add(new JLabel("Email:"));
            emailField = new JTextField();
            formPanel.add(emailField);
            
            mainPanel.add(formPanel, BorderLayout.CENTER);
            
            // Button panel
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            
            saveButton = new JButton("Save");
            saveButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Passenger added successfully!"));
            
            cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(e -> dispose());
            
            buttonPanel.add(saveButton);
            buttonPanel.add(cancelButton);
            mainPanel.add(buttonPanel, BorderLayout.SOUTH);
            
            add(mainPanel);
        }
    }

    public static class ViewPassengersDialog extends JDialog {
        private JTable passengersTable;
        private JScrollPane scrollPane;
        private JButton editButton, deleteButton, refreshButton, closeButton;
        
        public ViewPassengersDialog(Frame parent) {
            super(parent, "View Passengers", true);
            // Initialize components and layout
            setSize(800, 600);
            setLocationRelativeTo(parent);
            
            JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
            mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            
            // Table panel
            String[] columnNames = {"ID", "First Name", "Last Name", "Gender", "Passport", "Phone", "Email"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            
            // Add sample data
            model.addRow(new Object[]{"1", "John", "Doe", "Male", "AB123456", "123-456-7890", "john@example.com"});
            model.addRow(new Object[]{"2", "Jane", "Smith", "Female", "CD789012", "987-654-3210", "jane@example.com"});
            
            passengersTable = new JTable(model);
            passengersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            passengersTable.getTableHeader().setReorderingAllowed(false);
            
            scrollPane = new JScrollPane(passengersTable);
            mainPanel.add(scrollPane, BorderLayout.CENTER);
            
            // Button panel
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            
            editButton = new JButton("Edit");
            deleteButton = new JButton("Delete");
            refreshButton = new JButton("Refresh");
            closeButton = new JButton("Close");
            
            closeButton.addActionListener(e -> dispose());
            
            buttonPanel.add(editButton);
            buttonPanel.add(deleteButton);
            buttonPanel.add(refreshButton);
            buttonPanel.add(closeButton);
            mainPanel.add(buttonPanel, BorderLayout.SOUTH);
            
            add(mainPanel);
        }
    }

    // Booking Dialogs
    public static class BookFlightDialog extends JDialog {
        private JComboBox<String> flightSelector, passengerSelector;
        private JTextField seatNumberField;
        private JTextArea flightDetailsArea;
        private JButton bookButton, cancelButton;
        
        public BookFlightDialog(Frame parent) {
            super(parent, "Book Flight", true);
            // Initialize components and layout
            setSize(600, 500);
            setLocationRelativeTo(parent);
            
            JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
            mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            
            // Form panel
            JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
            
            formPanel.add(new JLabel("Select Flight:"));
            flightSelector = new JComboBox<>(new String[]{"AA123 - New York to Los Angeles", "UA456 - Chicago to Miami"});
            formPanel.add(flightSelector);
            
            formPanel.add(new JLabel("Select Passenger:"));
            passengerSelector = new JComboBox<>(new String[]{"John Doe (AB123456)", "Jane Smith (CD789012)"});
            formPanel.add(passengerSelector);
            
            formPanel.add(new JLabel("Seat Number:"));
            seatNumberField = new JTextField();
            formPanel.add(seatNumberField);
            
            mainPanel.add(formPanel, BorderLayout.NORTH);
            
            // Flight details panel
            JPanel detailsPanel = new JPanel(new BorderLayout());
            detailsPanel.setBorder(BorderFactory.createTitledBorder("Flight Details"));
            
            flightDetailsArea = new JTextArea();
            flightDetailsArea.setEditable(false);
            flightDetailsArea.setText("Flight: AA123\nDeparture: New York (JFK)\nArrival: Los Angeles (LAX)\nDate: April 15, 2023\nTime: 08:00 AM\nPrice: $350");
            
            detailsPanel.add(new JScrollPane(flightDetailsArea), BorderLayout.CENTER);
            mainPanel.add(detailsPanel, BorderLayout.CENTER);
            
            // Button panel
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            
            bookButton = new JButton("Book Flight");
            bookButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Flight booked successfully!"));
            
            cancelButton = new JButton("Cancel");
            cancelButton.addActionListener(e -> dispose());
            
            buttonPanel.add(bookButton);
            buttonPanel.add(cancelButton);
            mainPanel.add(buttonPanel, BorderLayout.SOUTH);
            
            add(mainPanel);
        }
    }

    public static class ViewBookingsDialog extends JDialog {
        private JTable bookingsTable;
        private JScrollPane scrollPane;
        private JButton checkInButton, cancelButton, closeButton;
        
        public ViewBookingsDialog(Frame parent) {
            super(parent, "View Bookings", true);
            // Initialize components and layout
            setSize(800, 600);
            setLocationRelativeTo(parent);
            
            JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
            mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            
            // Table panel
            String[] columnNames = {"Booking ID", "Flight", "Passenger", "Date", "Seat", "Status"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            
            // Add sample data
            model.addRow(new Object[]{"BK001", "AA123", "John Doe", "2023-04-15", "12A", "Confirmed"});
            model.addRow(new Object[]{"BK002", "UA456", "Jane Smith", "2023-04-16", "5B", "Pending"});
            
            bookingsTable = new JTable(model);
            bookingsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            bookingsTable.getTableHeader().setReorderingAllowed(false);
            
            scrollPane = new JScrollPane(bookingsTable);
            mainPanel.add(scrollPane, BorderLayout.CENTER);
            
            // Button panel
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            
            checkInButton = new JButton("Check-In");
            checkInButton.addActionListener(e -> {
                int selectedRow = bookingsTable.getSelectedRow();
                if (selectedRow != -1) {
                    String bookingId = (String) bookingsTable.getValueAt(selectedRow, 0);
                    JOptionPane.showMessageDialog(this, "Check-in for booking: " + bookingId);
                } else {
                    JOptionPane.showMessageDialog(this, "Please select a booking for check-in.");
                }
            });
            
            cancelButton = new JButton("Cancel Booking");
            closeButton = new JButton("Close");
            closeButton.addActionListener(e -> dispose());
            
            buttonPanel.add(checkInButton);
            buttonPanel.add(cancelButton);
            buttonPanel.add(closeButton);
            mainPanel.add(buttonPanel, BorderLayout.SOUTH);
            
            add(mainPanel);
        }
    }

    public static class CancelTicketDialog extends JDialog {
        private JTextField ticketIdField;
        private JTextArea bookingDetailsArea;
        private JButton searchButton, cancelTicketButton, closeButton;
        
        public CancelTicketDialog(Frame parent) {
            super(parent, "Cancel Ticket", true);
            // Initialize components and layout
            setSize(600, 500);
            setLocationRelativeTo(parent);
            
            JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
            mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            
            // Search panel
            JPanel searchPanel = new JPanel(new BorderLayout(10, 0));
            searchPanel.add(new JLabel("Ticket ID:"), BorderLayout.WEST);
            
            ticketIdField = new JTextField();
            searchPanel.add(ticketIdField, BorderLayout.CENTER);
            
            searchButton = new JButton("Search");
            searchButton.addActionListener(e -> {
                String ticketId = ticketIdField.getText().trim();
                if (!ticketId.isEmpty()) {
                    bookingDetailsArea.setText("Booking ID: BK001\nFlight: AA123 - New York to Los Angeles\nPassenger: John Doe\nDate: April 15, 2023\nSeat: 12A\nStatus: Confirmed");
                    cancelTicketButton.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Please enter a ticket ID.");
                }
            });
            searchPanel.add(searchButton, BorderLayout.EAST);
            
            mainPanel.add(searchPanel, BorderLayout.NORTH);
            
            // Booking details panel
            JPanel detailsPanel = new JPanel(new BorderLayout());
            detailsPanel.setBorder(BorderFactory.createTitledBorder("Booking Details"));
            
            bookingDetailsArea = new JTextArea();
            bookingDetailsArea.setEditable(false);
            
            detailsPanel.add(new JScrollPane(bookingDetailsArea), BorderLayout.CENTER);
            mainPanel.add(detailsPanel, BorderLayout.CENTER);
            
            // Button panel
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            
            cancelTicketButton = new JButton("Cancel Ticket");
            cancelTicketButton.setEnabled(false);
            cancelTicketButton.addActionListener(e -> {
                int confirm = JOptionPane.showConfirmDialog(this, 
                    "Are you sure you want to cancel this ticket?", 
                    "Confirm Cancellation", 
                    JOptionPane.YES_NO_OPTION);
                
                if (confirm == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(this, "Ticket cancelled successfully!");
                    bookingDetailsArea.setText("");
                    ticketIdField.setText("");
                    cancelTicketButton.setEnabled(false);
                }
            });
            
            closeButton = new JButton("Close");
            closeButton.addActionListener(e -> dispose());
            
            buttonPanel.add(cancelTicketButton);
            buttonPanel.add(closeButton);
            mainPanel.add(buttonPanel, BorderLayout.SOUTH);
            
            add(mainPanel);
        }
    }

    // Check-In Dialogs
    public static class CheckInDialog extends JDialog {
        private JTextField ticketIdField;
        private JTextArea bookingDetailsArea;
        private JButton searchButton, checkInButton, closeButton;
        
        public CheckInDialog(Frame parent) {
            super(parent, "Check-In", true);
            // Initialize components and layout
            setSize(600, 500);
            setLocationRelativeTo(parent);
            
            JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
            mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            
            // Search panel
            JPanel searchPanel = new JPanel(new BorderLayout(10, 0));
            searchPanel.add(new JLabel("Ticket ID:"), BorderLayout.WEST);
            
            ticketIdField = new JTextField();
            searchPanel.add(ticketIdField, BorderLayout.CENTER);
            
            searchButton = new JButton("Search");
            searchButton.addActionListener(e -> {
                String ticketId = ticketIdField.getText().trim();
                if (!ticketId.isEmpty()) {
                    bookingDetailsArea.setText("Booking ID: BK001\nFlight: AA123 - New York to Los Angeles\nPassenger: John Doe\nDate: April 15, 2023\nTime: 08:00 AM\nSeat: 12A\nStatus: Confirmed");
                    checkInButton.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Please enter a ticket ID.");
                }
            });
            searchPanel.add(searchButton, BorderLayout.EAST);
            
            mainPanel.add(searchPanel, BorderLayout.NORTH);
            
            // Booking details panel
            JPanel detailsPanel = new JPanel(new BorderLayout());
            detailsPanel.setBorder(BorderFactory.createTitledBorder("Booking Details"));
            
            bookingDetailsArea = new JTextArea();
            bookingDetailsArea.setEditable(false);
            
            detailsPanel.add(new JScrollPane(bookingDetailsArea), BorderLayout.CENTER);
            mainPanel.add(detailsPanel, BorderLayout.CENTER);
            
            // Button panel
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            
            checkInButton = new JButton("Check-In");
            checkInButton.setEnabled(false);
            checkInButton.addActionListener(e -> {
                JOptionPane.showMessageDialog(this, "Check-in successful!");
                dispose();
            });
            
            closeButton = new JButton("Close");
            closeButton.addActionListener(e -> dispose());
            
            buttonPanel.add(checkInButton);
            buttonPanel.add(closeButton);
            mainPanel.add(buttonPanel, BorderLayout.SOUTH);
            
            add(mainPanel);
        }
        
        public CheckInDialog(Frame parent, String ticketId) {
            this(parent);
            ticketIdField.setText(ticketId);
            // Auto-search for the booking
        }
    }

    public static class BoardingPassDialog extends JDialog {
        private JTextArea boardingPassArea;
        private JButton printButton, closeButton;
        
        public BoardingPassDialog(Frame parent) {
            super(parent, "Boarding Pass", true);
            // Initialize components and layout
            setSize(500, 600);
            setLocationRelativeTo(parent);
            
            // TODO: Implement full dialog functionality
        }
    }

    // Report Dialogs
    public static class ReportDialog extends JDialog {
        private JComboBox<String> reportTypeComboBox;
        private JPanel inputPanel;
        private CardLayout cardLayout;
        private JTextArea reportTextArea;
        private JButton generateButton, closeButton;
        
        public ReportDialog(Frame parent) {
            super(parent, "Reports", true);
            // Initialize components and layout
            setSize(800, 600);
            setLocationRelativeTo(parent);
            
            JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
            mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            
            // Selection panel
            JPanel selectionPanel = new JPanel(new BorderLayout(10, 0));
            selectionPanel.add(new JLabel("Report Type:"), BorderLayout.WEST);
            
            reportTypeComboBox = new JComboBox<>(new String[]{
                "Daily Bookings", 
                "Flight Occupancy", 
                "Revenue by Route"
            });
            selectionPanel.add(reportTypeComboBox, BorderLayout.CENTER);
            
            mainPanel.add(selectionPanel, BorderLayout.NORTH);
            
            // Report panel
            JPanel reportPanel = new JPanel(new BorderLayout());
            reportPanel.setBorder(BorderFactory.createTitledBorder("Report"));
            
            reportTextArea = new JTextArea();
            reportTextArea.setEditable(false);
            reportTextArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
            
            reportPanel.add(new JScrollPane(reportTextArea), BorderLayout.CENTER);
            mainPanel.add(reportPanel, BorderLayout.CENTER);
            
            // Button panel
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            
            generateButton = new JButton("Generate Report");
            generateButton.addActionListener(e -> {
                String reportType = (String) reportTypeComboBox.getSelectedItem();
                String report = "";
                
                switch (reportType) {
                    case "Daily Bookings":
                        report = "Daily Bookings Report - " + new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()) + "\n\n";
                        report += "Total Bookings: 25\n";
                        report += "Confirmed: 20\n";
                        report += "Pending: 5\n\n";
                        report += "Flight    Passengers    Revenue\n";
                        report += "----------------------------\n";
                        report += "AA123     15            $5,250\n";
                        report += "UA456     10            $4,200\n";
                        break;
                    case "Flight Occupancy":
                        report = "Flight Occupancy Report\n\n";
                        report += "Flight: AA123 - New York to Los Angeles\n";
                        report += "Date: April 15, 2023\n";
                        report += "Total Seats: 150\n";
                        report += "Booked Seats: 120\n";
                        report += "Occupancy Rate: 80%\n";
                        break;
                    case "Revenue by Route":
                        report = "Revenue by Route Report\n\n";
                        report += "Period: April 1-30, 2023\n\n";
                        report += "Route                  Passengers    Revenue\n";
                        report += "---------------------------------------------\n";
                        report += "New York - Los Angeles 350           $122,500\n";
                        report += "Chicago - Miami        280           $117,600\n";
                        report += "Boston - Seattle       200           $90,000\n";
                        break;
                }
                
                reportTextArea.setText(report);
            });
            
            closeButton = new JButton("Close");
            closeButton.addActionListener(e -> dispose());
            
            buttonPanel.add(generateButton);
            buttonPanel.add(closeButton);
            mainPanel.add(buttonPanel, BorderLayout.SOUTH);
            
            add(mainPanel);
        }
    }

    public static class ReportGenerator {
        public static String generateDailyBookingsReport(java.util.Date date) {
            // TODO: Implement report generation
            return "Daily Bookings Report for " + new SimpleDateFormat("yyyy-MM-dd").format(date);
        }
        
        public static String generateFlightOccupancyReport(String flightNumber) {
            // TODO: Implement report generation
            return "Flight Occupancy Report for " + flightNumber;
        }
        
        public static String generateRevenueByRouteReport(java.util.Date startDate, java.util.Date endDate) {
            // TODO: Implement report generation
            return "Revenue by Route Report from " + 
                   new SimpleDateFormat("yyyy-MM-dd").format(startDate) + 
                   " to " + 
                   new SimpleDateFormat("yyyy-MM-dd").format(endDate);
        }
    }
} 