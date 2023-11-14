package AccountDetails;

public class WalletAccount extends Account{
    private String walletID;

    public String getWalletID() {
        return walletID;
    }

    public void setWalletID(String walletID) {
        this.walletID = walletID;
    }

    public WalletAccount(String accountID, String username, String name, String password, String mobileNumber, AccountAPIProvider api, String walletID) {
        super(accountID, username, name, password, mobileNumber);
        this.walletID = walletID;
        setApiStrategy(api);
    }
    public void setApiStrategy(AccountAPIProvider api){
        this.api = api;
    }
}
