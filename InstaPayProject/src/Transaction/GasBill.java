package Transaction;

import java.time.LocalDate;
import java.util.Date;

public class GasBill extends Bill {
    private double gasConsumed;

    public GasBill(LocalDate d, String id, String mobile, PaymentAPIProvider api) {
        super(d, id, mobile, api);
        this.gasConsumed = Math.random() * 100.00;
        this.cost = gasConsumed * 0.15;
        this.total = cost + cost * 0.05;
    }

    @Override
    public void displayBillDetails() {
        System.out.println("Bill Type: " + this.apiProvider.name);
        System.out.println("Bill id: " + this.transactionID);
        System.out.println("Date: " + this.transactionDate);
        System.out.println("Mobile number: " + this.mobileNumber);
        System.out.println("Gas consumed: " + this.gasConsumed + " cubic meters");
        System.out.println("Cost: " + this.cost + " EGP");
        System.out.println("Taxes: " + this.cost * 0.05 + " EGP");
        System.out.println("Total: " + this.total + " EGP");
        System.out.println();
    }
}
