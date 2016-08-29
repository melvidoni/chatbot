package gui;

import gui.controller.ProgressPanelController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
    private Stage primaryStage;

    /**
     * Custom method to start the chatbot application.
     * @param stage The gui stage.
     */
    @Override
    public void start(Stage stage) {
        // First, config the progress stage
        this.progressStage = stage;
        this.progressStage.setTitle("Chatbot");
        this.progressStage.setResizable(false);
        this.progressStage.getIcons().add(new Image( ViewFilesLocation.LOGO_ICON.toString() ));

        try {
            // Load the layout from the fxml file
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource( ViewFilesLocation.PROGRESS_PANEL.toString() ));
            Scene scene = new Scene(loader.load());

            // Set the main app
            ((ProgressPanelController) loader.getController()).setMainApp(this);

            // Set the scene
            stage.setScene(scene);
            stage.show();
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
