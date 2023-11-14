package AccountDetails;

public interface AccountAPIProvider {

    public boolean verifyAccount(String mobileNumber, String accType);
    public double inquireBalance();
    public boolean withdraw(double amount);
    public void deposit(double amount);
    public String getApi();
}