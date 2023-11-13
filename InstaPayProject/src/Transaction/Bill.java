package Transaction;

import java.util.Date;

public abstract class Bill {
    protected Date transactionDate;
    protected String transactionID;
    protected double cost;
    protected double total;
    protected String mobileNumber;
    protected PaymentAPIProvider apiProvider;

    public Bill(Date d, String id, String mobile, PaymentAPIProvider api) {
        this.transactionDate = d;
        this.transactionID = id;
        this.mobileNumber = mobile;
        this.apiProvider = api;
    }

    public abstract void displayBillDetails();

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date d) {
        this.transactionDate = d;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String id) {
        this.transactionID = id;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double amount) {
        this.total = amount;
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
