package gui;

import bot.agent.ChatbotSimulator;
import gui.controller.MainPanelController;
import gui.controller.ProgressPanelController;
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

    private Stage progressStage;
    private Stage mainStage;
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
            e.printStackTrace();
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
            MainPanelController mpController = (MainPanelController) loader.getController();

            // Load the information
            mpController.setMainApp(this);
            mpController.setSimulator(simulator);

            // Set the scene
            mainStage.setScene(scene);
            mainStage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }




    /**
     * MainApp method that launches the application.
     * @param args Received parameters.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
