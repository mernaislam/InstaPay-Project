package Transaction;

import java.util.Date;

public class WaterBill extends Bill{
    public WaterBill(Date d, String id, double amount, String mobile, PaymentAPIProvider api) {
        super(d, id, amount, mobile, api);
    }

    @Override
    public void displayBillDetails() {

    }
}
