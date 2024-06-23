/* 
Name= Diwash Adhikari
University ID =2407736
*/
import java.io.*; // Import necessary for file handling (FileReader, FileWriter, etc.)
import java.util.LinkedList; // Import necessary for using LinkedList
import javax.swing.*; // Import necessary for building GUI components
import java.awt.event.*; // Import necessary for handling events 

class GUI extends JFrame {
    // Class variables for storing account data, file path, and GUI components
    private StringBuilder sbAllData = new StringBuilder(); // To store all account data for display
    private LinkedList<Account> globalAccounts;  // List of all accounts
    private String filePath = "Account.csv"; // Path to the CSV file
    private JLabel showAllData, depositInp, depositAcc, withdrawInp, withdrawAcc, transferInp, transferacc1, transferacc2;
    private JButton showAllButton, depositButton, withdrawButton, transferButton;
    private JTextField depositInput, withdrawInput, transferAmount, accDeposit, accWithdraw, acc1Transfer, acc2Transfer;
    // Constructor to initialize the GUI with account data
    GUI(LinkedList<Account> accounts) {
        super("Banking System by Diwash Adhikari");
        setLayout(null); // Use null layout for absolute positioning
        this.globalAccounts = accounts; // Initialize the account list
        // Build initial account data string
        for (Account account : globalAccounts) {
            sbAllData.append(account.getFirstName()).append(",")
                    .append(account.getLastName()).append(",")
                    .append(account.getAccountNum()).append(",")
                    .append(account.getBalance()).append("\n");
        }
        // Initialize buttons
        showAllButton = new JButton("Show All");
        depositButton = new JButton("Deposit");
        withdrawButton = new JButton("Withdraw");
        transferButton = new JButton("Transfer");
        depositInput = new JTextField();
        accDeposit = new JTextField();
        withdrawInput = new JTextField();
        accWithdraw = new JTextField();
        transferAmount = new JTextField();
        acc1Transfer = new JTextField();
        acc2Transfer = new JTextField();
        showAllData = new JLabel("All Data");
        depositInp = new JLabel("Deposit Amount:");
        depositAcc = new JLabel("Deposit Account Number:");
        withdrawInp = new JLabel("Withdraw Amount:");
        withdrawAcc = new JLabel("Withdraw Account Number:");
        transferInp = new JLabel("Transfer Amount:");
        transferacc1 = new JLabel("From Account:");
        transferacc2 = new JLabel("To Account:");

        // Set bounds for the components
        depositInp.setBounds(20, 0, 200, 30);
        depositInput.setBounds(20, 20, 200, 30);
        depositAcc.setBounds(20, 50, 200, 30);
        accDeposit.setBounds(20, 70, 200, 30);
        withdrawInp.setBounds(20, 130, 200, 30);
        withdrawInput.setBounds(20, 150, 200, 30);
        withdrawAcc.setBounds(20, 180, 200, 30);
        accWithdraw.setBounds(20, 200, 200, 30);
        transferInp.setBounds(20, 260, 200, 30);
        transferAmount.setBounds(20, 280, 200, 30);
        transferacc1.setBounds(20, 310, 200, 30);
        acc1Transfer.setBounds(20, 330, 200, 30);
        transferacc2.setBounds(20, 360, 200, 30);
        acc2Transfer.setBounds(20, 380, 200, 30);
        showAllButton.setBounds(20, 450, 120, 30);
        depositButton.setBounds(230, 40, 120, 30);
        withdrawButton.setBounds(230, 170, 120, 30);
        transferButton.setBounds(230, 330, 120, 30);
        showAllData.setBounds(20, 500, 1400, 125);
        // Add components to the frame
        add(depositInput);
        add(accDeposit);
        add(withdrawInput);
        add(accWithdraw);
        add(transferAmount);
        add(acc1Transfer);
        add(acc2Transfer);
        add(showAllButton);
        add(depositButton);
        add(withdrawButton);
        add(transferButton);
        add(showAllData);
        add(depositInp);
        add(depositAcc);
        add(withdrawInp);
        add(withdrawAcc);
        add(transferInp);
        add(transferacc1);
        add(transferacc2);

        HandlerClass handler = new HandlerClass();
        depositButton.addActionListener(handler);
        withdrawButton.addActionListener(handler);
        transferButton.addActionListener(handler);
        showAllButton.addActionListener(handler);
    }
    // Method to update the UI and refresh account data
    private void updateUI() {
        sbAllData.setLength(0);  // Clear the StringBuilder
        for (Account account : globalAccounts) {
            sbAllData.append(account.getFirstName()).append(",")
                    .append(account.getLastName()).append(",")
                    .append(account.getAccountNum()).append(",")
                    .append(account.getBalance()).append("\n");
        }
        // Update the JLabel with new account data
        showAllData.setText("<html>" + sbAllData.toString().replaceAll("\n", "<br>") + "</html>");
        updateCSV();
    }
    // Method to write updated account data to the CSV file
    private void updateCSV() {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filePath)))) {
            for (Account account : globalAccounts) {
                writer.println(account.getFirstName() + "," + account.getLastName() + "," + account.getAccountNum()
                        + "," + account.getBalance());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Inner class to handle transactions
    public class Transaction {
        // Method to check if an account has sufficient balance for a transfer
        public boolean canTransfer(Account acc, int amount) {
            return acc.getBalance() >= amount;
        }
        // Method to transfer money between two accounts
        public void transfer(Account acc1, Account acc2, int amount) {
            if (canTransfer(acc1, amount)) {
                acc1.withdraw(amount);
                acc2.deposit(amount);
            } else {
                System.out.println("Insufficient balance for transfer from account: " + acc1.getAccountNum());
            }
        }
    }

    private class HandlerClass implements ActionListener {
        private Transaction transaction = new Transaction();

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == showAllButton) {
                updateUI();
            } else if (e.getSource() == depositButton) {
                try {
                    int accountNumber = Integer.parseInt(accDeposit.getText());
                    int amount = Integer.parseInt(depositInput.getText());
                    boolean accountFound = false;
                    for (Account account : globalAccounts) {
                        if (account.getAccountNum() == accountNumber) {
                            account.deposit(amount);
                            updateUI();
                            accountFound = true;
                            break;
                        }
                    }
                    if (!accountFound) {
                        JOptionPane.showMessageDialog(null, "No such account: " + accountNumber, "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } else if (e.getSource() == withdrawButton) {
                try {
                    int accountNumber = Integer.parseInt(accWithdraw.getText());
                    int amount = Integer.parseInt(withdrawInput.getText());
                    boolean accountFound = false;
                    for (Account account : globalAccounts) {
                        if (account.getAccountNum() == accountNumber) {
                            if (transaction.canTransfer(account, amount)) {
                                account.withdraw(amount);
                                updateUI();
                            } else {
                                JOptionPane.showMessageDialog(null, "Insufficient balance in account: " + accountNumber, "Error", JOptionPane.ERROR_MESSAGE);
                            }
                            accountFound = true;
                            break;
                        }
                    }
                    if (!accountFound) {
                        JOptionPane.showMessageDialog(null, "Account not found: " + accountNumber, "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } else if (e.getSource() == transferButton) {
                try {
                    int accountNumber1 = Integer.parseInt(acc1Transfer.getText());
                    int accountNumber2 = Integer.parseInt(acc2Transfer.getText());
                    int amount = Integer.parseInt(transferAmount.getText());

                    Account account1 = null, account2 = null;
                    boolean account1Found = false, account2Found = false;

                    for (Account account : globalAccounts) {
                        if (account.getAccountNum() == accountNumber1) {
                            account1 = account;
                            account1Found = true;
                        } else if (account.getAccountNum() == accountNumber2) {
                            account2 = account;
                            account2Found = true;
                        }
                        if (account1Found && account2Found) {
                            break;
                        }
                    }

                    if (account1Found && account2Found) {
                        if (transaction.canTransfer(account1, amount)) {
                            transaction.transfer(account1, account2, amount);
                            JOptionPane.showMessageDialog(null, "Successfully Transferred amount: " + amount,
                                    "From Account: " + accountNumber1 + " to Account: " + accountNumber2,
                                    JOptionPane.INFORMATION_MESSAGE);
                            updateUI();
                        } else {
                            JOptionPane.showMessageDialog(null, "Insufficient balance for transfer from account: " + accountNumber1, "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        if (!account1Found && !account2Found) {
                            JOptionPane.showMessageDialog(null, "No such accounts: " + accountNumber1 + " and " + accountNumber2, "Error", JOptionPane.ERROR_MESSAGE);
                        } else if (!account1Found) {
                            JOptionPane.showMessageDialog(null, "No such account: " + accountNumber1, "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "No such account: " + accountNumber2, "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
