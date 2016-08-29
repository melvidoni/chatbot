package gui.controller;


import bot.agent.ChatbotSimulator;
import bot.knowledge.auxialiary.WordNormalizer;
import bot.knowledge.readers.ExtraAnswersList;
import gui.ViewFilesLocation;
import gui.language.BundlesKeywords;
import gui.language.ChatbotLanguage;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.effect.Effect;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.controlsfx.control.spreadsheet.Grid;

import java.awt.*;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;


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
    @FXML private ToggleGroup languageToggleGroup;
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

        // TODO CHANGE LANGUAGE
        setSpanishLanguage();
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




    /**
     * Action listener on the toggle, to change the language of the bot to English.
     */
    @FXML
    public void setEnglishLanguage() {
        // Update this panel
        changeLanguage(new Locale(ChatbotLanguage.ENGLISH.getAcronym().toLowerCase(), ChatbotLanguage.ENGLISH.getAcronym()));

        // Reload the answers
        ExtraAnswersList.getInstance().loadExtraAnswers(ChatbotLanguage.ENGLISH.getName());
    }


    /**
     * Action listener on the toggle, to change the language of the bot to Spanish.
     */
    @FXML
    public void setSpanishLanguage() {
        // Update this panel
        changeLanguage(new Locale(ChatbotLanguage.SPANISH.getAcronym().toLowerCase(), ChatbotLanguage.SPANISH.getAcronym()));

        // Reload the answers
        ExtraAnswersList.getInstance().loadExtraAnswers(ChatbotLanguage.SPANISH.getName());
    }



    /**
     * Method that changes the languages of the panel, according to the locale received.
     * @param locale Locale to change the language of the interface.
     */
    private void changeLanguage(Locale locale) {
        // Get the bundle
        ResourceBundle bundle = ResourceBundle.getBundle(ViewFilesLocation.LOCALE_BUNDLE.toString(), locale);

        // Now set the values
        suggestionsLabel.setText(bundle.getString(BundlesKeywords.SUGGESTIONS_LABEL.toString()));
        languagesMenu.setText(bundle.getString(BundlesKeywords.LANGUAGES_MENU.toString()));
        englishMenuItem.setText(bundle.getString(BundlesKeywords.ENGLISH_MENU_ITEM.toString()));
        spanishMenuItem.setText(bundle.getString(BundlesKeywords.SPANISH_MENU_ITEM.toString()));
        helpMenu.setText(bundle.getString(BundlesKeywords.HELP_MENU.toString()));
        creditsMenu.setText(bundle.getString(BundlesKeywords.CREDITS_MENU.toString()));
    }
}
