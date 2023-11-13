package InstaPayManager;

import AccountDetails.Account;
import AccountDetails.AccountAPIProvider;
import AccountDetails.BankAccount;
import AccountDetails.WalletAccount;
import Transaction.Bill;
import Transaction.PaymentAPIProvider;
import org.json.simple.JSONObject;


import java.util.List;
import java.util.Vector;

public class JSON implements DataManager {
    private JSONObject data;

    public JSON(){
        loadData();
    }
    public JSONObject toJSONObj(WalletAccount acc){
        JSONObject walletAcc = new JSONObject();
        walletAcc.put("Username", acc.getUsername());
        walletAcc.put("Name", acc.getName());
        walletAcc.put("MobileNumber", acc.getMobileNumber());
        walletAcc.put("API", toJSONObj(acc.getApi()));
        walletAcc.put("Bills", toJSONObj(acc.getBills()));
        walletAcc.put("WalletID", acc.getWalletID());
        return walletAcc;
    }
    public JSONObject toJSONObj(BankAccount acc){
        JSONObject bankAcc = new JSONObject();
        bankAcc.put("Username", acc.getUsername());
        bankAcc.put("Name", acc.getName());
        bankAcc.put("MobileNumber", acc.getMobileNumber());
        bankAcc.put("API", toJSONObj(acc.getApi()));
        bankAcc.put("Bills", toJSONObj(acc.getBills()));
        bankAcc.put("BankNumber", acc.getBankNumber());
        return bankAcc;
    }
    public JSONObject toJSONObj(AccountAPIProvider api){
        JSONObject jsonApi = new JSONObject();
//        jsonApi.put("Name",api.getName().toString);
        jsonApi.put("APIUrl",api.getApiUrl());
        return jsonApi;
    }
    public JSONObject toJSONObj(PaymentAPIProvider api){
        JSONObject jsonApi = new JSONObject();
        //        jsonApi.put("Name",api.getName().toString);
        jsonApi.put("APIUrl",api.getApiUrl());
        return jsonApi;
    }
    public JSONObject toJSONObj(Bill bill){
        JSONObject jsonBill = new JSONObject();
        jsonBill.put("TransactionDate", bill.getTransactionDate().toString());
        jsonBill.put("TransactionID", bill.getTransactionID());
        jsonBill.put("Total", bill.getTotal());
        jsonBill.put("MobileNumber", bill.getMobileNumber());
        jsonBill.put("APIProvider", toJSONObj(bill.getApiProvider()));
        return jsonBill;
    }
    public JSONObject toJSONObj(List<Bill> bills){
        int id = 1;
        JSONObject jsonBills = new JSONObject();
        for(Bill b: bills){
            jsonBills.put(id++, toJSONObj(b));
        }
        return jsonBills;
    }
    public void loadData(){

    }
    public void saveData(){

    }
    public Vector<Account> getAccounts(){
        Vector<Account> accounts = new Vector<>();
        return accounts;
    }
    public void addAccount(Account acc){

    }
    public boolean checkAuth(String uName, String password){
        return false;
    }
    public boolean userExist(String uName){
        return false;
    }

}
