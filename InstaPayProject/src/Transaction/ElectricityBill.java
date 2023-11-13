package Transaction;

import java.util.Date;

public class ElectricityBill extends Bill{

    public ElectricityBill(Date d, String id, double amount, String mobile, PaymentAPIProvider api) {
        super(d, id, amount, mobile, api);
    }

    @Override
    public void displayBillDetails() {

    }
}
