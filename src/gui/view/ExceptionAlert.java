package gui.view;


import gui.ViewFilesLocation;
import gui.language.CurrentLocale;
import gui.language.bundles.BundlesKeywords;
import javafx.application.Platform;
import javafx.scene.control.Alert;

import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;

import java.util.Optional;
import java.util.ResourceBundle;



/**
 * Class that shows an exception alert, with a custom message.
 * @author Melina Vidoni, INGAR CONICET-UTN, 2016.
 */
public class ExceptionAlert {


    /**
     * Static method to show an alert with an error message.
     * @param keyword It must be the bundle's keyword of where the error
     *                message is stored.
     */
    public static void showExceptionAlert(BundlesKeywords keyword) {
        // Get the language bundle
        ResourceBundle rBundle = ResourceBundle.getBundle(ViewFilesLocation.LOCALE_BUNDLE.toString(),
                CurrentLocale.getInstance().getLocale());

        // Create the new alert
        Alert alert = new Alert(Alert.AlertType.ERROR);

        // Set title, icon and header
        alert.setTitle( rBundle.getString(BundlesKeywords.EXCEPTION_TITLE.toString()) );
        alert.setHeaderText( rBundle.getString(BundlesKeywords.EXCEPTION_HEADER.toString()) );
        alert.setGraphic(new ImageView(ExceptionAlert.class.getResource(ViewFilesLocation.ERROR_ICON_BIG.toString()).toString()));

        // Set the text
        alert.setContentText( rBundle.getString(keyword.toString()) );

        // Show the alert
        Optional<ButtonType> result = alert.showAndWait();

        // If the result is OK, close the application
        if (result.get() == ButtonType.OK) Platform.exit();
    }


}
