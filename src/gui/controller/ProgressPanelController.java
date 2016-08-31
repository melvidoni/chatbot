package gui.controller;

import bot.agent.loader.SimulatorLoader;
import gui.language.bundles.BundlesKeywords;
import gui.view.ExceptionAlert;
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
    /**
     * Linked to the progress bar of the panel.
     */
    @FXML private ProgressBar progressBar;

    /**
     * Linked to the label that display the current operation
     * that the simulator loader is performing.
     */
    @FXML private Label progressLabel;

    /**
     * Property related to the progress of the loading. It is used to check
     * if the loader finished correctly or encountered an error.
     */
    private SimpleStringProperty progressValue = new SimpleStringProperty();

    /**
     * Reference to the simulator loader used to kickstart the agent, and whose
     * progress is linked to this stage.
     */
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
            switch (newValue) {
                case "DONE": mainApp.callMainPanel(simulatorLoader.getChatbotSimulator());
                             break;

                case "ERROR": ExceptionAlert.showExceptionAlert(BundlesKeywords.EXCEPTION_STACKTRACE_1);
                              break;
            }
        });

        // Get the simulator loader
        simulatorLoader = new SimulatorLoader();

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
