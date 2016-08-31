package gui;

/**
 * Enumerated class for the routes of the view files.
 * @author Ing. Melina C. Vidoni - 2016
 *
 */
public enum ViewFilesLocation {
    LOGO_ICON("/gui/view/img/appicon.png"),
    CREDITS_ICON_BIG("/gui/view/img/creditsicon-big.png"),
    ERROR_ICON_BIG("/gui/view/img/erroricon-big.png"),

    MAIN_PANEL("/gui/view/MainPanel.fxml"),
    PROGRESS_PANEL("/gui/view/ProgressPanel.fxml"),

    LOCALE_BUNDLE("gui.language.bundles.chatbotlocale");

    /**
     * The path where the files are located. These are not absolute
     * and are inside the source.
     */
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
        else if(CREDITS_ICON_BIG.toString().equals(n))
            return CREDITS_ICON_BIG;
        else if(ERROR_ICON_BIG.toString().equals(n))
            return ERROR_ICON_BIG;

        else if(MAIN_PANEL.toString().equals(n))
            return MAIN_PANEL;
        else if(PROGRESS_PANEL.toString().equals(n))
            return PROGRESS_PANEL;
        else if(LOCALE_BUNDLE.toString().equals(n))
            return LOCALE_BUNDLE;
        return null;
    }



    /**
     * Method that converts the current enum on a string.
     */
    public String toString() {
        return route;
    }

}