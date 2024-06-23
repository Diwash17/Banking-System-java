/* 
Name= Diwash Adhikari
University ID =2407736
*/
import java.io.*; // Import necessary classes for file I/O operations
import java.util.LinkedList; // Import LinkedList for storing Account object
import java.awt.*; // Import AWT classes for GUI components (Abstract Window Toolkit)
import javax.swing.*;  // Import Swing classes for GUI components

public class Main {
    public static void main(String[] args) {
        // Create a LinkedList to hold Account objects
        LinkedList<Account> accounts = new LinkedList<>(); // Efficient Insertions and Deletions Dynamic Size
        // Define the file path for the CSV file containing account data
        String filePath = "Account.csv";
         // Try-with-resources block to read the account data from the CSV file
        // BufferedReader and FileReader are used to read text from the file
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line; // Variable to hold each line read from the file
            // Loop through each line in the file until the end is reached
            while ((line = reader.readLine()) != null) {
                // Split the line by commas to get individual account details
                String[] values = line.split(",");
                // Parse and assign the account details to variables
                String firstName = values[0];
                String lastName = values[1];
                int accountNum = Integer.parseInt(values[2]);
                int balance = Integer.parseInt(values[3]);
                accounts.add(new Account(firstName, lastName, accountNum, balance));
            }
        } catch (IOException e) {
            e.printStackTrace(); // Print the stack trace of the caught IOException to the standard error stream for debugging.
        }
        // Create a GUI object and pass the list of accounts to its constructor
        GUI gui = new GUI(accounts);
        gui.getContentPane().setBackground(new Color(150, 40, 20));
        // Set the default close operation of the GUI window
        // EXIT_ON_CLOSE ensures the application exits when the window is closed
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setSize(950, 700);
         // Make the GUI window visible to the user
        gui.setVisible(true);
    }
}
