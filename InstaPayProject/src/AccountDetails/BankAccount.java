package AccountDetails;

public class BankAccount extends Account{
    String bankNumber;

    public BankAccount(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    public BankAccount(String username, String name, String password, String mobileNumber, String bankNumber) {
        super(username, name, password, mobileNumber);
        this.bankNumber = bankNumber;
    }

    public String getBankNumber() {
        return bankNumber;
    }
}
