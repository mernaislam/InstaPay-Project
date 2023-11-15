package TransactionDetails;

import AccountDetails.*;
import InstaPayManager.DataManager;
import java.time.LocalDate;
import java.util.Scanner;
import static Authentication.Authentication.loggedInUser;

/**
 * The {@code TransactionManager} class manages various transactions, including bill payments and
 * transfers.
 *
 * <p>This class is part of the {@code TransactionDetails} package.
 *
 * @see Bill
 * @see PaymentAPIProvider
 */
public class TransactionManager {
    private DataManager DM;

    /**
     * Constructs a new {@code TransactionManager} with the specified data manager.
     *
     * @param dm The data manager to be used for transactions.
     */
    public TransactionManager(DataManager dm) {
        this.DM = dm;
    }

     /**
     * Allows the user to choose a bill type for payment.
     */
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

    /**
     * Processes the payment for the given bill.
     *
     * @param bill The bill to be paid.
     */
    public boolean payBill(Bill bill) {
        //displaying the bill details
        double balance = loggedInUser.getApi().inquireBalance();
        bill.displayBillDetails();
        System.out.println("\nCurrent balance: " + balance);
        System.out.println("New balance: " + (balance - bill.getTotal()));
        System.out.println("Proceed with payment?\n1-Yes \t 2-No");
        Scanner sc = new Scanner(System.in);
        String choice;
        choice = sc.next();
        switch (choice) {
            case "1" -> {
                if (bill.getApiProvider().deductBill(bill)) {
                    System.out.println("Bill paid successfully");
                    return true;
                } else {
                    System.out.println("Failed to pay the bill");
                    return false;
                }
            }
            case "2" -> {
                System.out.println("Bill payment canceled");
                return false;
            }
            default -> {
                System.out.println("Incorrect number, please try again [1-2]");
                payBill(bill);
            }
        }
        return true;
    }

    /**
     * Transfers money to another user's wallet.
     *
     * @return {@code true} if the transfer is successful, {@code false} otherwise.
     */
    public boolean transferToWallet() {

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the phone number of the wallet you wish to send to: ");
        String mobileNum = sc.next();
        WalletType company = WalletType.Fawry;
        System.out.println("Choose the wallet provider:\n1-Vodafone \t 2-Fawry \t 3-Exit");
        String choice;
        choice = sc.next();
        switch (choice) {
            case "1" -> {
                company = WalletType.VodafoneCash;
            }
            case "2" -> {
                company = WalletType.Fawry;
            }
            case "3" -> {
                return false;
            }
            default -> {
                System.out.println("Incorrect number, please try again [1-3]");
                transferToWallet();
            }
        }
        AccountAPIProvider wallet = new WalletAPI(company);
        if (!wallet.verifyAccount(mobileNum, company.toString())){
            System.out.println("Wallet doesn't exist");
            return false;
        }

        System.out.println("Your current balance: " + loggedInUser.getApi().inquireBalance());
        System.out.print("Amount to be transferred: ");
        double amount = sc.nextDouble();
        if (!loggedInUser.getApi().withdraw(amount, loggedInUser)){
            System.out.println("You don't have enough money");
            return false;
        }
        wallet.deposit(amount, null);
        System.out.println("Transfer successful!");

        return true;
    }

    /**
     * Transfers money to another InstaPay account.
     *
     * @return {@code true} if the transfer is successful, {@code false} otherwise.
     */
    public boolean transferToAccount() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the account name you wish to send to: ");
        String accName = sc.nextLine();
        Account receiver = DM.retrieveAccount(accName);
        if (receiver == null){
            System.out.print("This account doesn't exist");
            return false;
        }
        System.out.println("Your current balance: " + loggedInUser.getApi().inquireBalance());
        System.out.print("Amount to be transferred: ");
        double amount = sc.nextDouble();
        if (!loggedInUser.getApi().withdraw(amount, loggedInUser)){
            System.out.println("You don't have enough money");
            return false;
        }
        receiver.getApi().deposit(amount, receiver);
        System.out.println("Transfer successful!");
        return true;
    }

    /**
     * Transfers money to a bank account.
     *
     * @return {@code true} if the transfer is successful, {@code false} otherwise.
     */
    public boolean transferToBank() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the Bank account number you wish to send to: ");
        String bankNum = sc.next();
        System.out.println("Choose his bank provider:\n1-Bank Misr \t 2-QNB \t 3-CIB \t 4-Exit");
        BankType company = BankType.CIB;

        String choice;
        choice = sc.next();
        switch (choice) {
            case "1" -> {
                company = BankType.BankMisr;
            }
            case "2" -> {
                company = BankType.QNB;
            }
            case "3" -> {
                company = BankType.CIB;
            }
            case "4" -> {
                return false;
            }
            default -> {
                System.out.println("Incorrect number, please try again [1-4]");
                transferToBank();
            }
        }
        AccountAPIProvider bank = new BankAPI(company);
        if (!bank.verifyAccount(bankNum, company.toString())){
            System.out.println("Bank account doesn't exist");
            return false;
        }
        System.out.println("Your current balance: " + loggedInUser.getApi().inquireBalance());
        System.out.print("Amount to be transferred: ");
        double amount = sc.nextDouble();
        if (!loggedInUser.getApi().withdraw(amount, loggedInUser)){
            System.out.println("You don't have enough money");
            return false;
        }
         bank.deposit(amount, null);
        System.out.println("Transfer successful!");
        return true;
    }
}
