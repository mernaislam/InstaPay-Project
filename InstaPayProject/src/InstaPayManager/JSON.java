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
        bankAcc.put("BankNumber", acc.getBankNumber());
        return bankAcc;
    }
    public JSONObject toJSONObj(AccountAPIProvider api){
        JSONObject jsonApi = new JSONObject();
        jsonApi.put("Name",api.getName().toString());
        jsonApi.put("APIUrl",api.getApiUrl());
        return jsonApi;
    }
    // to object functions - converts json obj
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
    // add account & retrieve account
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