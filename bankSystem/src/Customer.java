import java.util.ArrayList;
import java.util.Scanner;

public class Customer {
    public String firstName;
    public String lastName;
    public int numAccounts = 0;
    ArrayList<Account> accounts;

    public Customer(String first, String last) {
        firstName = first;
        lastName = last;
        accounts = new ArrayList<>(3);
        //creates 3 empty spots in the array, index 0 for checking,1 for savings, 2 for cd
        for (int i = 0; i < 3; i++) accounts.add(null);
    }
    //addAccount of the "instanceof type" of the parameter unless one already exists
    //if parameter is not of a type of a subclass of account it will return an exception
    public void addAccount(Account account) {
        if (account instanceof Checking) {
            if (accounts.get(0) == null) {
                accounts.set(0, account);
                System.out.println("Checking account successfully made");
            } else {
                System.out.println("There is a preexisting Checking account");
                return;
            }
        } else if (account instanceof Savings) {
            if (accounts.get(1) == null) {
                accounts.set(1, account);
                System.out.println("Savings account successfully made");
            } else {
                System.out.println("There is a preexisting Savings account");
                return;
            }
        } else if (account instanceof CD) {
            if (accounts.get(2) == null) {
                accounts.set(2, account);
                System.out.println("CD account successfully made");
            } else {
                System.out.println("There is a preexisting CD account");
                return;
            }
        } else {
            new Exception("Enter invalid account");
            return;
        }
        numAccounts++;
    }
    //getter for first name
    public String getFirstName() {
        return firstName;
    }
    //getter for last name
    public String getLastName() {
        return lastName;
    }
    //deposits money, currency money, into a certain account, String type
    //if type account does not exist it will allow the user to make one
    public boolean deposit(currency money, String type, Scanner keyboard) throws Exception {
        if (type.equalsIgnoreCase("Checking")) {
            addChecking(keyboard);//checks if account exists and allows user to create one if it doesnt exist
            accounts.get(0).deposit(money);
            return true;
        } else if (type.equalsIgnoreCase("Savings")) {
            addSavings(keyboard);//checks if account exists and allows user to create one if it doesnt exist
            accounts.get(1).deposit(money);
            return true;
        } else if (type.equalsIgnoreCase("CD")) {
            addCD(keyboard);//checks if account exists and allows user to create one if it doesnt exist
            accounts.get(2).deposit(money);
            return true;
        } else {
            System.out.println("invalid account type/Account does not exist");
            return false;
        }
    }
    //withdraws money, currency money, into a certain account, String type
    //if type account does not exist it will allow the user to make one
    public boolean withdraw(currency money, String type, Scanner keyboard) throws Exception {
        if (type.equalsIgnoreCase("Checking")) {
            addChecking(keyboard);//checks if account exists and allows user to create one if it doesnt exist
            accounts.get(0).withdraw(money);
            return true;
        } else if (type.equalsIgnoreCase("Savings")) {
            addSavings(keyboard);//checks if account exists and allows user to create one if it doesnt exist
            accounts.get(1).withdraw(money);
            return true;
        } else if (type.equalsIgnoreCase("CD")) {
            addCD(keyboard);//checks if account exists and allows user to create one if it doesnt exist
            accounts.get(2).withdraw(money);
            return true;
        } else {
            System.out.println("Invalid account type/Account does not exist");
            return false;
        }
    }

    public boolean balance(String type, Scanner keyboard) {
        if (type.equalsIgnoreCase("Checking")) {
            if (addChecking(keyboard)) return true;
            System.out.println(accounts.get(0).toString());
            return true;
        } else if (type.equalsIgnoreCase("Savings")) {
            if (addSavings(keyboard)) return true;
            System.out.println(accounts.get(1).toString());
            return true;
        } else if (type.equalsIgnoreCase("CD")) {
            if (addCD(keyboard)) return true;
            System.out.println(accounts.get(2).toString());
            return true;
        } else {
            System.out.println("Invalid account type/Account does not exist");
            return false;
        }

    }
    //Checking if checking account exists and if it doesn't it allows the user to create one
    public boolean addChecking(Scanner keyboard) {
        if (accounts.get(0) == null) {
            System.out.println("A checking account does not exist");
            System.out.println("Press 1 if you want to add a checking account or press 0");
            if (keyboard.nextInt() == 1) {
                addAccount(new Checking());
                return true;
            }
        }
        return false;
    }
    //Checking if savings account exists and if it doesn't it allows the user to create one
    public boolean addSavings(Scanner keyboard) {
        if (accounts.get(1) == null) {
            System.out.println("A savings account does not exist");
            System.out.println("Press 1 if you want to add a savings account or press 0");
            if (keyboard.nextInt() == 1) {
                addAccount(new Savings());
                return true;
            }
        }
        return false;
    }
    //Checking if CD account exists and if it doesn't it allows the user to create one
    public boolean addCD(Scanner keyboard) {
        if (accounts.get(2) == null) {
            System.out.println("A CD account does not exist");
            System.out.println("Press 1 if you want to add a CD account or press 0");
            if (keyboard.nextInt() == 1) {
                addAccount(new CD());
                return true;
            }
        }
        return false;
    }
}
