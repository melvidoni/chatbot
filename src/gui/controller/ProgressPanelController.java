package gui.controller;

import bot.agent.loader.SimulatorLoader;
import gui.language.ChatbotLanguage;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;




/**
 * Controller class for the ProgressPanel.fxml view. It generates the simulator
 * loader, and loads the agent while showing the new panel.
 * @author Melina Vidoni, INGAR CONICET-UTN, 2016.
 */
public class ProgressPanelController extends Controller {
    @FXML private ProgressBar progressBar;
    @FXML private Label progressLabel;

    private SimpleStringProperty progressValue = new SimpleStringProperty();
    private SimulatorLoader simulatorLoader;


    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Add a listener to the property
        progressValue.addListener((observableValue, oldValue, newValue) -> {
            // If the simulator is loaded
            if( newValue.equals("DONE") ) {
               mainApp.callMainPanel(simulatorLoader.getChatbotSimulator());
            }
        });

        // Get the simulator loader
        // TODO CHANGE DEFAULT LANGUAGE
        simulatorLoader = new SimulatorLoader(ChatbotLanguage.SPANISH);

        // Bind the properties
        progressBar.progressProperty().bind(simulatorLoader.progressProperty());
        progressLabel.textProperty().bind(simulatorLoader.messageProperty());
        progressValue.bind(simulatorLoader.titleProperty());

        // Run the long task
        Thread th = new Thread(simulatorLoader);
        th.setDaemon(true);
        th.start();
    }


}
