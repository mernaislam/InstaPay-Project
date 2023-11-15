package AccountDetails;

import java.util.HashMap;
import java.util.Map;

import static Authentication.Authentication.loggedInUser;
/**
 * The {@code WalletAPI} class implements the {@code AccountAPIProvider} interface for handling
 * wallet-related account operations such as verification, balance inquiry, withdrawal, and deposit.
 * It associates with a specific wallet type and maintains balances for different accounts.
 *
 */
public class WalletAPI implements AccountAPIProvider{
    WalletType walletType;
    Map<String, Double> walletBalance = new HashMap<>(){
        {
            put("01065470583", 500d);
            put("01065470585", 5000d);
            put("01022199451", 30000d);
        }
    };
    Map<String, WalletType> walletAccount = new HashMap<>(){
        {
            put("01065470583", WalletType.Fawry);
            put("01065470585", WalletType.VodafoneCash);
            put("01022199451", WalletType.Fawry);
        }
    };

    public WalletAPI(WalletType walletType){
        this.walletType = walletType;
    }
    @Override
    public boolean verifyAccount(String mobileNumber, String accType) {
        String walletType = walletAccount.get(mobileNumber).toString();
        if(walletAccount.containsKey(mobileNumber) && walletType.equals(accType)){
            return true;
        }
        return false;
    }

    public String getApi(){
        return walletType.name();
    }

    @Override
    public double inquireBalance() {
        return walletBalance.get(loggedInUser.getMobileNumber());
    }

    @Override
    public boolean withdraw(double amount) {
        double balance = walletBalance.get(loggedInUser.getMobileNumber());
        if(amount <= balance){
            walletBalance.put(loggedInUser.getMobileNumber(), balance-amount);
            return true;
        }
        return false;
    }

    @Override
    public void deposit(double amount) {
        double balance = walletBalance.get(loggedInUser.getMobileNumber());
        walletBalance.put(loggedInUser.getMobileNumber(), balance+amount);
    }
}
