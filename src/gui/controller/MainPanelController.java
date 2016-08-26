package gui.controller;


import bot.agent.ChatbotAgent;
import bot.agent.ChatbotEnvironment;
import bot.agent.ChatbotSimulator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;



/**
 * Controller class for the main panel of the application.
 * @link gui.view.MainPanel.fxml
 * @author Melina Vidoni, INGAR CONICET-UTN, 2016.
 */
public class MainPanelController {
    @FXML
    private Button askButton;

    @FXML
    private TextField askTextField;

    @FXML
    private TextArea answerTextArea;

    @FXML
    private Label suggestionsLabel;

    /**
     * A reference to the simulator of the agent.
     */
    private ChatbotSimulator simulator;


    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // TODO SHOW A PROGRESS BAR WHILE INITIALIZING

        // Get the environment
        ChatbotEnvironment environment = new ChatbotEnvironment();

        // Get the chatbot
        ChatbotAgent agent = new ChatbotAgent();

        // Initialize the simulator
        simulator = new ChatbotSimulator(environment, agent);
    }


    @FXML
    public void askQuestion() {
        System.out.println("APRETAMOS EL BOTON");

        // The simulator reads the question
        simulator.setReadStatement(askTextField.getText());

        // Start the simulator
        simulator.start();
    }

}
