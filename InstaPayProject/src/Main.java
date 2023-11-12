import Authentication.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import InstaPayManager.GUIManager;

public class Main {
    public static void run(){
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

        Authentication auth = new Authentication();
        OTPManager otpManager = new OTPManager();
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to InstaPay: \n1. Sign in \n2. Sign up \n3. Exit");
        int choice;
        choice = sc.nextInt();
        switch (choice) {
            case 1 -> { // login
                String username, password;
                System.out.println("Please enter your credentials");
                System.out.print("Username: ");
                username = sc.next();
                System.out.print("Password: ");
                password = sc.next();
                int cnt = 1;
                boolean invalid = false;
                while (!auth.login(username, password)) {
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
                    run();
                }
                // display options based on account type
            }
            case 2 -> { // sign up
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
                    // verify it is registered in the bank

                    // send OTP
                    int otp = otpManager.getOTP(mobileNumber);

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
                }

                System.out.print("Welcome, please enter your name: ");
                name = sc.next();
                System.out.print("Create your username: ");
                username = sc.next();
                while (auth.usernameExists(username)) {
                    System.out.print("This username already exists, choose a different one: ");
                    username = sc.next();
                }
                System.out.print("Create your password: ");
                password = sc.next();
                String passRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,10}$";
                while (!password.matches(passRegex)) {
                    System.out.print("Must include a strong password, try again: ");
                    password = sc.next();
                }
            }
            case 3 -> System.out.println("Thank you for using the app!");
            default -> {
                System.out.println("Incorrect number, please try again [1-3]");
                run();
            }
        }
    }
    public static void main(String[] args) {
//        GUIManager M = new GUIManager();
//        M.run();
        run();
    }
}