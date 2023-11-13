package AccountDetails;

import Transaction.Bill;

import java.util.List;

public class BankAccount extends Account{
    String bankNumber;

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    public BankAccount(String accountID, String username, String name, String password, String mobileNumber, AccountAPIProvider api, List<Bill> bills, String bankNumber) {
        super(accountID, username, name, password, mobileNumber, api, bills);
        this.bankNumber = bankNumber;
    }
}
