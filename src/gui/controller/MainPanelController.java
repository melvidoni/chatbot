package gui.controller;


import bot.agent.ChatbotSimulator;
import bot.knowledge.auxialiary.WordNormalizer;
import bot.knowledge.readers.ExtraAnswersList;
import gui.ViewFilesLocation;
import gui.language.BundlesKeywords;
import gui.language.ChatbotLanguage;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

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
     * A reference to the locale of the interface
     */
    private Locale locale;



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

        // Set default locale
        // TODO CHANGE LANGUAGE HERE
        locale = new Locale(ChatbotLanguage.SPANISH.getAcronym().toLowerCase(), ChatbotLanguage.SPANISH.getAcronym());
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



    @FXML
    public void showCredits() {
        // Get the bundle with the current locale
        ResourceBundle bundle = ResourceBundle.getBundle(ViewFilesLocation.LOCALE_BUNDLE.toString(), locale);

        // Prepare the alert
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        // Set the icon and title
        alert.setTitle( bundle.getString(BundlesKeywords.CREDITS_WINDOW_TITLE.toString()) );

        // Set the icon
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(this.getClass().getResource(ViewFilesLocation.LOGO_ICON.toString()).toString()));

        // Set header text
        alert.setHeaderText( bundle.getString(BundlesKeywords.CREDITS_WINDOW_HEADER.toString()) );

        // Change main icon and text
        alert.setGraphic(new ImageView(this.getClass().getResource(ViewFilesLocation.CREDITS_ICON_BIG.toString()).toString()));
        alert.setContentText( bundle.getString(BundlesKeywords.CREDITS_WINDOW_MSG.toString()) );

        // Show and wait
        alert.showAndWait();
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
        // Set the locale
        locale = new Locale(ChatbotLanguage.ENGLISH.getAcronym().toLowerCase(), ChatbotLanguage.ENGLISH.getAcronym());

        // Update this panel
        changeLanguage();

        // Reload the answers
        ExtraAnswersList.getInstance().loadExtraAnswers(ChatbotLanguage.ENGLISH.getName());
    }


    /**
     * Action listener on the toggle, to change the language of the bot to Spanish.
     */
    @FXML
    public void setSpanishLanguage() {
        // Set the locale
        locale = new Locale(ChatbotLanguage.SPANISH.getAcronym().toLowerCase(), ChatbotLanguage.SPANISH.getAcronym());

        // Update this panel
        changeLanguage();

        // Reload the answers
        ExtraAnswersList.getInstance().loadExtraAnswers(ChatbotLanguage.SPANISH.getName());
    }



    /**
     * Method that changes the languages of the panel, according to the locale received.
     */
    private void changeLanguage() {
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
