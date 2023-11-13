package Transaction;

import java.time.LocalDate;
import java.util.Date;

public class WaterBill extends Bill{
    public WaterBill(LocalDate d, String id, double amount, String mobile, PaymentAPIProvider api) {
        super(d, id, amount, mobile, api);
    }

    @Override
    public void displayBillDetails() {

    }
}
