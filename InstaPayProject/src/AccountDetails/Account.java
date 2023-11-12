package AccountDetails;

import Transaction.Bill;

import java.util.List;

public class Account {
    private String username;
    private String name;
    private String password;
    private String mobileNumber;

    public Account(){}
    public Account(String username, String name, String password, String mobileNumber) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.mobileNumber = mobileNumber;
    }
    private AccountAPIProvider api;
    private List<Bill> bills;

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
    public List<Bill> getBills() {
        return bills;
    }
}
