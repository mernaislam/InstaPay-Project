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
        Map<Integer, BankType> bankAccountsMap = new HashMap<>(){
            {
                put(1, BankType.CIB);
                put(2, BankType.QNB);
                put(3, BankType.BankMisr);
            }
        };
        Map<Integer, WalletType> walletsMap = new HashMap<>(){
            {
                put(1,WalletType.VodafoneCash);
                put(2,WalletType.Fawry);
            }
        };
        Scanner sc = new Scanner(System.in);
        String name, mobileNumber, username, password;
        int accountChoice, bankChoice, walletChoice;
        String id = Integer.toString(dataManager.getAccounts().size() + 1);
        BankType bankApi = null;
        WalletType walletApi = null;
        AccountAPIProvider accApi;

        System.out.println("Choose your Account type: \n1. Bank Account \n2. Mobile Wallet");
        System.out.print(">> ");
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
            System.out.print(">> ");
            bankChoice = sc.nextInt();
            while (!bankAccountsMap.containsKey(bankChoice)) {
                System.out.println("Invalid number, please try again: ");
                bankChoice = sc.nextInt();
            }
            bankApi = bankAccountsMap.get(bankChoice);
            System.out.println("Enter your mobile number [Must be registered at this bank account]");
            System.out.print(">> ");
            mobileNumber = sc.next();
            String mobRegex = "^01[0-2,5]{1}[0-9]{8}$";
            accApi = new BankAPI(bankApi);
            while (!mobileNumber.matches(mobRegex) || mobileNumberExists(mobileNumber) || !accApi.verifyAccount(mobileNumber, bankApi.toString())) {
                if(!mobileNumber.matches(mobRegex)) System.out.print("Invalid phone number, try again: ");
                if(mobileNumberExists(mobileNumber)) System.out.print("This mobile number is already registered, try again: ");
                if(!accApi.verifyAccount(mobileNumber, bankApi.toString())) System.out.print("This mobile number is not registered in this Account Type, try again: ");
                mobileNumber = sc.next();
            }

            int otp = otpManager.getOTP(mobileNumber);
            System.out.println("Your otp is: " + otp);

        } else {
            System.out.println("Please choose your Wallet:");
            for (int i = 1; i <= walletsMap.size(); i++) {
                System.out.println(i + ". " + walletsMap.get(i));
            }
            System.out.print(">> ");
            walletChoice = sc.nextInt();
            while (!walletsMap.containsKey(walletChoice)) {
                System.out.println("Invalid number, please try again: ");
                walletChoice = sc.nextInt();
            }
            walletApi = walletsMap.get(walletChoice);
            System.out.println("Please enter your mobile number [Must be registered at this wallet account]");
            System.out.print(">> ");
            mobileNumber = sc.next();
            String mobRegex = "^01[0-2,5]{1}[0-9]{8}$";
            accApi = new WalletAPI(walletApi);
            while (!mobileNumber.matches(mobRegex) || mobileNumberExists(mobileNumber) || !accApi.verifyAccount(mobileNumber, walletApi.toString())) {
                if(!mobileNumber.matches(mobRegex)) System.out.print("Invalid phone number, try again: ");
                if(mobileNumberExists(mobileNumber)) System.out.print("This mobile number is already registered, try again: ");
                if(!accApi.verifyAccount(mobileNumber, walletApi.toString())) System.out.print("This mobile number is not registered in this Account Type, try again: ");
                mobileNumber = sc.next();
            }

            int otp = otpManager.getOTP(mobileNumber);
            System.out.println("Your otp is: " + otp);
        }

        System.out.print("Welcome, please enter your name: ");
        name = sc.next();
        System.out.print("Create your username: ");
        username = sc.next();
        String usernameRegex = "^[a-zA-Z0-9]*$";

        while (dataManager.retrieveAccount(username) != null || !username.matches(usernameRegex)) {
            if(dataManager.retrieveAccount(username) != null) {
                System.out.print("This username already exists, try a different one: ");
            }
            if(!username.matches(usernameRegex)) {
                System.out.println("Must include only alphanumeric characters!");
                System.out.print("Invalid username, try a different one: ");
            }
            username = sc.next();
        }
        System.out.print("Create your password: ");
        password = sc.next();
        String passRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        while (!password.matches(passRegex)) {
            System.out.print("Must include a strong password that contains at least:\n . 1 uppercase letter \n . 1 special character[@$!%*?&] \n . 1 number [0-9]\n try again: ");
            password = sc.next();
        }
        if(accApi instanceof WalletAPI){
            String walletID = walletApi.name() + mobileNumber;
            user = new WalletAccount(
                    id,
                    username,
                    name,password,
                    mobileNumber,
                    accApi,
                    walletID);
        }
        else {
            String bankNumber = bankApi.name() + mobileNumber;
            user = new BankAccount(
                    id,
                    username,
                    name,password,
                    mobileNumber,
                    accApi,
                    bankNumber);
        }
        dataManager.addAccount(user);
        System.out.println("Your account has been created successfully!");
    }
    public boolean login(){
        Scanner sc = new Scanner(System.in);
        String username, password;
        System.out.println("Please enter your credentials:");
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
            System.out.println("Incorrect username or password, please try again!");
            System.out.print("Username: ");
            username = sc.next();
            System.out.print("Password: ");
            password = sc.next();
            cnt++;
        }
        if (invalid) {
            System.out.println("You've reached maximum login attempts, returning to main menu...");
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

    public boolean mobileNumberExists(String mobileNumber){
        Vector<Account> accounts = dataManager.getAccounts();
        if(accounts != null) {
            for (Account acc : accounts) {
                if (acc.getMobileNumber().equals(mobileNumber))
                    return true;
            }
        }
        return false;
    }
}
