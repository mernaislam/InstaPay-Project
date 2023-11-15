package InstaPayManager;

import AccountDetails.BankAccount;
import AccountDetails.WalletAccount;
import Authentication.Authentication;
import TransactionDetails.TransactionManager;

import java.util.Scanner;

import static Authentication.Authentication.loggedInUser;

/**
 * The {@code UIManager} class manages the user interface for the InstaPay application.
 *
 * <p>This class is part of the {@code InstaPayManager} package.
 *
 * @see DataManager
 * @see Authentication
 * @see TransactionManager
 */
public class UIManager {

    private DataManager DM;
    private Authentication auth;
    private TransactionManager TM;

    public UIManager() {
        this.auth = new Authentication();
        setDMStrategy();
        this.TM = new TransactionManager(this.DM);
    }
    public void setDMStrategy() {
        this.DM = new JSON();
    }
    public void run() {
        System.out.println("Welcome to InstaPay :)");
        System.out.println("-------------------------------------------");
        Scanner scan = new Scanner(System.in);
        while (true) {
            String options = """ 
                    \nPlease choose one of the following options:
                    1. Register
                    2. Login
                    3. Exit
                    """;
            System.out.print(options);
            System.out.print(">> ");
            String choice = scan.nextLine();
            switch (choice) {
                case "1":
                    auth.register();
                    break;
                case "2":
                    if(auth.login())
                        displayOptions();
                    break;
                case "3":
                    System.out.println("Thank you for using the app!");
                    System.exit(0);
                default:
                    System.out.println("Incorrect number, please try again & choose a number between [1-3]");
            }
        }
    }
    public void displayOptions() {
        while (true) {
            if (loggedInUser instanceof BankAccount) {
                Scanner scan = new Scanner(System.in);
                String options = """ 
                        \nPlease choose one of the following options:
                        1. Transfer to Wallet
                        2. Transfer to another InstaPay Account
                        3. Transfer to Bank Account
                        4. Pay Bills
                        5. Inquire Balance
                        6. Logout
                        7. Exit
                        """;
                System.out.print(options);
                System.out.print(">> ");
                String choice = scan.nextLine();
                switch (choice) {
                    case "1" -> TM.transferToWallet();
                    case "2" -> TM.transferToAccount();
                    case "3" -> TM.transferToBank();
                    case "4" -> TM.chooseBill();
                    case "5" -> System.out.println(loggedInUser.getApi().inquireBalance());
                    case "6" -> {
                        auth.logout();
                        return;
                    }
                    case "7" -> {
                        System.out.println("Thank you for using the app!");
                        System.exit(0);
                    }
                    default -> System.out.println("Incorrect number, please try again & choose a number between [1-7]");
                }
            } else if (loggedInUser instanceof WalletAccount) {
                Scanner scan = new Scanner(System.in);
                String options = """ 
                        \nPlease choose one of the following options:
                        1. Transfer to Wallet
                        2. Transfer to another InstaPay Account
                        3. Pay Bills
                        4. Inquire Balance
                        5. Logout
                        6. Exit
                        """;
                System.out.print(options);
                System.out.print(">> ");
                String choice = scan.nextLine();
                switch (choice) {
                    case "1" -> TM.transferToWallet();
                    case "2" -> TM.transferToAccount();
                    case "3" -> TM.chooseBill();
                    case "4" -> System.out.println(loggedInUser.getApi().inquireBalance());
                    case "5" -> {
                        auth.logout();
                        return;
                    }
                    case "6" -> {
                        System.out.println("Thank you for using the app!");
                        System.exit(0);
                    }
                    default -> System.out.println("Incorrect number, please try again & choose a number between [1-7]");
                }
            }
        }
    }
}
