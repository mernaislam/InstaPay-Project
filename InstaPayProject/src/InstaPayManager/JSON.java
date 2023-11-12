package InstaPayManager;

import AccountDetails.Account;
import org.json.simple.JSONObject;


import java.util.Vector;

public class JSON implements DataManager {
    private JSONObject data;

    JSON(){
        loadData();
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
