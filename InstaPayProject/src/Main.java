import InstaPayManager.UIManager;

/**
 * The {@code Main} class serves as the entry point for the InstaPay application.
 * It creates an instance of the {@code UIManager} and initiates the main program loop.
 */
public class Main {
    public static void main(String[] args) {
        UIManager M = new UIManager();
        // Run the main loop of the application
        M.run();
    }
}