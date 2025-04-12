import java.awt.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

public class MainWindow extends JFrame {
    private JLabel statusLabel;
    private JPanel contentPanel;
    private CardLayout cardLayout;
    
    private JMenuBar menuBar;
    private JMenu fileMenu, flightsMenu, passengersMenu, bookingsMenu, checkInMenu, reportsMenu, adminMenu;
    
    private boolean isLoggedIn = false;
    private String currentUser = "";
    private String userRole = "";
    
    public MainWindow() {
        super("Airline Management System");
        initializeUI();
        showLoginDialog();
    }
    
    private void initializeUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        
        // Set modern look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Create menu bar
        createMenuBar();
        setJMenuBar(menuBar);
        
        // Create content panel with card layout
        contentPanel = new JPanel(new CardLayout());
        contentPanel.add(createWelcomePanel(), "WELCOME");
        add(contentPanel);
        
        // Create status bar
        JPanel statusBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusBar.setBorder(BorderFactory.createEtchedBorder());
        statusLabel = new JLabel("Not logged in");
        statusBar.add(statusLabel);
        add(statusBar, BorderLayout.SOUTH);
        
        // Set menus enabled/disabled based on login status
        setMenusEnabled(false);
    }
    
    private void createMenuBar() {
        menuBar = new JMenuBar();
        menuBar.setBackground(new Color(240, 240, 240));
        
        // File Menu
        fileMenu = new JMenu("File");
        JMenuItem logoutItem = new JMenuItem("Logout");
        JMenuItem exitItem = new JMenuItem("Exit");
        
        logoutItem.addActionListener(e -> logout());
        exitItem.addActionListener(e -> System.exit(0));
        
        fileMenu.add(logoutItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        
        // Flights Menu
        flightsMenu = new JMenu("Flights");
        JMenuItem addFlightItem = new JMenuItem("Add Flight");
        JMenuItem editFlightItem = new JMenuItem("Edit Flight");
        JMenuItem viewFlightsItem = new JMenuItem("View All Flights");
        
        addFlightItem.addActionListener(e -> {
            Dialogs.AddFlightDialog dialog = new Dialogs.AddFlightDialog(this);
            dialog.setVisible(true);
        });

        editFlightItem.addActionListener(e -> {
            Dialogs.EditFlightDialog dialog = new Dialogs.EditFlightDialog(this);
            dialog.setVisible(true);
        });

        viewFlightsItem.addActionListener(e -> {
            Dialogs.ViewFlightsDialog dialog = new Dialogs.ViewFlightsDialog(this);
            dialog.setVisible(true);
        });
        
        flightsMenu.add(addFlightItem);
        flightsMenu.add(editFlightItem);
        flightsMenu.add(viewFlightsItem);
        
        // Passengers Menu
        passengersMenu = new JMenu("Passengers");
        JMenuItem addPassengerItem = new JMenuItem("Add Passenger");
        JMenuItem viewPassengersItem = new JMenuItem("View All Passengers");
        
        addPassengerItem.addActionListener(e -> {
            Dialogs.AddPassengerDialog dialog = new Dialogs.AddPassengerDialog(this);
            dialog.setVisible(true);
        });

        viewPassengersItem.addActionListener(e -> {
            Dialogs.ViewPassengersDialog dialog = new Dialogs.ViewPassengersDialog(this);
            dialog.setVisible(true);
        });
        
        passengersMenu.add(addPassengerItem);
        passengersMenu.add(viewPassengersItem);
        
        // Bookings Menu
        bookingsMenu = new JMenu("Bookings");
        JMenuItem bookFlightItem = new JMenuItem("Add Booking");
        JMenuItem viewBookingsItem = new JMenuItem("View All Bookings");
        JMenuItem cancelTicketItem = new JMenuItem("Cancel Ticket");
        
        bookFlightItem.addActionListener(e -> {
            Dialogs.BookFlightDialog dialog = new Dialogs.BookFlightDialog(this);
            dialog.setVisible(true);
        });

        viewBookingsItem.addActionListener(e -> {
            Dialogs.ViewBookingsDialog dialog = new Dialogs.ViewBookingsDialog(this);
            dialog.setVisible(true);
        });

        cancelTicketItem.addActionListener(e -> {
            Dialogs.CancelTicketDialog dialog = new Dialogs.CancelTicketDialog(this);
            dialog.setVisible(true);
        });
        
        bookingsMenu.add(bookFlightItem);
        bookingsMenu.add(viewBookingsItem);
        bookingsMenu.add(cancelTicketItem);
        
        // Check-In Menu
        checkInMenu = new JMenu("Check-In");
        JMenuItem checkInItem = new JMenuItem("Check-In Passenger");
        
        checkInItem.addActionListener(e -> {
            Dialogs.CheckInDialog dialog = new Dialogs.CheckInDialog(this);
            dialog.setVisible(true);
        });
        
        checkInMenu.add(checkInItem);
        
        // Reports Menu
        reportsMenu = new JMenu("Reports");
        JMenuItem generateReportItem = new JMenuItem("Generate Reports");
        
        generateReportItem.addActionListener(e -> {
            Dialogs.ReportDialog dialog = new Dialogs.ReportDialog(this);
            dialog.setVisible(true);
        });
        
        reportsMenu.add(generateReportItem);
        
        // Admin Menu
        adminMenu = new JMenu("Administration");
        JMenuItem manageUsersItem = new JMenuItem("Manage Users");
        
        manageUsersItem.addActionListener(e -> openManageUsersDialog());
        
        adminMenu.add(manageUsersItem);
        
        // Add menus to menu bar
        menuBar.add(fileMenu);
        menuBar.add(flightsMenu);
        menuBar.add(passengersMenu);
        menuBar.add(bookingsMenu);
        menuBar.add(checkInMenu);
        menuBar.add(reportsMenu);
        menuBar.add(adminMenu);
    }
    
    private JPanel createWelcomePanel() {
        JPanel welcomePanel = new JPanel(new BorderLayout());
        welcomePanel.setBackground(new Color(255, 255, 255));
        
        // Header Panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(0, 120, 212));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel titleLabel = new JLabel("Welcome to Airline Management System");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        headerPanel.add(titleLabel, BorderLayout.WEST);
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM d, yyyy");
        JLabel dateLabel = new JLabel(dateFormat.format(new Date()));
        dateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        dateLabel.setForeground(Color.WHITE);
        headerPanel.add(dateLabel, BorderLayout.EAST);
        
        welcomePanel.add(headerPanel, BorderLayout.NORTH);
        
        // Quick Access Panel
        JPanel quickAccessPanel = new JPanel(new GridLayout(2, 3, 20, 20));
        quickAccessPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        quickAccessPanel.setBackground(new Color(240, 240, 240));
        
        // Create quick access buttons
        String[] buttonLabels = {
            "Search & Book Flights", "Check-In Passenger", "View Bookings",
            "Manage Flights", "Manage Passengers", "Generate Reports"
        };
        
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            button.setBackground(new Color(0, 120, 212));
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false);
            button.setBorderPainted(false);
            button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            button.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    button.setBackground(new Color(0, 100, 192));
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    button.setBackground(new Color(0, 120, 212));
                }
            });
            
            // Add action listeners based on button label
            switch (label) {
                case "Search & Book Flights":
                    button.addActionListener(e -> openSearchFlightDialog());
                    break;
                case "Check-In Passenger":
                    button.addActionListener(e -> openCheckInDialog());
                    break;
                case "View Bookings":
                    button.addActionListener(e -> openViewBookingsDialog());
                    break;
                case "Manage Flights":
                    button.addActionListener(e -> openViewFlightsDialog());
                    break;
                case "Manage Passengers":
                    button.addActionListener(e -> openViewPassengersDialog());
                    break;
                case "Generate Reports":
                    button.addActionListener(e -> openReportDialog());
                    break;
            }
            
            quickAccessPanel.add(button);
        }
        
        welcomePanel.add(quickAccessPanel, BorderLayout.CENTER);
        
        // Footer Panel
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        footerPanel.setBackground(new Color(240, 240, 240));
        footerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel footerLabel = new JLabel("© 2024 Airline Management System | Version 1.0");
        footerLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        footerLabel.setForeground(new Color(100, 100, 100));
        footerPanel.add(footerLabel);
        
        welcomePanel.add(footerPanel, BorderLayout.SOUTH);
        
        return welcomePanel;
    }
    
    private void setMenusEnabled(boolean enabled) {
        flightsMenu.setEnabled(enabled);
        passengersMenu.setEnabled(enabled);
        bookingsMenu.setEnabled(enabled);
        checkInMenu.setEnabled(enabled);
        reportsMenu.setEnabled(enabled);
        adminMenu.setEnabled(enabled && "ADMIN".equals(userRole));
    }
    
    private void showLoginDialog() {
        LoginDialog loginDialog = new LoginDialog(this);
        loginDialog.setVisible(true);
        
        if (loginDialog.isSuccessful()) {
            isLoggedIn = true;
            currentUser = loginDialog.getUsername();
            userRole = loginDialog.getUserRole();
            statusLabel.setText("Logged in as: " + currentUser + " (" + userRole + ")");
            setMenusEnabled(true);
        } else {
            System.exit(0);
        }
    }
    
    private void logout() {
        isLoggedIn = false;
        currentUser = "";
        userRole = "";
        statusLabel.setText("Not logged in");
        setMenusEnabled(false);
        showLoginDialog();
    }
    
    // Dialog opening methods
    private void openAddFlightDialog() {
        Dialogs.AddFlightDialog dialog = new Dialogs.AddFlightDialog(this);
        dialog.setVisible(true);
    }
    
    private void openViewFlightsDialog() {
        Dialogs.ViewFlightsDialog dialog = new Dialogs.ViewFlightsDialog(this);
        dialog.setVisible(true);
    }
    
    private void openAddPassengerDialog() {
        Dialogs.AddPassengerDialog dialog = new Dialogs.AddPassengerDialog(this);
        dialog.setVisible(true);
    }
    
    private void openViewPassengersDialog() {
        Dialogs.ViewPassengersDialog dialog = new Dialogs.ViewPassengersDialog(this);
        dialog.setVisible(true);
    }
    
    private void openViewBookingsDialog() {
        Dialogs.ViewBookingsDialog dialog = new Dialogs.ViewBookingsDialog(this);
        dialog.setVisible(true);
    }
    
    private void openSearchFlightDialog() {
        Dialogs.BookFlightDialog dialog = new Dialogs.BookFlightDialog(this);
        dialog.setVisible(true);
    }
    
    private void openCheckInDialog() {
        Dialogs.CheckInDialog dialog = new Dialogs.CheckInDialog(this);
        dialog.setVisible(true);
    }
    
    private void openCancelTicketDialog() {
        Dialogs.CancelTicketDialog dialog = new Dialogs.CancelTicketDialog(this);
        dialog.setVisible(true);
    }
    
    private void openReportDialog() {
        Dialogs.ReportDialog dialog = new Dialogs.ReportDialog(this);
        dialog.setVisible(true);
    }
    
    private void openManageUsersDialog() {
        // TODO: Implement ManageUsersDialog
        JOptionPane.showMessageDialog(this, "Manage Users functionality coming soon!");
    }
    
    // Main method
    public static void main(String[] args) {
        try {
            // Set Look and Feel
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            
            // Initialize the database
            initializeDatabase();
            
            // Start the application
            SwingUtilities.invokeLater(() -> {
                MainWindow window = new MainWindow();
                window.setVisible(true);
            });
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, 
                "Error starting application: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    private static void initializeDatabase() throws SQLException {
        Database.initialize();
    }
} 