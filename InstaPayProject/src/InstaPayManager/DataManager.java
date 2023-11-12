package InstaPayManager;

import AccountDetails.Account;

import java.util.Vector;

public interface DataManager {

    public void loadData();
    public void saveData();
    public Vector<Account> getAccounts();
    public void addAccount(Account acc);
}
