package InstaPayManager;

import AccountDetails.*;
import Transaction.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class JSON implements DataManager {
    private JSONObject data;

    public JSON(){
        loadData();
    }
    // to json object - functions
    public JSONObject toJSONObj(WalletAccount acc){
        JSONObject walletAcc = new JSONObject();
        walletAcc.put("AccountID", acc.getAccountID());
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
        bankAcc.put("AccountID", acc.getAccountID());
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
        jsonApi.put("Name",api.getName().toString());
        jsonApi.put("APIUrl",api.getApiUrl());
        return jsonApi;
    }
    public JSONObject toJSONObj(PaymentAPIProvider api){
        JSONObject jsonApi = new JSONObject();
        jsonApi.put("Name",api.getName().toString());
        jsonApi.put("APIUrl",api.getApiUrl());
        return jsonApi;
    }
    public JSONObject toJSONObj(Bill bill){
        JSONObject jsonBill = new JSONObject();
        jsonBill.put("TransactionDate", bill.getTransactionDate().toString());
        jsonBill.put("TransactionID", bill.getTransactionID());
        jsonBill.put("Total", bill.getTotal());
        jsonBill.put("MobileNumber", bill.getMobileNumber());
        jsonBill.put("API", toJSONObj(bill.getApiProvider()));
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
    // to-functions, convert each json obj to different objects
    public ElectricityBill toElectricityBill(JSONObject billJSON) {
        return new ElectricityBill(
                LocalDate.parse(billJSON.get("TransactionDate").toString()),
                billJSON.get("TransactionID").toString(),
                Double.parseDouble(billJSON.get("Amount").toString()),
                billJSON.get("MobileNumber").toString(),
                toBillAPI((JSONObject) billJSON.get("API"))
        );
    }
    public WaterBill toWaterBill(JSONObject billJSON) {
        return new WaterBill(
                LocalDate.parse(billJSON.get("TransactionDate").toString()),
                billJSON.get("TransactionID").toString(),
                Double.parseDouble(billJSON.get("Amount").toString()),
                billJSON.get("MobileNumber").toString(),
                toBillAPI((JSONObject) billJSON.get("API"))
        );
    }
    public GasBill toGasBill(JSONObject billJSON) {
        return new GasBill(
                LocalDate.parse(billJSON.get("TransactionDate").toString()),
                billJSON.get("TransactionID").toString(),
                Double.parseDouble(billJSON.get("Amount").toString()),
                billJSON.get("MobileNumber").toString(),
                toBillAPI((JSONObject) billJSON.get("API"))
        );
    }
    public List<Bill> toBills(JSONObject billsJSON) {
        List<Bill> bills = new ArrayList<>();
        for (Object key : billsJSON.keySet()) {
//            bills.add(toBill((JSONObject) key));
        }
        return bills;
    }
    public BankAPI toBankAPI(JSONObject apiJSON){
        return new BankAPI(
                InstaPayAPI.valueOf(apiJSON.get("Name").toString()),
                apiJSON.get("APIUrl").toString());
    }
    public WalletAPI toWalletAPI(JSONObject apiJSON){
        return new WalletAPI(
                InstaPayAPI.valueOf(apiJSON.get("Name").toString()),
                apiJSON.get("APIUrl").toString());
    }
    public BillAPI toBillAPI(JSONObject apiJSON){
        return new BillAPI(
                BillType.valueOf(apiJSON.get("Name").toString()),
                apiJSON.get("APIUrl").toString()
        );
    }
    // getters
    public Account getAccount(JSONObject obj){
        Account acc;
        if(obj.get("WalletID") != null){
            acc = new WalletAccount(
                    (String) obj.get("AccountID"),
                    (String) obj.get("Username"),
                    (String) obj.get("Name"),
                    (String) obj.get("Password"),
                    (String) obj.get("MobileNumber"),
                    toWalletAPI((JSONObject) obj.get("API")),
                    toBills((JSONObject) obj.get("Bills")),
                    (String) obj.get("WalletID"));
        }
        else {
            acc = new BankAccount(
                    (String) obj.get("AccountID"),
                    (String) obj.get("Username"),
                    (String) obj.get("Name"),
                    (String) obj.get("Password"),
                    (String) obj.get("MobileNumber"),
                    toBankAPI((JSONObject) obj.get("API")),
                    toBills((JSONObject) obj.get("Bills")),
                    (String) obj.get("BankNumber"));
        }
        return acc;
    }
    public Vector<Account> getAccounts() {
        Vector<Account> accounts = new Vector<>();
        JSONObject walletAccounts = (JSONObject) data.get("Accounts");
        if(walletAccounts != null){
            Account acc;
            for(Object user: walletAccounts.values()){
                acc = getAccount((JSONObject) user);
                accounts.add(acc);
            }
        }
        return accounts;
    }

    // load and save data
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
    // add account
    public void addAccount(Account acc){
        if (data.get("Accounts") == null) {
            data.put("Accounts", new JSONObject());
        }
        if(acc instanceof WalletAccount){
            if(((JSONObject) (data.get("Accounts"))).get(acc.getAccountID()) == null){
                int size = accountsSize() + 1;
                data.put("AccountSize", Integer.toString(size));
            }
            ((JSONObject)(data.get("Accounts"))).put(acc.getAccountID(), toJSONObj((WalletAccount) acc));
        }
        else if(acc instanceof BankAccount){
            if(((JSONObject) (data.get("Accounts"))).get(acc.getAccountID()) == null){
                int size = accountsSize() + 1;
                data.put("AccountSize", Integer.toString(size));
            }
            ((JSONObject)(data.get("Accounts"))).put(acc.getAccountID(), toJSONObj((BankAccount) acc));
        }
        saveData();
    }
    public Account retrieveAccount(String username){
        Vector<Account> accounts = getAccounts();
        if(accounts != null) {
            for (Account acc : accounts) {
                if (acc.getUsername().equals(username))
                    return acc;
            }
        }
        return null;
    }
    // get size of accounts
    public int accountsSize() {
        if (data.get("AccountSize") == null) {
            return 0;
        }
        return Integer.parseInt(data.get("AccountSize").toString());
    }

}