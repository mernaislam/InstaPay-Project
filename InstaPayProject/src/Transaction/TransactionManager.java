package Transaction;

import AccountDetails.AccountAPIProvider;
import InstaPayManager.DataManager;
import Authentication.*;
import java.util.Scanner;

public class TransactionManager {
    private DataManager DM;

    public TransactionManager(DataManager dm){
        this.DM = dm;
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
                System.out.println("Incorrect number, please try again [1-3]");
                payBill(bill);
            }
        }
        return;
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
                System.out.println("Incorrect number, please try again [1-3]");
                transferToWallet();
            }
        }
//lma el constructor yt3ml
        AccountAPIProvider receiverApi = new AccountAPIProvider();
//        System.out.println("Your current balance: " + AccountAPIProvider.inquireBalance());
        System.out.print("Amount to be transferred: ");
        double amount = sc.nextDouble();
//        if (AccountAPIProvider.inquireBalance() < amount){
//            System.out.println("Amount is more than your current balance.");
//            return false;
//        }
//        Authentication.user.getApi().withdraw(amount);
//        receiverApi.deposit(amount);
            return true;
    }

    public boolean transferToAccount() {
        return true;
    }

    public boolean transferToBank() {
        return true;
    }
}
