package AccountDetails;
/**
 * The {@code AccountAPIProvider} interface defines a set of methods that must be implemented
 * by classes providing API functionality for user accounts. Classes implementing this interface
 * are expected to handle account verification, balance inquiries, withdrawals, deposits, and
 * retrieval of the API information.
 *
 */
public interface AccountAPIProvider {
    /**
     * Verifies an account based on the provided mobile number and account type.
     *
     * @param mobileNumber The mobile number associated with the account.
     * @param accType The account type to be verified.
     * @return {@code true} if the account is verified, {@code false} otherwise.
     */
    public boolean verifyAccount(String mobileNumber, String accType);
    /**
     * Retrieves the account balance.
     *
     * @return The current account balance as a {@code double}.
     */
    public double inquireBalance();
    /**
     * Withdraws the specified amount from the account.
     *
     * @param amount The amount to be withdrawn.
     * @return {@code true} if the withdrawal is successful, {@code false} otherwise.
     */
    public boolean withdraw(double amount);
    /**
     * Deposits the specified amount into the account.
     *
     * @param amount The amount to be deposited.
     */
    public void deposit(double amount);
    /**
     * Gets the API information.
     *
     * @return A {@code String} representing the API information.
     */
    public String getApi();
}
