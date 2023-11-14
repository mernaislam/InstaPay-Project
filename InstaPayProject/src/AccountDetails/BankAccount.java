package AccountDetails;

public class BankAccount extends Account{
    String bankNumber;

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    public BankAccount(String accountID, String username, String name, String password, String mobileNumber, AccountAPIProvider api, String bankNumber) {
        super(accountID, username, name, password, mobileNumber, api);
        this.bankNumber = bankNumber;
    }
}
