package gui.controller;

import bot.agent.ChatbotSimulator;
import bot.agent.SimulatorLoader;
import bot.knowledge.auxialiary.ChatbotLanguage;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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


    /**
     * The simulator being initialized by the loader.
     */
    private ChatbotSimulator chatbotSimulator;



    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Add a listener to the property
        progressValue.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                // Change the information on the panel
                if( newValue.equals("DONE") )
                    System.out.println("SIMULATOR LOADED");
            }
        });


        // Get the simulator loader
        SimulatorLoader simulatorLoader = new SimulatorLoader(ChatbotLanguage.ENGLISH.getName());

        // Bind the properties
        progressBar.progressProperty().bind(simulatorLoader.progressProperty());
        progressLabel.textProperty().bind(simulatorLoader.messageProperty());
        progressValue.bind(simulatorLoader.titleProperty());

        // Run the long task
        Thread th = new Thread(simulatorLoader);
        th.setDaemon(true);
        th.start();
    }


    /**
     * Getter to obtain the initialized simulator.
     * @return A complete initialized simulator.
     */
    public ChatbotSimulator getChatbotSimulator() {
        return chatbotSimulator;
    }



}
