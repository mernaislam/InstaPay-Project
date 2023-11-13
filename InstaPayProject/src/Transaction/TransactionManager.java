package Transaction;

import AccountDetails.Account;
import AccountDetails.AccountAPIProvider;
import InstaPayManager.DataManager;
import java.time.LocalDate;
import java.util.Scanner;
import static Authentication.Authentication.loggedInUser;
public class TransactionManager {
    private DataManager DM;

    public TransactionManager(DataManager dm) {
        this.DM = dm;
    }

    public void chooseBill() {
        System.out.println("Choose which bill you want to pay:\n1-Water bill \t 2-Gas Bill \t 3-Electricity Bill \t 4-Back");
        Scanner sc = new Scanner(System.in);
        String choice;
        choice = sc.next();
        switch (choice) {
            case "1" -> {
                int id = (int) (Math.random() * 1000000);
                Bill bill = new WaterBill(LocalDate.now(), Integer.toString(id),
                        loggedInUser.getMobileNumber(),new BillAPI(BillType.WATER_BILL,"dummy"));
                payBill(bill);
            }
            case "2" -> {
                int id = (int) (Math.random() * 1000000);
                Bill bill = new GasBill(LocalDate.now(), Integer.toString(id),
                        loggedInUser.getMobileNumber(),new BillAPI(BillType.GAS_BILL,"dummy"));
                payBill(bill);
            }
            case "3" -> {
                int id = (int) (Math.random() * 1000000);
                Bill bill = new ElectricityBill(LocalDate.now(), Integer.toString(id),
                        loggedInUser.getMobileNumber(),new BillAPI(BillType.ELECTRICITY_BILL,"dummy"));
                payBill(bill);
            }
            case "4" -> {
                return;
            }
            default -> {
                System.out.println("Incorrect number, please try again [1-4]");
                chooseBill();
            }
        }
    }

    public void payBill(Bill bill) {
        //displaying the bill details
        bill.displayBillDetails();
        System.out.println("Proceed with payment?\n1-Yes \t 2-No");
        Scanner sc = new Scanner(System.in);
        String choice;
        choice = sc.next();
        switch (choice) {
            case "1" -> {
                // deductBill not yet implemented
                if (bill.getApiProvider().deductBill(bill)) {
                    System.out.println("Bill paid successfully");
                } else {
                    System.out.println("Failed to pay the bill");
                }
            }
            case "2" -> {
                System.out.println("Bill payment canceled");
            }
            default -> {
                System.out.println("Incorrect number, please try again [1-2]");
                payBill(bill);
            }
        }
    }

    public boolean transferToWallet() {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the phone number of the wallet you wish to send to: ");
        String wallet = sc.next();
        String company;
        System.out.print("Enter the wallet's company:\n1-Vodafone \t 2-Fawry");
        String choice;
        choice = sc.next();
        switch (choice) {
            case "1" -> {
                company = "Vodafone";
            }
            case "2" -> {
                company = "Fawry";
            }
            default -> {
                System.out.println("Incorrect number, please try again [1-2]");
                transferToWallet();
            }
        }
        //Check if phone number exists in the company's database
        // if not return false and print wallet doesn't exist

        // to be fixed when AccountAPI constructor is made
//         AccountAPIProvider receiverApi = new AccountAPIProvider();
//        System.out.println("Your current balance: " + AccountAPIProvider.inquireBalance());
        System.out.print("Amount to be transferred: ");
        double amount = sc.nextDouble();
//        if (AccountAPIProvider.inquireBalance() < amount){
//            System.out.println("You don't have enough money");
//            return false;
//        }
//        loggedInUser.getApi().withdraw(amount);
//        receiverApi.deposit(amount);
        return true;
    }

    public boolean transferToAccount() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the account name you wish to send to: ");
        String accName = sc.nextLine();
        //check el database law fe account be accName da
        //law la2 return false wa print acc doesn't exist
        //create instance bel receiver account
        System.out.print("Amount to be transferred: ");
        double amount = sc.nextDouble();
//        double balance = loggedInUser.getApi().inquireBalance();
//        if (amount > balance){
//            System.out.println("You don't have enough money");
//            return false;
//        }
//        loggedInUser.getApi().withdraw(amount);
//         receiver.getApi().deposit(amount);
        return true;
    }

    public boolean transferToBank() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the account name you wish to send to: ");
        String accName = sc.nextLine();
        System.out.println("Enter the bank number you wish to send to: ");
        String bankNum = sc.next();
        // check if accName exists with this bankNum
        // if not return false and print unable to transfer to unregistered bank account
        // create instance of receiving account
        System.out.print("Amount to be transferred: ");
        double amount = sc.nextDouble();
//        double balance = loggedInUser.getApi().inquireBalance();
//        if (amount > balance){
//            System.out.println("You don't have enough money");
//            return false;
//        }
//        loggedInUser.getApi().withdraw(amount);
//         receiver.getApi().deposit(amount);
        return true;
    }
}
