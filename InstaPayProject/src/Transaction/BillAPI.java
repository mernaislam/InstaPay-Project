package Transaction;

import static Authentication.Authentication.loggedInUser;

public class BillAPI extends PaymentAPIProvider {

    public BillAPI(BillType name, String apiUrl) {
        super(name, apiUrl);
    }

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
