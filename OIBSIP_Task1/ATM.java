import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class Transaction {
    private String type;
    private double amount;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }
}

class Account {
    private String userId;
    private String userPin;
    private double balance;
    private List<Transaction> transactionHistory;

    public Account(String userId, String userPin, double initialBalance) {
        this.userId = userId;
        this.userPin = userPin;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
    }

    public boolean verifyPin(String pin) {
        return userPin.equals(pin);
    }

    public String getUserId() {
        return userId;
    }

    public double getBalance() {
        return balance;
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add(new Transaction("Withdrawal", amount));
            JOptionPane.showMessageDialog(null, "Amount successfully withdrawn: $" + amount);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid amount or insufficient balance!");
        }
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add(new Transaction("Deposit", amount));
            JOptionPane.showMessageDialog(null, "Amount successfully deposited: $" + amount);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid amount!");
        }
    }

    public void transfer(double amount, Account recipientAccount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            recipientAccount.deposit(amount);
            transactionHistory.add(new Transaction("Transfer", amount));
            JOptionPane.showMessageDialog(null, "Amount successfully transferred: $" + amount);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid amount or insufficient balance!");
        }
    }

    public void printTransactionHistory() {
        StringBuilder sb = new StringBuilder();
        sb.append("Transaction History:\n");
        sb.append("Current Balance: $"+balance+"\n");
        for (Transaction transaction : transactionHistory) {
            sb.append(transaction.getType()).append(" - $").append(transaction.getAmount()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }
}

public class ATM {
    private Account account;
    private JFrame frame;

    public ATM(Account account) {
        this.account = account;
        createGUI();
    }

    private void createGUI() {
        frame = new JFrame("ATM");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(3, 1));

        JLabel label1 = new JLabel("User ID:");
        JLabel label2 = new JLabel("User PIN:");
        JButton button = new JButton("Login");

        JTextField userIdField = new JTextField();
        JPasswordField pinField = new JPasswordField();

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userId = userIdField.getText();
                String userPin = new String(pinField.getPassword());

                if (account.verifyPin(userPin) && userId.equals(account.getUserId())) {
                    JOptionPane.showMessageDialog(null, "Login successful!");
                    frame.dispose(); // Close the login screen
                    showOptions();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid User ID or PIN. Access denied!");
                }
            }
        });

        frame.add(label1);
        frame.add(userIdField);
        frame.add(label2);
        frame.add(pinField);
        frame.add(button);
        frame.setVisible(true);
    }

    private void showOptions() {
        JFrame optionsFrame = new JFrame("ATM Operations");
        optionsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        optionsFrame.setSize(400, 300);
        optionsFrame.setLayout(new GridLayout(5, 1));

        JButton transactionHistoryButton = new JButton("Transaction History");
        JButton withdrawButton = new JButton("Withdraw");
        JButton depositButton = new JButton("Deposit");
        JButton transferButton = new JButton("Transfer");
        JButton quitButton = new JButton("Quit");

        transactionHistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                account.printTransactionHistory();
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String withdrawAmount = JOptionPane.showInputDialog(null, "Enter the amount to withdraw:");
                if (withdrawAmount != null) {
                    double amount = Double.parseDouble(withdrawAmount);
                    account.withdraw(amount);
                }
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String depositAmount = JOptionPane.showInputDialog(null, "Enter the amount to deposit:");
                if (depositAmount != null) {
                    double amount = Double.parseDouble(depositAmount);
                    account.deposit(amount);
                }
            }
        });

        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String transferAmount = JOptionPane.showInputDialog(null, "Enter the amount to transfer:");
                if (transferAmount != null) {
                    double amount = Double.parseDouble(transferAmount);
                    String recipientId = JOptionPane.showInputDialog(null, "Enter the recipient's User ID:");
                    if (recipientId != null) {
                        if (recipientId.equals(account.getUserId())) {
                            JOptionPane.showMessageDialog(null, "Cannot transfer to the same account!");
                        } else {
                            Account recipientAccount = getRecipientAccount(recipientId);
                            if (recipientAccount != null) {
                                account.transfer(amount, recipientAccount);
                            } else {
                                JOptionPane.showMessageDialog(null, "Recipient account not found!");
                            }
                        }
                    }
                }
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Thank you for using the ATM. Goodbye!");
                System.exit(0);
            }
        });
        optionsFrame.add(transactionHistoryButton);
        optionsFrame.add(withdrawButton);
        optionsFrame.add(depositButton);
        optionsFrame.add(transferButton);
        optionsFrame.add(quitButton);
        optionsFrame.setVisible(true);
    }

    public static void main(String[] args) {
        Account account = new Account("user123", "1234", 1000.0);
        ATM atm = new ATM(account);
    }

    public static Account getRecipientAccount(String userId) {
        // This method can be replaced with actual account retrieval logic from a database or data source
        // For simplicity, we are using a hardcoded recipient account here
        if (userId.equals("recipient123")) {
            return new Account("recipient123", "5678", 0.0);
        } else {
            return null;
        }
    }
}
