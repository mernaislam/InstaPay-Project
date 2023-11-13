package Transaction;

public class BillAPI extends PaymentAPIProvider {

    public BillAPI(BillType name, String apiUrl) {
        super(name, apiUrl);
    }

    @Override
    public boolean deductBill(Bill bill) {
        return false;
    }


}
