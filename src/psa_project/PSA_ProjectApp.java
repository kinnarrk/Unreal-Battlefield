/*
 * PSA_ProjectApp.java
 */

package psa_project;

import org.apache.log4j.Logger;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
public class PSA_ProjectApp extends SingleFrameApplication {

    public final static Logger logger = Logger.getLogger(PSA_ProjectApp.class);
    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        show(new PSA_ProjectView(this));
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of PSA_ProjectApp
     */
    public static PSA_ProjectApp getApplication() {
        return Application.getInstance(PSA_ProjectApp.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        launch(PSA_ProjectApp.class, args);
    }
}
