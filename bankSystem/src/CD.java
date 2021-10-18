public class CD extends Account {
    double rate;
    int trackDeposit = 0;//keeps track of amount of deposits made
    int trackWithdrawn = 0;//keeps track of amount of withdraws made

    public CD() {
        super(new currency(0));
        rate = 0.04;
    }

    public CD(currency initialAmount, double rate) {
        super(initialAmount);
        this.rate = rate;
    }

    @Override
    //deposits money into CD only if no deposits where made prior during the program, and it accounts for interest
    public void deposit(currency amount) throws Exception {
        if (trackDeposit == 0) {
            initAmount += amount.cents;
            balance();
            trackDeposit++;
        } else throw new Exception("You can no longer deposit money into the CD account at this time");
    }

    @Override
    //deposits money into CD only if no deposits where made prior during the program, and accounts for interest
    public void withdraw(currency amount) throws Exception {
        if (trackWithdrawn == 0) {
            if (amount.cents > initAmount) {
                System.out.println("Insufficient funds in account");
            } else {
                initAmount *= (1 + rate);
                initAmount -= amount.cents;
                trackWithdrawn++;
            }
            balance();
        } else throw new Exception("You can no longer withdraw money into the CD account at this time");
    }

    //Gives the balance of the CD account without the interest
    public void balance() {
        if (initAmount % 100 < 10) {
            System.out.println("Balance: $" + initAmount / 100 + ".0" + initAmount % 100);
            return;
        }
        System.out.println("Balance: $" + initAmount / 100 + "." + initAmount % 100);
    }
    //Gives the balance accounting for interest
    public String toString() {
        if ((int) (initAmount * (1 + rate) % 100) < 10)
            return "Balance: $" + (int) (initAmount * (1 + rate) / 100) + ".0" + (int) (initAmount * (1 + rate) % 100);
        return "Balance: $" + (int) (initAmount * (1 + rate) / 100) + "." + (int) (initAmount * (1 + rate) % 100);
    }
}