package TransactionDetails;

/**
 * The {@code PaymentAPIProvider} abstract class represents a generic payment API provider.
 *
 * <p>This class is part of the {@code TransactionDetails} package.
 *
 */
public abstract class PaymentAPIProvider {
    protected BillType name;
    protected String apiUrl;
    public PaymentAPIProvider(BillType name, String apiUrl){
        this.name = name;
        this.apiUrl = apiUrl;
    }

    public String getApiUrl(){
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public BillType getName(){
        return name;
    }

    public void setName(BillType name) {
        this.name = name;
    }

    public abstract boolean deductBill(Bill bill);
}

