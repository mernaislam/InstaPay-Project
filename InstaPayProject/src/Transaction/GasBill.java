package Transaction;

import java.util.Date;

public class GasBill extends Bill{
    public GasBill(Date d, String id, double amount, String mobile, PaymentAPIProvider api) {
        super(d, id, amount, mobile, api);
    }

    @Override
    public void displayBillDetails() {

    }
}
