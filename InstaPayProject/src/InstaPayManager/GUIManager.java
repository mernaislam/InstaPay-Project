package InstaPayManager;

import Authentication.Authentication;

public class GUIManager {
    private DataManager DM;
    private Authentication auth;
//    private TransactionManager TM;

    public GUIManager() {
        this.DM = new JSON();
        this.auth = new Authentication();
//        this.TM = new TransactionManager();
    }
    public void run(){

    }
    public void displayOptions(){

    }
}
