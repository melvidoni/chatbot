package gui.controller;


import bot.agent.ChatbotSimulator;
import bot.knowledge.auxialiary.WordNormalizer;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


/**
 * Controller class for the main panel of the application.
 * @link gui.view.MainPanel.fxml
 * @author Melina Vidoni, INGAR CONICET-UTN, 2016.
 */
public class MainPanelController extends Controller {
    @FXML private TextField askTextField;
    @FXML private Label answerLabel;

    @FXML private Label suggestionsLabel;

    @FXML private Menu languagesMenu;
    @FXML private RadioMenuItem englishMenuItem;
    @FXML private RadioMenuItem spanishMenuItem;
    @FXML private Menu helpMenu;
    @FXML private MenuItem creditsMenu;

    /**
     * Flag for the key listener to clean the text field.
     */
    private boolean firstKey;

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
        // Focus on the text field
        askTextField.requestFocus();

        // Set the flag as true;
        firstKey = true;
    }


    /**
     * Listener for the label. The user must press enter to launch the evaluation.
     * @param keyEvent They event to evaluate.
     */
    @FXML
    public void askEnterPressed(KeyEvent keyEvent) {
        // If the enter key was pressed
        if(keyEvent.getCode().equals(KeyCode.ENTER)) {
            // Call the private method
            getAnAswer();

            // Restart the flag
            firstKey = true;
        }
        // If not, if the user is writting and this is the first key
        else if(firstKey){
            // Set the flag as false
            firstKey = false;
            // And clear the text field
            askTextField.setText("");
        }
    }


    /**
     * Method that launches the simulation and attempts to find an answer
     * for the question the user entered. It checks that the text field is
     * not empty, or it shows an error.
     */
    private void getAnAswer() {
        // If the textfield is not empty
        if(!askTextField.getText().isEmpty()
                && !WordNormalizer.normalizeWord(askTextField.getText()).isEmpty()) {
            // The simulator reads the question
            simulator.setReadStatement(askTextField.getText());

            // Start the simulator
            simulator.start();

            // Print the answer
            String answer = simulator.getFinalAnswer();

            System.out.println("Answer on controller: " + answer );

            // Show the answer
            answerLabel.setText( answer );
        }
        else {
            // TODO CHANGE BORDER
        }
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
