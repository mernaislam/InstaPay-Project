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
}
