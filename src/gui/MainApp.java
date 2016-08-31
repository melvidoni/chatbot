package gui;

import bot.agent.ChatbotSimulator;
import bot.knowledge.record.Record;
import gui.controller.MainPanelController;
import gui.controller.ProgressPanelController;
import gui.language.bundles.BundlesKeywords;
import gui.view.ExceptionAlert;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;


/**
 * MainApp class for ISIChatbot. Runs the application using the main panel.
 * @author Melina Vidoni, INGAR CONICET-UTN, 2016.
 */
public class MainApp extends Application {
    /**
     * The stage of the progress view, that it is shown while
     * the simulator loader loads all the components of the agent.
     */
    private Stage progressStage;

    /**
     * The stage of the main view, used to perform basic operations
     * with the agent.
     */
    private Stage mainStage;

    /**
     * The starting stage, a generic reference that is initialized
     * with the parameter that the method start() obtains.
     */
    private Stage startStage;



    /**
     * Custom method to start the chatbot application.
     * @param stage The gui stage.
     */
    @Override
    public void start(Stage stage) {
        // Save the stage
        startStage = stage;

        // First, config the progress stage
        progressStage = startStage;
        progressStage.setTitle("Chatbot");
        progressStage.setResizable(false);
        progressStage.getIcons().add(new Image( ViewFilesLocation.LOGO_ICON.toString() ));

        try {
            // Load the layout from the fxml file
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource( ViewFilesLocation.PROGRESS_PANEL.toString() ));
            Scene scene = new Scene(loader.load());

            // Set the main app
            ((ProgressPanelController) loader.getController()).setMainApp(this);

            // Set the scene
            progressStage.setScene(scene);
            progressStage.show();
        }
        catch (IOException e) {
            // Show an error message
            ExceptionAlert.showExceptionAlert(BundlesKeywords.EXCEPTION_STACKTRACE_2);
        }
    }


    /**
     * Method that removes the progress window, and replaces it with the main panel,
     * while setting the loaded simulator onto the controller.
     * @param simulator The loaded simulator to set on the controller.
     */
    public void callMainPanel(ChatbotSimulator simulator) {
        // Config the main panel
        mainStage = startStage;
        mainStage.setTitle("Chatbot");
        mainStage.setResizable(false);
        mainStage.getIcons().add(new Image( ViewFilesLocation.LOGO_ICON.toString() ));

        try {
            // Close the progress stage
            progressStage.close();

            // Load the layout from the fxml file
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource( ViewFilesLocation.MAIN_PANEL.toString() ));
            Scene scene = new Scene(loader.load());

            // Get the controller
            MainPanelController mpController = loader.getController();

            // Load the information
            mpController.setMainApp(this);
            mpController.setSimulator(simulator);

            // Set the scene
            mainStage.setScene(scene);

            // Set on close request handler
            mainStage.setOnCloseRequest(we -> MainApp.this.handleRecordClosing());

            // Show the stage
            mainStage.show();
        }
        catch (IOException e) {
            // Show an error message
            ExceptionAlert.showExceptionAlert(BundlesKeywords.EXCEPTION_STACKTRACE_2);
        }

    }


    /**
     * Method to handle the closing of the main stage
     */
    private void handleRecordClosing() {
        // Get the record to save the information
        Record.getInstance().saveRecord();
    }



    /**
     * MainApp method that launches the application.
     * @param args Received parameters.
     */
    public static void main(String[] args) {
        launch(args);
    }

}
