package Transaction;

import java.util.Date;

public class WaterBill extends Bill{
    private double waterConsumed;

    public WaterBill(Date d, String id, String mobile, PaymentAPIProvider api) {
        super(d, id, mobile, api);
        this.waterConsumed = Math.random() * 100;
        this.cost = waterConsumed * 0.10;
        this.total = cost + cost * 0.05;
    }

    @Override
    public void displayBillDetails() {
        System.out.println("Bill Type: " + this.apiProvider.name);
        System.out.println("Bill id: " + this.transactionID);
        System.out.println("Date: " + this.transactionDate);
        System.out.println("Mobile number: " + this.mobileNumber);
        System.out.println("Water consumed: " + this.waterConsumed + " cubic meters");
        System.out.println("Cost: " + this.cost + " EGP");
        System.out.println("Taxes: " + this.cost * 0.05 + " EGP");
        System.out.println("Total: " + this.total + " EGP");
        System.out.println();
    }
}
