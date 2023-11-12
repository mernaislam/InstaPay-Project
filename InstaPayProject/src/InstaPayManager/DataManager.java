package InstaPayManager;

import AccountDetails.Account;

import java.util.Vector;

public interface DataManager {

    public void saveData();
    public void loadData();
    public Vector<Account> getAccounts();
    public void addAccount(Account acc);
}
