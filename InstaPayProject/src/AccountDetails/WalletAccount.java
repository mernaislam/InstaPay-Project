package AccountDetails;

public class WalletAccount extends Account{
    private String walletID;

    public WalletAccount(String walletID) {
        this.walletID = walletID;
    }

    public WalletAccount(String username, String name, String password, String mobileNumber, String walletID) {
        super(username, name, password, mobileNumber);
        this.walletID = walletID;
    }

    public String getWalletID() {
        return walletID;
    }
}
