package Transaction;

import java.time.LocalDate;
import java.util.Date;

public abstract class Bill {
    protected LocalDate transactionDate;
    protected String transactionID;
    protected double amount;
    protected String mobileNumber;
    protected PaymentAPIProvider apiProvider;

    public Bill(LocalDate d, String id, double amount, String mobile, PaymentAPIProvider api){
        this.transactionDate = d;
        this.transactionID = id;
        this.amount = amount;
        this.mobileNumber = mobile;
        this.apiProvider = api;
    }

    public abstract void displayBillDetails();

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate d) {
        this.transactionDate = d;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String id) {
        this.transactionID = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobile) {
        this.mobileNumber = mobile;
    }

    public PaymentAPIProvider getApiProvider() {
        return apiProvider;
    }

    public void setApiProvider(PaymentAPIProvider apiProvider) {
        this.apiProvider = apiProvider;
    }
}
