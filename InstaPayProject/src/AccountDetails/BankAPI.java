package AccountDetails;

import java.util.HashMap;
import java.util.Map;
import static Authentication.Authentication.loggedInUser;


public class BankAPI implements AccountAPIProvider{

    BankType bankType;
    Map<String, Double> bankBalance = new HashMap<>(){
        {
            put("01065470583", 500d);
            put("01065470585", 5000d);
            put("01002003004", 300d);
            put("01022199451", 30000d);
        }
    };
    Map<String, BankType> bankAccount = new HashMap<>(){
        {
            put("01065470583", BankType.BankMisr);
            put("01065470585", BankType.CIB);
            put("01002003004", BankType.QNB);
            put("01022199451", BankType.BankMisr);
        }
    };

    public BankAPI(BankType bankType){
        this.bankType = bankType;
    }
    @Override
    public boolean verifyAccount(String bankNum, String accType) {
        if (!bankAccount.containsKey(bankNum))
            return false;
        String bankType = bankAccount.get(bankNum).toString();
        if(bankAccount.containsKey(bankNum) && accType.equals(bankType)){
            return true;
        }
        return false;
    }

    @Override
    public double inquireBalance() {
        return bankBalance.get(loggedInUser.getMobileNumber());
    }

    @Override
    public boolean withdraw(double amount, Account acc) {
        double balance = bankBalance.get(acc.getMobileNumber());
        if(amount <= balance){
            bankBalance.put(acc.getMobileNumber(), balance-amount);
            return true;
        }
        return false;
    }

    public String getApi(){
        return bankType.name();
    }

    @Override
    public void deposit(double amount, Account acc) {
        if (acc == null)
            return;
        double balance = bankBalance.get(acc.getMobileNumber());
        bankBalance.put(acc.getMobileNumber(), balance+amount);
    }
}
