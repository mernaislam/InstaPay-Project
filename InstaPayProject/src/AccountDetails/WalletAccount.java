package AccountDetails;
/**
 * The {@code WalletAccount} class represents a user account specifically associated with a wallet.
 * It extends the {@code Account} class and includes additional information such as the wallet ID.
 * Instances of this class should be created using a specific {@code AccountAPIProvider} for
 * handling wallet-related operations.
 *
 * <p>This class is part of the {@code AccountDetails} package.
 *
 * @see Account
 * @see AccountAPIProvider
 */
public class WalletAccount extends Account{
    /** The wallet ID associated with this wallet account. */
    private String walletID;
    /**
     * Gets the wallet ID associated with this wallet account.
     *
     * @return The wallet ID as a {@code String}.
     */
    public String getWalletID() {
        return walletID;
    }
    /**
     * Sets the wallet ID for this wallet account.
     *
     * @param walletID The wallet ID to be set.
     */
    public void setWalletID(String walletID) {
        this.walletID = walletID;
    }
    /**
     * Constructs a new {@code WalletAccount} with the specified account details, API provider,
     * and wallet ID.
     *
     * @param accountID The account ID.
     * @param username The username associated with the account.
     * @param name The name associated with the account.
     * @param password The password associated with the account.
     * @param mobileNumber The mobile number associated with the account.
     * @param api The API provider for wallet-related operations.
     * @param walletID The wallet ID associated with this account.
     */
    public WalletAccount(String accountID, String username, String name, String password, String mobileNumber, AccountAPIProvider api, String walletID) {
        super(accountID, username, name, password, mobileNumber);
        this.walletID = walletID;
        setApiStrategy(api);
    }
    public void setApiStrategy(AccountAPIProvider api){
        this.api = api;
    }
}
