package TransactionDetails;

/**
 * The {@code BillAPI} class extends {@code PaymentAPIProvider} and represents the API provider
 * specifically for handling bills.
 *
 * <p>This class is part of the {@code TransactionDetails} package.
 *
 * @see PaymentAPIProvider
 */
public class BillAPI extends PaymentAPIProvider {

    /**
     * Constructs a new {@code BillAPI} instance with the specified name and API URL.
     *
     * @param name   The type of bill (e.g., water bill, gas bill).
     * @param apiUrl The URL of the API for bill processing.
     */
    public BillAPI(BillType name, String apiUrl) {
        super(name, apiUrl);
    }

    /**
     * Deducts the bill amount from the user's account.
     *
     * @param bill The bill to be deducted.
     * @return {@code true} if the deduction is successful, {@code false} otherwise.
     */
    @Override
    public boolean deductBill(Bill bill) {
//        double balance = loggedInUser.getApi().inquireBalance();
//        if (bill.getAmount() > balance){
//            System.out.println("You don't have enough money");
//            return false;
//        }
//        loggedInUser.getApi().withdraw(bill.getAmount());
        return true;

    }


}
