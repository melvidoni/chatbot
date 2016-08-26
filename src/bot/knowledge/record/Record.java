package bot.knowledge.record;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Class that controls the question/answer record, to log the chat with
 * the robot on a printed file. Newest nodes are always added at the end
 * of the list.
 *
 * @author Melina Vidoni, INGAR CONICET-UTN, 2016.
 */
public class Record {

    /**
     * List with all the nodes of the record
     */
    private LinkedList<RecordNode> list;

    /**
     * A reference to itself, because it is singleton
     */
    private static Record instance = null;



    /**
     * Default constructor of the class
     */
    private Record() {
        list = new LinkedList<RecordNode>();
    }


    /**
     * Method that obtains and return the current instance of the
     * class, in order to avoid creating multiple instances.
     * @return The singleton instance.
     */
    public static Record getInstance () {
        if(instance == null)
            instance = new Record();
        return instance;
    }



    /**
     * Method that adds a new conversational element to the list of
     * already performed nodes.
     * @param question The question asked by the user.
     * @param ruleId The ID of the answer given by the bot.
     */
    public void addNode(String question, String ruleId, String answer, String criteria) {
        RecordNode nuevoNodo = new RecordNode(question, ruleId, answer, criteria);
        list.addLast(nuevoNodo);
    }



    /**
     * Method that allows finding if an answer was already given or not, by
     * comparing on the list of IDs stored in memory.
     * @param ruleId the ID to be checked.
     * @return true if it was already used, or false in the other hand.
     */
    public boolean repeatedAnswer(String ruleId) {
        // Prepare a flag
        boolean used = false;

        // If it is RE
        if(ruleId.equals("Re") || ruleId.equals("Rr")) {
            // Then return false
            return false;
        }
        // Otherwise...
        else {
            // Go through the answers...
            Iterator<RecordNode> iterator = list.iterator();
            while(iterator.hasNext() && !used) {
                // Obtain the next one
                RecordNode rn = iterator.next();

                // If the ID was used, then return true;
                if(rn.getRuleID().equals(ruleId))
                    used = true;
            }
        }

        // Return if it was used
        return used;
    }



    /**
     * Method that prints the chat log in a string, to be then
     * stored in a given file.
     * @return String with the content of each node in the log on one line.
     */
    private String printRecord(){
        // Create the variable that will store the log
        String log = "";

        // Go through the list
        for(RecordNode rn: list) {
            // Add the node printing to the main log
            log = log + rn.print() + "\r\n\r\n";
        }

        // Return the completed log
        return log;
    }



    /**
     * Method that effectively writes the log as a text file, stored in
     * a default location.
     * @param logName Name, including the directory, where the log is going to
     *                be stored.
     * @throws IOException for errors when writing the file.
     */
    public void generateLog(String logName) throws IOException {
        // Create the new file
        File logFile = new File(logName);

        // Open file to be written
        PrintWriter writer = new PrintWriter(new FileWriter(logFile));

        // Write the file
        writer.write(this.printRecord());

        // Close the file
        writer.close();

    }



    /**
     * Method to check if the record is empty.
     * @return true if the record is empty, false otherwise.
     */
    public boolean isEmpty(){
        return list.isEmpty();
    }



    /**
     * Method that returns the last existent answer in the conversation
     * record log.
     * @return A string with the answer in text format.
     */
    public String getLastAnswer() {
        // Get the last node and get the answer.
        return list.getLast().getAnswer();
    }


}
