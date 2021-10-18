public class Checking extends Account {
    public Checking() {
        super(new currency(0));
    }

    public Checking(currency initialAmount) {
        super(initialAmount);
    }

    @Override
    public void deposit(currency amount) {
        initAmount += amount.cents;
        String s = toString();
        System.out.println(s);
    }

    @Override
    public void withdraw(currency amount) {
        if (amount.cents > initAmount) {
            System.out.println("Insufficient funds in account");
            String s = toString();
            System.out.println(s);
            return;
        }
        initAmount -= amount.cents;
        String s = toString();
        System.out.println(s);
    }

    @Override
    public String toString() {
        if ((int) (initAmount % 100) == 0)
            return "Balance: $" + getBalance().cents / 100 + "." + getBalance().cents % 100 + "" + 0;
        return "Balance: $" + getBalance().cents / 100 + "." + getBalance().cents % 100;
    }
}