/* 
Name= Diwash Adhikari
University ID =2407736
*/
class Account extends Customer { // superclass customer child class account
    private int balance; // Holds the balance of the account
    private int accountNumber; // Holds the unique account number

    // Constructor to initialize Account with first name, last name, account number, and balance
    Account(String FName, String LName, int accNum, int accBal) {
        setFirstName(FName); // Set the first name using the method from the superclass Customer
        setLastName(LName); // Set the last name using the method from the superclass Customer
        this.accountNumber = accNum; // Initialize account number
        this.balance = accBal; 
    }

    // Getter method to return the account balance
    public int getBalance() { //getBalance(): Returns the current balance of the account.
        return this.balance;
    }

    // Getter method to return the account number
    public int getAccountNum() { //getAccountNum(): Returns the account number of the account.
        return this.accountNumber;
    }

    // Method to deposit a specified amount to the account
    public void deposit(int amount) {
        this.balance += amount; // Increase the balance by the deposit amount
    }

    // Method to withdraw a specified amount from the account
    public void withdraw(int amount) {
        this.balance -= amount; // Decrease the balance by the withdrawal amount
    }
}
