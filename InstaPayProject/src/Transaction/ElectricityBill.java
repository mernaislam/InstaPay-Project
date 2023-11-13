package Transaction;

import java.time.LocalDate;
import java.util.Date;

public class ElectricityBill extends Bill{
    private double electricityConsumed;

    public ElectricityBill(LocalDate d, String id, String mobile, PaymentAPIProvider api) {
        super(d, id, mobile, api);
        this.electricityConsumed = Math.random() * 100;
        this.cost = electricityConsumed * 0.20;
        this.total = cost + cost * 0.05;
    }

    @Override
    public void displayBillDetails() {
        System.out.println("Bill Type: " + this.apiProvider.name);
        System.out.println("Bill id: " + this.transactionID);
        System.out.println("Date: " + this.transactionDate);
        System.out.println("Mobile number: " + this.mobileNumber);
        System.out.println("Electricity consumed: " + this.electricityConsumed + " Kilowatts");
        System.out.println("Cost: " + this.cost + " EGP");
        System.out.println("Taxes: " + this.cost * 0.05 + " EGP");
        System.out.println("Total: " + this.total + " EGP");
        System.out.println();
    }
}
