package bot.knowledge.record;


import bot.knowledge.auxialiary.GlossaryFilesLocation;
import bot.knowledge.readers.ExtraAnswersList;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        list = new LinkedList<>();
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
     * @param filter the filters used for this node.
     */
    public void addNode(String question, String ruleId, String answer, String filter) {
        // Create a new node
        RecordNode newNode = new RecordNode(question, ruleId, answer, filter);

        System.out.println("Adding to record: " + newNode.getAnswer());

        // Add it last to the list
        list.addLast(newNode);
    }



    /**
     * Method that allows finding if an answer was already given or not, by
     * comparing on the list of IDs stored in memory.
     * @param ruleId the ID to be checked.
     * @return true if it was already used, or false in the other hand.
     */
    public boolean isRuleUsed(String ruleId) {
        // Prepare a flag
        boolean used = false;

        // If it is RE
        if(ruleId.equals(ExtraAnswersList.getInstance().getRuleRE().getRuleID())
                || ruleId.equals(ExtraAnswersList.getInstance().getRuleRR().getRuleID())) {
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


    /**
     * Method that saves the record on a file, on the agent's main folder.
     */
    public void saveRecord() {
        try {
            // Get the directory and create it if it does not exists
            File recordDirectory = new File(GlossaryFilesLocation.RECORD_FILE_DIRECTORY.toString());
            recordDirectory.mkdir();

            // Get the date and format it
            SimpleDateFormat formatter = new SimpleDateFormat("yy-MM-dd-HH-mm-ss");
            String dateFormated = formatter.format(new Date());

            // Now get the file for the record
            String recordFileLocation = GlossaryFilesLocation.RECORD_FILE_DIRECTORY.toString()
                    + GlossaryFilesLocation.RECORD_FILE_NAME_START.toString()
                    + dateFormated + GlossaryFilesLocation.RECORD_FILE_NAME_END;

            // Create the print writer
            PrintWriter writer = new PrintWriter(recordFileLocation, "UTF-8");

            // Create a flag for the first
            boolean isFirst = true;

            // For each node on the record
            for(RecordNode rNode: list) {
                // If this is the first node change the flag to false
                if(isFirst)
                    isFirst = false;
                // If this is not the first node, print an empty line
                else writer.println("");

                // Now, for each part of the node, write the line
                rNode.print().forEach( writer::println );
            }

            // When this is done, close the writer
            writer.close();
        }
        catch(IOException e) {
            // TODO HANDLE EXCEPTION
            e.printStackTrace();
        }

    }


    /**
     * Method that clears the current record. All information previously
     * stored is lost. This does not save the record.
     */
    public void clearRecord() {
        // Empty the list
        list = new LinkedList<>();
    }

}
