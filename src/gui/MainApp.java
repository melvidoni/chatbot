package gui;

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
    private Stage primaryStage;


    /**
     * Custom method to start the chatbot application.
     * @param stage The gui stage.
     */
    @Override
    public void start(Stage stage) {
        // Config the main stage
        this.primaryStage = stage;
        this.primaryStage.setTitle("ISI Chatbot");
        this.primaryStage.setResizable(false);
        this.primaryStage.getIcons().add(new Image( ViewFilesLocation.LOGO_ICON.toString() ));

        try {
            // Load the layout from the fxml file
            Parent root = FXMLLoader.load(getClass().getResource( ViewFilesLocation.MAIN_PANEL.toString() ));

            // Launch the scene
            stage.setScene(new Scene(root));
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
