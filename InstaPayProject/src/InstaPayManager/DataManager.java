package InstaPayManager;

import AccountDetails.Account;

import java.util.Vector;
/**
 * The {@code DataManager} interface defines methods for loading, saving, retrieving, and
 * manipulating account data.
 *
 * <p>This interface is part of the {@code InstaPayManager} package.
 *
 */
public interface DataManager {
    /**
     * Loads data from the storage.
     */
    public void loadData();

    /**
     * Saves data to the storage.
     */
    public void saveData();

    /**
     * Retrieves a vector of accounts from the data manager.
     *
     * @return A vector of {@code Account} objects.
     */
    public Vector<Account> getAccounts();

    /**
     * Adds an account to the data manager.
     *
     * @param acc The account to be added.
     */
    public void addAccount(Account acc);

    /**
     * Retrieves an account based on the username.
     *
     * @param username The username of the account to retrieve.
     * @return The {@code Account} object if found, or {@code null} if not found.
     */
    public Account retrieveAccount(String username);
}
