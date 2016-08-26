package gui;

/**
 * Enumerated class for the routes of the view files.
 * @author Ing. Melina C. Vidoni - 2016
 *
 */
public enum ViewFilesLocation {
    LOGO_ICON("/gui/view/img/appicon.png"),
    MAIN_PANEL("/gui/view/MainPanel.fxml");

    private String route;



    /**
     * Default constructor of the class.
     * @param l name of the element.
     */
    ViewFilesLocation(String l) {
        route = l;
    }




    /**
     * Method that allows the system to get a enum that matches with the received label.
     * @param n The label to be compared with.
     * @return The enum object if it exists, or a null object.
     */
    public static ViewFilesLocation getLabel(String n){
        if(LOGO_ICON.toString().equals(n))
            return LOGO_ICON;
        else if(MAIN_PANEL.toString().equals(n))
            return MAIN_PANEL;
        return null;
    }



    /**
     * Method that converts the current enum on a string.
     */
    public String toString() {
        return route;
    }

}