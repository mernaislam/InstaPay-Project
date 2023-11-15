package AccountDetails;

import java.util.HashMap;
import java.util.Map;

import static Authentication.Authentication.loggedInUser;

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
    public boolean verifyAccount(String accNum, String accType) {
        if (!walletAccount.containsKey(accNum))
            return false;
        String walletType = walletAccount.get(accNum).toString();
        if(walletAccount.containsKey(accNum) && walletType.equals(accType)){
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
    public boolean withdraw(double amount, Account acc) {
        double balance = walletBalance.get(acc.getMobileNumber());
        if(amount <= balance){
            walletBalance.put(acc.getMobileNumber(), balance-amount);
            return true;
        }
        return false;
    }

    @Override
    public void deposit(double amount, Account acc) {
        if (acc == null)
            return;
        double balance = walletBalance.get(acc.getMobileNumber());
        walletBalance.put(acc.getMobileNumber(), balance+amount);
    }
}
