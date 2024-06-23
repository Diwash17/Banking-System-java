/* 
Name= Diwash Adhikari
University ID =2407736
*/
class Customer {
    private String firstName; // Holds the first name of the customer
    private String lastName; // Holds the last name of the customer

    // Getter method to return the first name of the customer
    public String getFirstName() {
        return this.firstName; // 'this.firstName' refers to the instance variable, 'firstname' refers to the parameter
    }

    // Getter method to return the last name of the customer
    public String getLastName() {
        return this.lastName;
    }

    // Setter method to set the first name of the customer
    public void setFirstName(String firstName) {
        this.firstName = firstName; // Assign the provided first name to the instance variable
    }

    // Setter method to set the last name of the customer
    public void setLastName(String lastName) {
        this.lastName = lastName; // Assign the provided last name to the instance variable
    }
}
