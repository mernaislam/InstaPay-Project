package Authentication;

import AccountDetails.*;
import InstaPayManager.DataManager;
import InstaPayManager.JSON;

import java.util.*;

public class Authentication {
     public static Account loggedInUser;
     OTPManager otpManager;
     DataManager dataManager;

    public Authentication() {
        this.otpManager = new OTPManager();
        this.dataManager = new JSON();
    }
    void setLoggedInUser(Account acc) {
        loggedInUser = acc;
    }

    public void register(){
        Account user;
        Map<Integer, InstaPayAPI> bankAccountsMap = new HashMap<>(){
            {
                put(1, InstaPayAPI.CIBAccount);
                put(2, InstaPayAPI.QNBAccount);
                put(3, InstaPayAPI.BankMisrAccount);
            }
        };
        Map<Integer, InstaPayAPI> walletsMap = new HashMap<>(){
            {
                put(1,InstaPayAPI.VodafoneCash);
                put(2,InstaPayAPI.Fawry);
            }
        };
        Scanner sc = new Scanner(System.in);
        String name, mobileNumber, username, password;
        int accountChoice, bankChoice, walletChoice;
        String id = Integer.toString(dataManager.getAccounts().size() + 1);
        InstaPayAPI api;
        AccountAPIProvider accApi;

        System.out.println("Choose your Account type for the given number: \n1. Bank Account \n2. Mobile Wallet");
        System.out.print(">>");
        accountChoice = sc.nextInt();
        while (accountChoice != 1 && accountChoice != 2) {
            System.out.print("Invalid number, please try again: ");
            accountChoice = sc.nextInt();
        }
        if (accountChoice == 1) {
            System.out.println("Choose your Bank:");
            for (int i = 1; i <= bankAccountsMap.size(); i++) {
                System.out.println(i + ". " + bankAccountsMap.get(i).toString());
            }
            System.out.print(">>");
            bankChoice = sc.nextInt();
            while (!bankAccountsMap.containsKey(bankChoice)) {
                System.out.println("Invalid number, please try again: ");
                bankChoice = sc.nextInt();
            }
            api = bankAccountsMap.get(bankChoice);
            System.out.println("Enter your mobile number [Must be registered at this bank account]");
            System.out.print(">>");
            mobileNumber = sc.next();
            String mobRegex = "^01[0-2,5]{1}[0-9]{8}$";
            while (!mobileNumber.matches(mobRegex)) {
                System.out.print("Invalid phone number, try again: ");
                mobileNumber = sc.next();
            }
            //  ------------------------ verify it is registered in the bank
            accApi = new BankAPI(api, "www.bank.com");


            int otp = otpManager.getOTP(mobileNumber);
            System.out.println("Your otp is: " + otp);

        } else {
            System.out.println("Please choose your Wallet:");
            for (int i = 1; i <= walletsMap.size(); i++) {
                System.out.println(i + ". " + walletsMap.get(i));
            }
            System.out.print(">>");
            walletChoice = sc.nextInt();
            while (!walletsMap.containsKey(walletChoice)) {
                System.out.println("Invalid number, please try again: ");
                walletChoice = sc.nextInt();
            }
            api = walletsMap.get(walletChoice);
            System.out.println("Please enter your mobile number [Must be registered at this wallet account]");
            System.out.print(">>");
            mobileNumber = sc.next();
            String mobRegex = "^01[0-2,5]{1}[0-9]{8}$";
            while (!mobileNumber.matches(mobRegex)) {
                System.out.print("Invalid phone number, try again: ");
                mobileNumber = sc.next();
            }
            // ------------------------ verify it is registered in the wallet
            accApi = new WalletAPI(api, "www.wallet.com");

            int otp = otpManager.getOTP(mobileNumber);
            System.out.println("Your otp is: " + otp);
        }

        System.out.print("Welcome, please enter your name: ");
        name = sc.next();
        System.out.print("Create your username: ");
        username = sc.next();
        String usernameRegex = "^(?=[a-zA-Z0-9._]{8,20}$)(?!.*[_.]{2})[^_.].*[^_.]$";

        // dataManager.retrieveAccount(username) != null || username.matches(usernameRegex)
        while (dataManager.retrieveAccount(username) != null) {
            if(dataManager.retrieveAccount(username) != null)
                System.out.print("This username already exists, try a different one: ");
//          if(username.matches(usernameRegex))
//            System.out.print("Invalid username, try a different one: ");
            username = sc.next();
        }
        System.out.print("Create your password: ");
        password = sc.next();
//        String passRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,10}$";
//        while (!password.matches(passRegex)) {
//            System.out.print("Must include a strong password, try again: ");
//            password = sc.next();
//        }
        if(accApi instanceof WalletAPI){
            user = new WalletAccount(
                    id,
                    username,
                    name,password,
                    mobileNumber,
                    accApi,
                    "105"); // edit wallet id

        }
        else{
            user = new BankAccount(
                    id,
                    username,
                    name,password,
                    mobileNumber,
                    accApi,
                    "105"); // edit bank number
        }
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
        Account acc;
        while (checkAccount(username, password) == null) {
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
        acc = checkAccount(username, password);
        setLoggedInUser(acc);
        return true;
    }

    public void logout(){
        loggedInUser = null;
        System.out.println("Logged out successfully!");
    }

    public Account checkAccount(String uName, String password){
        Vector<Account> accounts = dataManager.getAccounts();
        if(accounts != null) {
            for (Account acc : accounts) {
                if (acc.getUsername().equals(uName) && acc.getPassword().equals(password))
                    return acc;
            }
        }
        return null;
    }
//    public boolean usernameExists(String uName) {
//        Vector<Account> accounts = dataManager.getAccounts();
//        if(accounts != null) {
//            for (Account acc : accounts) {
//                if (acc.getUsername().equals(uName))
//                    return true;
//            }
//        }
//        return false;
//    }
}
