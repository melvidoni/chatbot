package gui.controller;


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
public class MainPanelController extends Controller {
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
    private void initialize() { }



    @FXML
    public void askQuestion() {
        // The simulator reads the question
        simulator.setReadStatement(askTextField.getText());

        // Start the simulator
        simulator.start();

        // Print the answer
        String answer = simulator.getFinalAnswer();

        System.out.println("Answer on controller: " + answer );

        // Show the answer
        answerTextArea.setText( answer );
    }


    /**
     * Setter to set a new simulator on the controller. It replaces the previous one
     * and it cannot be restored.
     * @param newSimulator New simulator to set on the panel.
     */
    public void setSimulator(ChatbotSimulator newSimulator) {
        simulator = newSimulator;
    }

}
