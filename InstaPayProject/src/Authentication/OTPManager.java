package Authentication;

import java.util.Random;

/**
 * The {@code OTPManager} class generates one-time passwords (OTPs) for mobile number verification.
 *
 * <p>This class is part of the {@code Authentication} package.
 *
 */
public class OTPManager {
    /**
     * Generates a random one-time password (OTP) for a given mobile number.
     *
     * @param mobNum The mobile number for which the OTP is generated.
     * @return The generated OTP as an integer.
     */
    public int getOTP(String mobNum){
        Random rnd = new Random();
        return 100000 + rnd.nextInt(900000);
    }
}
