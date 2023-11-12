package Transaction;

import java.util.Date;

public class Bill {
    private Date transactionDate;
    private String transactionID;
    private double amount;
    private String mobileNumber;
    private PaymentAPIProvider apiProvider;

    public Date getTransactionDate() {
        return transactionDate;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public double getAmount() {
        return amount;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public PaymentAPIProvider getApiProvider() {
        return apiProvider;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void setApiProvider(PaymentAPIProvider apiProvider) {
        this.apiProvider = apiProvider;
    }
}
