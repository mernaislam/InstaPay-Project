package Authentication;

import java.util.Random;

public class OTPManager {
    public int getOTP(String mobNum){
        Random rnd = new Random();
        return 100000 + rnd.nextInt(900000);
    }
}
