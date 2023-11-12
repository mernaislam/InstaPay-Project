package InstaPayManager;

import Authentication.Authentication;

import java.util.Scanner;

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
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to InstaPay: \n1. Sign in \n2. Sign up \n3. Exit");
        int choice;
        choice = sc.nextInt();
        switch (choice) {
            case 1 -> { // login
                if(!auth.login()){
                    run();
                }
                // ------------------------- display options based on account type
            }
            case 2 -> { // sign up
                auth.register();
                // ------------------------- display options based on account type
            }
            case 3 -> System.out.println("Thank you for using the app!");
            default -> {
                System.out.println("Incorrect number, please try again [1-3]");
                run();
            }
        }
    }
    public void displayOptions(){

    }
}
