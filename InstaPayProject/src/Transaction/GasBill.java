package Transaction;

import java.time.LocalDate;
import java.util.Date;

public class GasBill extends Bill{
    public GasBill(LocalDate d, String id, double amount, String mobile, PaymentAPIProvider api) {
        super(d, id, amount, mobile, api);
    }

    @Override
    public void displayBillDetails() {

    }
}
