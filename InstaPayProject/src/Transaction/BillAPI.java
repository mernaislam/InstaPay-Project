package Transaction;

import Authentication.Authentication;

public class BillAPI extends PaymentAPIProvider {

    public BillAPI(BillType name, String apiUrl) {
        super(name, apiUrl);
    }

    @Override
    public boolean deductBill(Bill bill) {
//        double balance = Authentication.user.getApi().incquireBalance();
//        if (bill.getAmount() > balance){
//            System.out.println("You don't have enough money");
//            return false;
//        }
//        Authentication.user.getApi().withdraw(bill.getAmount());
        return true;

    }


}
