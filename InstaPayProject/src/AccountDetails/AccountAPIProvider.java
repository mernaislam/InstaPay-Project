package AccountDetails;

public abstract class AccountAPIProvider {
    private InstaPayAPI name;
    String apiUrl;

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public InstaPayAPI getName() {
        return name;
    }

    public void setName(InstaPayAPI name) {
        this.name = name;
    }

    public AccountAPIProvider(InstaPayAPI name, String apiUrl) {
        this.name = name;
        this.apiUrl = apiUrl;
    }
}
