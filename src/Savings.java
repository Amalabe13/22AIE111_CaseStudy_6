public class Savings extends Account {

    public Savings(String accountNumber,
                          String accountType,
                          double balance) {

        super(accountNumber, accountType, balance);
    }

    @Override
    public boolean withdraw(double amount) {

        if(getBalance() - amount < 1000) {
            return false;
        }

        return super.withdraw(amount);
    }
}