public class Account {
    //represents the account balance and is accessible to subclasses of Account
    protected int initAmount;

    //Constructor sets the accounts initAmount to the cents of the given currency
    public Account(currency initialDeposit) {
        initAmount = initialDeposit.cents;
    }

    //subtracts the initAmount by the cents of the given currency to account for money withdrawn
    public void withdraw(currency money) throws Exception {
        initAmount -= money.cents;
    }

    //adds the initAmount by the cents of the given currency to account for money deposited
    public void deposit(currency money) throws Exception {
        initAmount += money.cents;
    }

    //return the currency with the initial cents of initAmount which will have the Accounts balance in cents
    currency getBalance() {
        return new currency(initAmount);
    }
}
