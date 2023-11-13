package InstaPayManager;

import AccountDetails.BankAccount;
import AccountDetails.WalletAccount;
import Authentication.Authentication;
import Transaction.TransactionManager;

import java.util.Scanner;

import static Authentication.Authentication.loggedInUser;

public class GUIManager {
    private DataManager DM;
    private Authentication auth;
    private TransactionManager TM;

    public GUIManager() {
        this.DM = new JSON();
        this.auth = new Authentication();
        this.TM = new TransactionManager(this.DM);
    }
    public void run(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to InstaPay: \n1. Sign in \n2. Sign up \n3. Exit");
        int choice;
        choice = sc.nextInt();
        switch (choice) {
            case 1 -> { // login
                if(!auth.login()){
                    run();
                }
                // ------------------------- display options based on account type
            }
            case 2 -> { // sign up
                auth.register();
                // ------------------------- display options based on account type
            }
            case 3 -> System.out.println("Thank you for using the app!");
            default -> {
                System.out.println("Incorrect number, please try again [1-3]");
                run();
            }
        }
    }
    public void displayOptions(){
        if(loggedInUser instanceof BankAccount){
            Scanner scan = new Scanner(System.in);
            String options = """ 
                        Please choose one of the following options:
                        1. Transfer to Wallet
                        2. Transfer to another InstaPay Account
                        3. Transfer to Bank Account
                        4. Pay Bills
                        5. Inquire Balance
                        6. Logout
                        7. Return back
                        """;
            System.out.println(options);
            String choice = scan.nextLine();
            switch (choice){
                case "1":
                    TM.transferToWallet();
                    break;
                case "2":
                    TM.transferToAccount();
                    break;
                case "3":
                    TM.transferToBank();
                    break;
                case "4":
//                    TM.chooseBill();
                    break;
                case "5":
//                    loggedInUser.getApi().inquireBalance();
                    break;
                case "6":
                    auth.logout();
                    break;
                case "7":
                    return;
                default:
                    System.out.println("Incorrect number, please try again [1-7]");
                    break;
            }
        }
        else if(loggedInUser instanceof WalletAccount){
            Scanner scan = new Scanner(System.in);
            String options = """ 
                        Please choose one of the following options:
                        1. Transfer to Wallet
                        2. Transfer to another InstaPay Account
                        3. Pay Bills
                        4. Inquire Balance
                        5. Logout
                        6. Return back
                        """;
            System.out.println(options);
            String choice = scan.nextLine();
            switch (choice){
                case "1":
                    TM.transferToWallet();
                    break;
                case "2":
                    TM.transferToAccount();
                    break;
                case "3":
//                    TM.chooseBill();
                    break;
                case "4":
//                    loggedInUser.getApi().inquireBalance();
                    break;
                case "5":
                    auth.logout();
                    break;
                case "6":
                    return;
                default:
                    System.out.println("Incorrect number, please try again [1-6]");
                    break;
            }
        }
    }
}
