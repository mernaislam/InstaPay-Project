package Authentication;

import AccountDetails.Account;
import InstaPayManager.DataManager;
import InstaPayManager.GUIManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Authentication {
     public static Account user;
     OTPManager otpManager;
     DataManager dataManager;

    public void register(){
        Map<Integer, String> bankAccountsMap = new HashMap<>(){
            {
                put(1, "CIB");
                put(2, "QNB");
                put(3, "Bank Misr");
            }
        };
        Map<Integer, String> walletsMap = new HashMap<>(){
            {
                put(1, "Vodafone Cash");
                put(2, "Fawry");
            }
        };
        Scanner sc = new Scanner(System.in);
        String name, mobileNumber, username, password;
        int accountChoice, bankChoice, walletChoice;

        System.out.println("Choose your Account type for the given number: \n1. Bank Account \n2. Mobile Wallet");
        accountChoice = sc.nextInt();
        while (accountChoice != 1 && accountChoice != 2) {
            System.out.print("Invalid number, please try again: ");
            accountChoice = sc.nextInt();
        }
        if (accountChoice == 1) {
            System.out.println("Choose your Bank:");
            for (int i = 1; i <= bankAccountsMap.size(); i++) {
                System.out.println(i + ". " + bankAccountsMap.get(i));
            }
            bankChoice = sc.nextInt();
            while (!bankAccountsMap.containsKey(bankChoice)) {
                System.out.println("Invalid number, please try again: ");
                bankChoice = sc.nextInt();
            }
            System.out.println("Enter your mobile number [Must be registered at this bank account]");
            mobileNumber = sc.next();
            String mobRegex = "^01[0-2,5]{1}[0-9]{8}$";
            while (!mobileNumber.matches(mobRegex)) {
                System.out.print("Invalid phone number, try again: ");
                mobileNumber = sc.next();
            }
            //  ------------------------ verify it is registered in the bank

            int otp = otpManager.getOTP(mobileNumber);
            System.out.println("Your otp is: " + otp);

        } else {
            System.out.println("Please choose your Wallet:");
            for (int i = 1; i <= walletsMap.size(); i++) {
                System.out.println(i + ". " + walletsMap.get(i));
            }
            walletChoice = sc.nextInt();
            while (!walletsMap.containsKey(walletChoice)) {
                System.out.println("Invalid number, please try again: ");
                walletChoice = sc.nextInt();
            }
            System.out.println("Please enter your mobile number [Must be registered at this wallet account]");
            mobileNumber = sc.next();
            String mobRegex = "^01[0-2,5]{1}[0-9]{8}$";
            while (!mobileNumber.matches(mobRegex)) {
                System.out.print("Invalid phone number, try again: ");
                mobileNumber = sc.next();
            }
            // ------------------------ verify it is registered in the wallet

            int otp = otpManager.getOTP(mobileNumber);
            System.out.println("Your otp is: " + otp);
        }

        System.out.print("Welcome, please enter your name: ");
        name = sc.next();
        System.out.print("Create your username: ");
        username = sc.next();
        String usernameRegex = "^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$";
        while (usernameExists(username) || username.matches(usernameRegex)) {
            if(usernameExists(username)) System.out.print("This username already exists, try a different one: ");
            if(username.matches(usernameRegex)) System.out.print("Invalid username, try a different one: ");
            username = sc.next();
        }
        System.out.print("Create your password: ");
        password = sc.next();
        String passRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,10}$";
        while (!password.matches(passRegex)) {
            System.out.print("Must include a strong password, try again: ");
            password = sc.next();
        }
        user.setName(name);
        user.setMobileNumber(mobileNumber);
        user.setUsername(username);
        user.setPassword(password);
        dataManager.addAccount(user);
        System.out.println("Your account has been created successfully!");
    }
    public boolean login(){
        Scanner sc = new Scanner(System.in);
        String username, password;
        System.out.println("Please enter your credentials");
        System.out.print("Username: ");
        username = sc.next();
        System.out.print("Password: ");
        password = sc.next();
        int cnt = 1;
        boolean invalid = false;
        while (!checkAccount(username, password)) {
            if (cnt == 3) {
                invalid = true;
                break;
            }
            System.out.println("Incorrect username or password, please try again");
            System.out.print("Username: ");
            username = sc.next();
            System.out.print("Password: ");
            password = sc.next();
            cnt++;
        }
        if (invalid) {
            System.out.println("Returning to main menu ...");
            return false;
        }
        return true;
    }

    public boolean checkAccount(String username, String password){
        //  ------------------------ checks in dataManager
        return true;
    }

    public void logout(){}

    public boolean usernameExists(String username){
        //  ------------------------ checks in dataManager
        return false;
    }

}
