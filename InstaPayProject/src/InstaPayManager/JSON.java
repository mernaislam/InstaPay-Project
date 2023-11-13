package InstaPayManager;

import AccountDetails.Account;
import AccountDetails.AccountAPIProvider;
import AccountDetails.BankAccount;
import AccountDetails.WalletAccount;
import Transaction.Bill;
import Transaction.PaymentAPIProvider;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
        walletAcc.put("Password", acc.getPassword());
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
        bankAcc.put("Password", acc.getPassword());
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
        jsonBill.put("Amount", bill.getAmount());
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
        JSONParser parser = new JSONParser();
        try {
            data = (JSONObject) parser.parse(new FileReader("Data.json"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    public void saveData(){
        try {
            FileWriter file = new FileWriter("Data.json");
            file.write(data.toJSONString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addAccount(Account acc){
        if(acc instanceof WalletAccount){
            if (data.get("WalletAccounts") == null) {
                data.put("WalletAccounts", new JSONObject());
            }
            if(walletAccountSize() == 0){
                int size = walletAccountSize() + 1;
                data.put("WalletAccSize", Integer.toString(size));
            }
            ((JSONObject)(data.get("WalletAccounts"))).put(walletAccountSize()+1, toJSONObj((WalletAccount) acc));
        }
        else if(acc instanceof BankAccount){
            if (data.get("BankAccounts") == null) {
                data.put("BankAccounts", new JSONObject());
            }
            if(bankAccountSize() == 0){
                int size = bankAccountSize() + 1;
                data.put("BankAccSize", Integer.toString(size));
            }
            ((JSONObject)(data.get("BankAccounts"))).put(bankAccountSize()+1, toJSONObj((BankAccount) acc));
        }
        saveData();
    }
    public Bill toBill(JSONObject billJSON) {
        Bill bill = new Bill();
        //////////////////////// --> string to date
//        bill.setTransactionDate(new SimpleDateFormat("dd/MM/yyyy").parse(billJSON.get("TransactionDate").toString()));
        bill.setTransactionID(billJSON.get("TransactionID").toString());
        bill.setAmount(Double.parseDouble(billJSON.get("Amount").toString()));
        bill.setMobileNumber(billJSON.get("MobileNumber").toString());
//        bill.setApiProvider(toPaymentAPI((JSONObject) billJSON.get("API")));
        return bill;
    }
    public List<Bill> toBills(JSONObject billsJSON) {
        List<Bill> bills = new ArrayList<>();
        for (Object key : billsJSON.keySet()) {
            bills.add(toBill((JSONObject) key));
        }
        return bills;
    }
    public AccountAPIProvider toAccountAPI(JSONObject apiJSON){
        AccountAPIProvider api = new AccountAPIProvider();
        api.setApiUrl(apiJSON.get("APIUrl").toString());
//        api.setAPIProvider(apiJSON.get("APIProvider").toString());
        return api;
    }
    public PaymentAPIProvider toPaymentAPI(JSONObject apiJSON){
        PaymentAPIProvider api = new PaymentAPIProvider();
        api.setApiUrl(apiJSON.get("APIUrl").toString());
//        api.setAPIProvider(api.valueOf(apiJSON.get("APIProvider").toString());
        return api;
    }
    public WalletAccount getWalletAccount(JSONObject obj){
        return new WalletAccount(
                (String) obj.get("Username"),
                (String) obj.get("Name"),
                (String) obj.get("Password"),
                (String) obj.get("MobileNumber"),
//                toAccountAPI((JSONObject) obj.get("API")),
//                toBills((JSONObject) obj.get("Bills")),
                (String) obj.get("WalletID"));
    }
    public BankAccount getBankAccount(JSONObject obj){
        return new BankAccount(
                (String) obj.get("Username"),
                (String) obj.get("Name"),
                (String) obj.get("Password"),
                (String) obj.get("MobileNumber"),
//                toAccountAPI((JSONObject) obj.get("API")),
//                toBills((JSONObject) obj.get("Bills")),
                (String) obj.get("BankNumber"));
    }
    public Vector<WalletAccount> getWalletAccounts(){
        Vector<WalletAccount> accounts = new Vector<>();
        JSONObject walletAccounts = (JSONObject) data.get("WalletAccounts");
        if(walletAccounts != null){
            WalletAccount acc;
            for(Object user: walletAccounts.values()){
                acc = getWalletAccount((JSONObject) user);
                accounts.add(acc);
            }
        }
        return accounts;
    }
    public Vector<BankAccount> getBankAccounts(){
        Vector<BankAccount> accounts = new Vector<>();
        JSONObject bankAccounts = (JSONObject) data.get("BankAccounts");
        if(bankAccounts != null){
            BankAccount acc;
            for(Object user: bankAccounts.values()){
                acc = getBankAccount((JSONObject) user);
                accounts.add(acc);
            }
        }
        return accounts;
    }

    public boolean checkAuth(String uName, String password){
        JSONObject bankAccounts = (JSONObject) data.get("BankAccounts");
        JSONObject walletAccounts = (JSONObject) data.get("WalletAccounts");
        if (bankAccounts != null) {
            for (Object account : bankAccounts.values()) {
                if (((JSONObject) account).get("Username").toString().equals(uName)
                        && ((JSONObject) account).get("Password").toString().equals(password)) {
                    return true;
                }
            }
        }
        if (walletAccounts != null) {
            for (Object account : walletAccounts.values()) {
                if (((JSONObject) account).get("Username").toString().equals(uName)
                        && ((JSONObject) account).get("Password").toString().equals(password)){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean userExist(String uName){
        JSONObject bankAccounts = (JSONObject) data.get("BankAccounts");
        JSONObject walletAccounts = (JSONObject) data.get("WalletAccounts");
        if (bankAccounts != null) {
            for (Object account : bankAccounts.values()) {
                if (((JSONObject) account).get("Username").toString().equals(uName)) {
                    return true;
                }
            }
        }
        if (walletAccounts != null) {
            for (Object account : walletAccounts.values()) {
                if (((JSONObject) account).get("Username").toString().equals(uName)) {
                    return true;
                }
            }
        }
        return false;
    }
    public int bankAccountSize() {
        if (data.get("BankAccSize") == null) {
            return 0;
        }
        return Integer.parseInt(data.get("BankAccSize").toString());
    }
    public int walletAccountSize() {
        if (data.get("WalletAccSize") == null) {
            return 0;
        }
        return Integer.parseInt(data.get("WalletAccSize").toString());
    }

}