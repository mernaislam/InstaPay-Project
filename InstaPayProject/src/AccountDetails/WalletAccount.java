package AccountDetails;

import Transaction.Bill;

import java.util.List;

public class WalletAccount extends Account{
    private String walletID;

    public String getWalletID() {
        return walletID;
    }

    public void setWalletID(String walletID) {
        this.walletID = walletID;
    }

    public WalletAccount(String accountID, String username, String name, String password, String mobileNumber, AccountAPIProvider api, List<Bill> bills, String walletID) {
        super(accountID, username, name, password, mobileNumber, api, bills);
        this.walletID = walletID;
    }
}
