package InstaPayProject.src.AccountDetails;

import java.util.ArrayList;

public class BankAPI extends AccountAPIProvider{
    private ArrayList<BankAccount>allBankAccounts;
    public BankAPI(InstaPayAPI name, String mobileNo) {
        super(name, mobileNo);
        allBankAccounts = new ArrayList<>();
    }
    public String makeVerifactionRequest(String mobileNo){
        for(BankAccount a : allBankAccounts){
            if(a.getMobileNumber().equals(mobileNo) ){
                return "Bank API verification response: success";
            }
        }
        return "Bank API verification response: failed";
    }
}
