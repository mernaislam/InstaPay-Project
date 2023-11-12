package InstaPayManager;

import AccountDetails.Account;
import AccountDetails.BankAccount;
import AccountDetails.WalletAccount;

import java.util.Vector;

public interface DataManager {

    public void loadData();
    public void saveData();
    public Vector<WalletAccount> getWalletAccounts();
    public Vector<BankAccount> getBankAccounts();
    public void addAccount(Account acc);
}
