public class Current extends Account {

    public Current(String accountNumber,
                          String accountType,
                          double balance) {

        super(accountNumber, accountType, balance);
    }

    @Override
    public boolean withdraw(double amount) {

        return super.withdraw(amount);
    }
}