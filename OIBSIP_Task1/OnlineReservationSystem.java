import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OnlineReservationSystem extends JFrame {
    private static String username = "admin";
    private static String password = "password";
    private static boolean isLoggedIn = false;

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton exitButton;

    public OnlineReservationSystem() {
        setTitle("Online Reservation System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        exitButton = new JButton("Exit");

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);
        add(exitButton);
    }

    private void login() {
        String enteredUsername = usernameField.getText();
        String enteredPassword = new String(passwordField.getPassword());

        if (enteredUsername.equals(username) && enteredPassword.equals(password)) {
            isLoggedIn = true;
            showMainMenu();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password. Login failed!", "Login Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showMainMenu() {
        setTitle("Online Reservation System - Main Menu");
        getContentPane().removeAll();
        setLayout(new GridLayout(3, 1));

        JButton makeReservationButton = new JButton("Make a Reservation");
        JButton cancelReservationButton = new JButton("Cancel a Reservation");
        JButton logoutButton = new JButton("Logout");

        makeReservationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                makeReservation();
            }
        });

        cancelReservationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancelReservation();
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                isLoggedIn = false;
                showLoginScreen();
            }
        });

        add(makeReservationButton);
        add(cancelReservationButton);
        add(logoutButton);

        revalidate();
        repaint();
    }

    private void makeReservation() {
        JPanel panel = new JPanel(new GridLayout(7, 2));

        JTextField nameField = new JTextField();
        JTextField trainNumberField = new JTextField();
        JTextField trainNameField = new JTextField();
        JTextField classTypeField = new JTextField();
        JTextField dateField = new JTextField();
        JTextField sourceField = new JTextField();
        JTextField destinationField = new JTextField();

        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Train Number:"));
        panel.add(trainNumberField);
        panel.add(new JLabel("Train Name:"));
        panel.add(trainNameField);
        panel.add(new JLabel("Class Type:"));
        panel.add(classTypeField);
        panel.add(new JLabel("Date of Journey:"));
        panel.add(dateField);
        panel.add(new JLabel("Source:"));
        panel.add(sourceField);
        panel.add(new JLabel("Destination:"));
        panel.add(destinationField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Make a Reservation", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String name = nameField.getText();
            int trainNumber = Integer.parseInt(trainNumberField.getText());
            String trainName = trainNameField.getText();
            String classType = classTypeField.getText();
            String date = dateField.getText();
            String source = sourceField.getText();
            String destination = destinationField.getText();

            // Perform the reservation logic here

            JOptionPane.showMessageDialog(this, "Reservation successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void cancelReservation() {
        JPanel panel = new JPanel(new GridLayout(1, 1));

        JTextField pnrField = new JTextField();

        panel.add(new JLabel("PNR Number:"));
        panel.add(pnrField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Cancel a Reservation", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String pnrNumber = pnrField.getText();

            // Retrieve reservation details based on the PNR number
            // Perform cancellation logic here

            JOptionPane.showMessageDialog(this, "Reservation with PNR " + pnrNumber + " cancelled successfully!", "Cancellation", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void showLoginScreen() {
        setTitle("Online Reservation System");
        getContentPane().removeAll();
        setLayout(new GridLayout(3, 2));

        usernameField.setText("");
        passwordField.setText("");

        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(loginButton);
        add(exitButton);

        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                OnlineReservationSystem reservationSystem = new OnlineReservationSystem();
                reservationSystem.setVisible(true);

                if (isLoggedIn) {
                    reservationSystem.showMainMenu();
                }
            }
        });
    }
}
