package TransactionDetails;

import java.time.LocalDate;

public class ElectricityBill extends Bill {
    private double electricityConsumed;

    public ElectricityBill(LocalDate d, String id, String mobile, PaymentAPIProvider api) {
        super(d, id, mobile, api);
        this.electricityConsumed = Math.round(Math.random() * 1000 * 100.0) / 100.0;
        this.cost = Math.round(electricityConsumed * 0.75 * 100.0) / 100.0;
        this.tax = Math.round(cost * 0.15 * 100.0) / 100.0;
        this.total = Math.round((cost + cost * 0.15) * 100.0) / 100.0;
    }

    @Override
    public void displayBillDetails() {
        System.out.println("Bill Type: " + this.apiProvider.name);
        System.out.println("Bill id: " + this.transactionID);
        System.out.println("Date: " + this.transactionDate);
        System.out.println("Mobile number: " + this.mobileNumber);
        System.out.println("Electricity consumed: " + this.electricityConsumed + " Kilowatts");
        System.out.println("Cost: " + this.cost + " EGP");
        System.out.println("Taxes: " + this.tax + " EGP");
        System.out.println("Total: " + this.total + " EGP");
        System.out.println();
    }
}
