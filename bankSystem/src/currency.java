public class currency {
    int cents;

    public currency() {//default constructor
        this.cents = 0;
    }

    public currency(int cents) {//constructor setting cents to given int parameter
        this.cents = cents;
    }

    public int getValue() {//getter for cents
        return cents;
    }

    public currency add(currency rhs) {
        //returns a currency with parameter that is the sum of the present and given currency
        return new currency(this.cents + rhs.cents);
    }

    public currency subtract(currency rhs) {
        //returns a currency with parameter that is the difference of the present and given currency
        return new currency(this.cents - rhs.cents);
    }

    @Override
    public String toString() {//converts the currency object into a currency line
        return (double) this.cents / 100 + " dollars";
    }
}
