package AccountDetails;
/**
 * The {@code Account} class represents a generic user account with basic information such as
 * username, name, password, mobile number, and account ID. This class is intended to be
 * extended by specific account types, and it includes methods for accessing and modifying
 * account details.
 *
 * <p>The class also includes an abstract method, {@code setApiStrategy}, which should be
 * implemented by subclasses to set the API strategy for the specific account type.
 *
 * <p>This class is part of the {@code AccountDetails} package.
 *
 * @see AccountAPIProvider
 */
public abstract class Account {
    private String username;
    private String accountID;

    private String name;
    private String password;
    private String mobileNumber;
    protected AccountAPIProvider api;

    public abstract void setApiStrategy(AccountAPIProvider api);
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getName() {
        return name;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    public AccountAPIProvider getApi() {
        return api;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public Account(String accountID, String username, String name, String password, String mobileNumber) {
        this.accountID = accountID;
        this.username = username;
        this.name = name;
        this.password = password;
        this.mobileNumber = mobileNumber;
    }
}
