public class Savings extends Account {
    double rate;

    //    initAmount a protected Account variable
    public Savings() {
        super(new currency(0));
        rate = 0.035;
    }

    public Savings(currency initial, double rate) {
        super(initial);
        this.rate = rate;
    }

    @Override
    //deposits money into savings and accounts for interest
    public void deposit(currency amount) {
        initAmount *= (1 + rate);
        initAmount += amount.cents;
        balance();
    }

    @Override
    //withdraws money from the savings account and it accounts for interest
    public void withdraw(currency amount) {
        if (amount.cents > initAmount) {
            System.out.println("Insufficient funds in account");
        } else {
            initAmount *= (1 + rate);
            initAmount -= amount.cents;
        }
        balance();
    }

    @Override
    //returns the currency with the balance of the Savings account after an interest period
    currency getBalance() {
        return new currency((int) (initAmount * (1 + rate)));
    }
    //gives the balance not accounting for interest
    public void balance() {
        if (initAmount % 100 < 10) {
            System.out.println("Balance: $" + initAmount / 100 + ".0" + initAmount % 100);
            return;
        }
        System.out.println("Balance: $" + initAmount / 100 + "." + initAmount % 100);
    }

    @Override
    //gives the balance accounting for interest
    public String toString() {
        if ((int) (initAmount * (1 + rate) % 100) < 10)
            return "Balance: $" + (int) (initAmount * (1 + rate) / 100) + ".0" + (int) (initAmount * (1 + rate) % 100);
        return "Balance: $" + (int) (initAmount * (1 + rate) / 100) + "." + (int) (initAmount * (1 + rate) % 100);
    }
}