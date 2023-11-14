package AccountDetails;

import InstaPayProject.src.Transaction.Bill;

import java.util.List;

public abstract class Account {
    private String username;
    private String accountID;
    private String name;
    private String password;
    private String mobileNumber;
    protected AccountAPIProvider api;

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

    public Account(String accountID, String username, String name, String password, String mobileNumber, AccountAPIProvider api) {
        this.accountID = accountID;
        this.username = username;
        this.name = name;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.api = api;
    }
}

