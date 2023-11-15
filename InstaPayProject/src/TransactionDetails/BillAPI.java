package TransactionDetails;

import static Authentication.Authentication.loggedInUser;

public class BillAPI extends PaymentAPIProvider {

    public BillAPI(BillType name, String apiUrl) {
        super(name, apiUrl);
    }

    @Override
    public boolean deductBill(Bill bill) {
        if (loggedInUser.getApi().withdraw(bill.getTotal(), loggedInUser))
            return true;
        System.out.println("You don't have enough money");
        return false;

    }


}
