package AccountDetails;

public interface AccountAPIProvider {

    public boolean verifyAccount(String mobileNumber, String accType);
    public double inquireBalance();
    public boolean withdraw(double amount, Account acc);
    public void deposit(double amount, Account acc);
    public String getApi();
}
