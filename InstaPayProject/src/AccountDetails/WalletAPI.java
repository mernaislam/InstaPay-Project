package InstaPayProject.src.AccountDetails;

import java.util.ArrayList;
public class WalletAPI extends AccountAPIProvider{
    private ArrayList<WalletAccount>allWalletAccounts;
    public WalletAPI(InstaPayAPI name, String mobileNo) {
        super(name, mobileNo);
        allWalletAccounts = new ArrayList<>();
    }
    public String makeVerifactionRequest(String mobileNo){
        for(WalletAccount a : allWalletAccounts){
            if(a.getMobileNumber().equals(mobileNo)){
                return "Wallet API verification response: success";
            }
        }
        return "Wallet API verification response: failed";
    }

}
