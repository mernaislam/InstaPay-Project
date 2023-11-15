package AccountDetails;
/**
 * The {@code BankAccount} class represents a user account specifically associated with a bank.
 * It extends the {@code Account} class and includes additional information such as the bank
 * number. Instances of this class should be created using a specific {@code AccountAPIProvider}
 * for handling bank-related operations.
 *
 * <p>This class is part of the {@code AccountDetails} package.
 *
 * @see Account
 * @see AccountAPIProvider
 */
public class BankAccount extends Account{
    /** The bank number associated with this bank account. */
    String bankNumber;
    /**
     * Gets the bank number associated with this bank account.
     *
     * @return The bank number as a {@code String}.
     */
    public String getBankNumber() {
        return bankNumber;
    }
    /**
     * Sets the bank number for this bank account.
     *
     * @param bankNumber The bank number to be set.
     */
    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }
    /**
     * Constructs a new {@code BankAccount} with the specified account details, API provider,
     * and bank number.
     *
     * @param accountID The account ID.
     * @param username The username associated with the account.
     * @param name The name associated with the account.
     * @param password The password associated with the account.
     * @param mobileNumber The mobile number associated with the account.
     * @param api The API provider for bank-related operations.
     * @param bankNumber The bank number associated with this account.
     */
    public BankAccount(String accountID, String username, String name, String password, String mobileNumber, AccountAPIProvider api, String bankNumber) {
        super(accountID, username, name, password, mobileNumber);
        this.bankNumber = bankNumber;
        setApiStrategy(api);
    }
    public void setApiStrategy(AccountAPIProvider api){
        this.api = api;
    }
}
