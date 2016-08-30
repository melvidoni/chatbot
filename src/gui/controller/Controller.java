package gui.controller;

import gui.MainApp;
import javafx.fxml.FXML;


/**
 * Dummy class that works as a parent class for all the controllers, so the application
 * can change panels accordingly without worrying.
 * @author Melina Vidoni, INGAR CONICET-UTN, 2016.
 */
public abstract class Controller {
    /**
     * Reference to the main application launcher.
     */
    MainApp mainApp;



    /**
     * The default constructor of the class.
     * The constructor is called before the initialize() method.
     */
    Controller() { }


    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() { }


    /**
     * Is called by the main application to give a reference back to itself.
     * @param ma Reference to the main application of the class.
     */
    public void setMainApp(MainApp ma) {
        mainApp = ma;
    }


    /**
     * Called when the user clicks on the exit button.
     */
    @FXML
    private void exitSystem() {
        // TODO DO SOMETHING
    }

}
