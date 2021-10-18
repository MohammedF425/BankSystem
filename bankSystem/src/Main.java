import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Customer> bank = new ArrayList<>();

    public static void main(String[] args) {
        try {
            Scanner keyboard = new Scanner(System.in);
            while (true) {
                if (menu(keyboard).equalsIgnoreCase("0"))
                    break;
            }
            keyboard.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //makes the menu given ton the user
    public static String menu(Scanner keyboard) throws Exception {
        enterPrompt();
        return responsePrompt(keyboard);
    }

    //gives user the prompt of the program, such as creating a checking account
    public static void enterPrompt() {
        System.out.println("\nPlease enter a valid number");
        System.out.println("Press 0 to exit");
        System.out.println("Press 1 to become a new user in this bank");
        System.out.println("Press 2 to check balance");
        System.out.println("Press 3 to withdraw money");
        System.out.println("Press 4 to deposit money");
        System.out.println("Press 5 to make a Checking/Savings/CD account");
    }

    //responses to the users input to the enterPrompt and calls other methods based on the users input
    public static String responsePrompt(Scanner keyboard) throws Exception {
        String entered = keyboard.next();
        switch (entered) {
            case "0":
                break;
            case "1":
                addUser(keyboard);
                break;
            case "2":
                getBalance(keyboard);
                break;
            case "3":
                withdraw(keyboard);
                break;
            case "4":
                deposit(keyboard);
                break;
            case "5":
                addAccount(keyboard);
                break;
            default:
                System.out.println("Invalid Number");
        }
        return entered;
    }

    //askd for users first and last name, and sets all the characters to lower case except the first letter
    //which it sets to uppercase and returns the users first and last name in a String
    public static String userInfo(Scanner keyboard) {
        System.out.println("Enter first name and last name");
        String firstName = keyboard.next();
        String lastName = keyboard.next();
        firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1).toLowerCase();
        lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1).toLowerCase();
        return firstName + " " + lastName;
    }

    //adds user into the bank as a customer and if user is already a customer
    // it will tell the user that they are already a customer
    public static void addUser(Scanner keyboard) {
        String name = userInfo(keyboard);
        String firstName = name.substring(0, name.indexOf(" "));
        String lastName = name.substring(name.indexOf(" ") + 1);
        boolean add = true;
        for (Customer c : bank) {
            if (firstName.equals(c.getFirstName()) && lastName.equals(c.getLastName())) {
                add = false;
            }
        }
        if (add) {
            Customer customer = new Customer(firstName, lastName);
            bank.add(customer);
            System.out.println("Welcome, as a new customer " + customer.getFirstName() + " " + customer.getLastName());
            System.out.println();
        } else {
            System.out.println(firstName + " " + lastName + " is already a user");
        }
    }

    //gives user their balance for a certain account type and accounts for any
    //possible user error when they input certain things like account type
    public static void getBalance(Scanner keyboard) throws Exception {
        Customer result = getCustomer(keyboard);
        if (result == null) {
            System.out.println("Press 1 to try again or press 0 to exit");
            if (keyboard.nextInt() == 1)
                getBalance(keyboard);
            return;
        }
        System.out.println("Which account do you want to see.");
        System.out.println("Enter \"Checking\", \"Savings\", or \"CD\"");
        String accountType = keyboard.next();
        if (!result.balance(accountType, keyboard)) {
            System.out.println("Press 1 to try again or press 0 to exit");
            try {
                if (keyboard.nextInt() == 1)
                    getBalance(keyboard);
            } catch (Exception e) {
                throw new Exception("Invalid number entered");
            }
        }
    }

    //allows user to withdraw from a certain account type and accounts for any
    //possible user errors  when they input certain things like account type
    public static void withdraw(Scanner keyboard) throws Exception {
        Customer result = getCustomer(keyboard);
        if (result == null) {
            System.out.println("Press 1 to try again or press 0");
            if (keyboard.nextInt() == 1)
                withdraw(keyboard);
            return;
        }
        System.out.println("Which account you want to withdraw from");
        System.out.println("Enter \"Checking\", \"Savings\", or \"CD\"");
        String accountType = keyboard.next();
        System.out.println("Enter the amount you want to withdraw in dollars");
        double amountWithdraw = keyboard.nextDouble();

        if (!result.withdraw(new currency((int) (amountWithdraw * 100)), accountType, keyboard)) {
            System.out.println("Press 1 to try again or press 0 to exit");
            try {
                if (keyboard.nextInt() == 1)
                    withdraw(keyboard);
            } catch (Exception e) {
                throw new Exception("Invalid number entered for withdraw");
            }
        }
    }

    //allows user to deposit into a certain account type and accounts for any
    //possible user error when they input certain things like account type
    public static void deposit(Scanner keyboard) throws Exception {
        Customer result = getCustomer(keyboard);
        if (result == null) {
            System.out.println("Press 1 to try again or press 0");
            if (keyboard.nextInt() == 1)
                deposit(keyboard);
            return;
        }
        System.out.println("Which account you want to deposit to");
        System.out.println("Enter \"Checking\", \"Savings\", or \"CD\"");
        String accountType = keyboard.next();
        System.out.println("Enter the amount you want to deposit in dollars");
        double amountDeposit = keyboard.nextDouble();
        if (!result.deposit(new currency((int) (amountDeposit * 100)), accountType, keyboard)) {
            System.out.println("Press 1 to try again or press 0 to exit");
            try {
                if (keyboard.nextInt() == 1)
                    deposit(keyboard);
            } catch (Exception e) {
                throw new Exception("Invalid number entered for deposit");
            }
        }
    }

    //gets Customers information from the bank system, or customers arraylist based on their names
    public static Customer getCustomer(Scanner keyboard) {
        String name = userInfo(keyboard);
        String firstName = name.substring(0, name.indexOf(" "));
        String lastName = name.substring(name.indexOf(" ") + 1);
        for (Customer c : bank) {
            if (firstName.equals(c.getFirstName()) && lastName.equals(c.getLastName()))
                return c;
        }
        System.out.println("Invalid User");
        return null;
    }

    //add Account of the given type, accounts for many errors that can be made by user and allows them to redo task
    public static void addAccount(Scanner keyboard) throws Exception {
        Customer customer = getCustomer(keyboard);
        if (customer == null) {
            System.out.println("Press 1 to try again or press 0");
            if (keyboard.nextInt() == 1)
                addAccount(keyboard);
            return;
        }
        System.out.println("Enter the account type you want to make, \"Checking\", \"Savings\", or \"CD\"");
        String accountType = keyboard.next();
        Account result = addAccount(accountType, keyboard);
        if (result == null)
            return;
        customer.addAccount(result);
    }

    //verifies the entered type of account is an account type, if not, it asks user to either redo the task or to exit
    //if user enters valid account type it will make that account type
    public static Account addAccount(String type, Scanner keyboard) throws Exception {
        if (type.equalsIgnoreCase("Checking")) {
            return new Checking();
        } else if (type.equalsIgnoreCase("Savings")) {
            return new Savings();
        } else if (type.equalsIgnoreCase("CD")) {
            return new CD();
        } else {
            System.out.println("Invalid Account type");
            System.out.println("Press 1 to try again or 0");
            try {
                if (keyboard.nextInt() == 1)
                    addAccount(keyboard);
            } catch (Exception e) {
                throw new Exception("Invalid number entered");
            }
            return null;
        }
    }
}
