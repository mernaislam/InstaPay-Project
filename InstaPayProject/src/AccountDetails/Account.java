package AccountDetails;

import Transaction.Bill;

import java.util.List;

public class Account {
    private String username;
    private String name;
    private String password;
    private String mobileNumber;
    private AccountAPIProvider api;
    private List<Bill> bills;

    public String getUsername() {
        return username;
    }
    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }
    public AccountAPIProvider getApi() {
        return api;
    }
    public List<Bill> getBills() {
        return bills;
    }
}
