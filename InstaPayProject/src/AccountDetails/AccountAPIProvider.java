package InstaPayProject.src.AccountDetails;
import java.util.ArrayList;
public abstract class AccountAPIProvider {
    InstaPayAPI name;
    String mobileNo;

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public InstaPayAPI getName() {
        return name;
    }

    public void setName(InstaPayAPI name) {
        this.name = name;
    }

    public AccountAPIProvider(InstaPayAPI name, String mobileNo) {
        this.name = name;
        this.mobileNo = mobileNo;
    }

    public abstract String makeVerifactionRequest(String mobileNo);
    public boolean verifyAccount(){
        String response = makeVerifactionRequest(mobileNo);
        boolean isVerified = response.contains("success");
        if(isVerified){
            System.out.println("Account verification successful");
        }
        return isVerified;
    }
}
