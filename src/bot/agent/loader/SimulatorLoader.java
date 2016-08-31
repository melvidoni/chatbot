package bot.agent.loader;


import bot.agent.ChatbotAgent;
import bot.agent.ChatbotEnvironment;
import bot.agent.ChatbotSimulator;
import bot.knowledge.readers.ExtraAnswersList;
import bot.knowledge.readers.GraphReader;
import bot.knowledge.readers.RulesReader;
import bot.knowledge.readers.UnimportantWordsList;
import bot.knowledge.readers.synonyms.SynonymsList;
import gui.ViewFilesLocation;
import gui.language.bundles.BundlesKeywords;
import gui.language.CurrentLocale;
import javafx.concurrent.Task;
import java.io.IOException;
import java.util.ResourceBundle;




/**
 * Class that loads the environment, the chatbot (along with its knowledge, the
 * graph, the rules, and so on) and the simulator. It is a task, because it is
 * linked to a progress bar.
 * @author Melina Vidoni, INGAR CONICET-UTN, 2016.
 */
public class SimulatorLoader extends Task<Object> {

    /**
     * The simulator being initialized by the loader.
     */
    private ChatbotSimulator chatbotSimulator;


    /**
     * Default empty constructor of the class
     */
    public SimulatorLoader() {
        chatbotSimulator = null;
    }


    /**
     * A method called when the task starts
     * @return a null object.
     * @throws Exception In case something bad happens
     */
    @Override
    protected Object call() throws Exception {
        try {
            // Set up a locale
            CurrentLocale currentLocale = CurrentLocale.getInstance();
            ResourceBundle bundle = ResourceBundle.getBundle(ViewFilesLocation.LOCALE_BUNDLE.toString(),
                    currentLocale.getLocale());

            /*
             STEP 0: START THE PROCESS
             */
            updateMessage(bundle.getString(BundlesKeywords.LOADING_MESSAGE.toString()));
            updateProgress(0, 100);

            /*
             STEP 1: GET THE ENVIRONMENT
             */
            updateMessage(bundle.getString(BundlesKeywords.OBTAINING_ENV_MESSAGE.toString()));
            // Obtain the environment
            ChatbotEnvironment environment = new ChatbotEnvironment();
            updateProgress(10, 100);

            /*
             STEP 2: READ SECONDARY KNOWLEDGE
             */
            updateMessage(bundle.getString(BundlesKeywords.LEARNING_MESSAGE.toString()));
            // Read uninmportant words
            UnimportantWordsList.getInstance().loadWords();
            updateProgress(20, 100);
            // Read synonyms
            SynonymsList.getInstance().loadSynonyms();
            updateProgress(30, 100);
            // Read extra answers
            ExtraAnswersList.getInstance().loadExtraAnswers( CurrentLocale.getInstance().getcLanguage().getName() );
            updateProgress(40, 100);

            /*
            STEP 3: READ MAIN KNOWLEDGE
             */
            updateMessage(bundle.getString(BundlesKeywords.MORE_LEARNING_MESSAGE.toString()));
            GraphReader.loadGraph();
            updateProgress(65, 100);

            /*
            STEP 4: WAKE UP THE AGENT
             */
            updateMessage(bundle.getString(BundlesKeywords.WAKE_UP_CHATBOT_MESSAGE.toString()));
            ChatbotAgent chatbot = new ChatbotAgent(RulesReader.loadActionsList());
            updateProgress(85, 100);

            /*
            STEP 5: PREPARE THE SIMULATOR
             */
            updateMessage(BundlesKeywords.STAR_SIMULATION_MESSAGE.toString());
            chatbotSimulator = new ChatbotSimulator(environment, chatbot);
            updateProgress(100, 100);
            updateTitle("DONE");
        }
        catch(IOException e) {
            // Update the title
            updateTitle("ERROR");
        }

        // Now return null
        return null;
    }



    /**
     * Getter to obtain the initialized simulator.
     * @return A complete initialized simulator.
     */
    public ChatbotSimulator getChatbotSimulator() {
        return chatbotSimulator;
    }

}
