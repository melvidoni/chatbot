package bot.agent.loader;


import bot.agent.ChatbotAgent;
import bot.agent.ChatbotEnvironment;
import bot.agent.ChatbotSimulator;
import bot.knowledge.readers.ExtraAnswersList;
import bot.knowledge.readers.GraphReader;
import bot.knowledge.readers.UnimportantWordsList;
import bot.knowledge.readers.synonyms.SynonymsList;
import frsf.cidisi.faia.simulator.Simulator;
import gui.ViewFilesLocation;
import gui.language.BundlesKeywords;
import gui.language.ChatbotLanguage;
import javafx.concurrent.Task;

import java.util.Locale;
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
     * The language of the initialization.
     */
    private ChatbotLanguage cLanguage;



    /**
     * Default empty constructor of the class
     * @param languageName The language in which the agent will be initialized.
     */
    public SimulatorLoader(ChatbotLanguage languageName) {
        // Update the language
        cLanguage = languageName;
    }


    /**
     * A method called when the task starts
     * @return a null object.
     * @throws Exception In case something bad happens
     */
    @Override
    protected Object call() throws Exception {
        // Get the bundle
        ResourceBundle bundle = ResourceBundle.getBundle(ViewFilesLocation.LOCALE_BUNDLE.toString(),
                new Locale(cLanguage.getAcronym().toLowerCase(), cLanguage.getAcronym()));


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
        ExtraAnswersList.getInstance().loadExtraAnswers(cLanguage.getName());
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
        ChatbotAgent chatbot = new ChatbotAgent();
        updateProgress(85, 100);

        /*
        STEP 5: PREPARE THE SIMULATOR
         */
        updateMessage(BundlesKeywords.STAR_SIMULATION_MESSAGE.toString());
        chatbotSimulator = new ChatbotSimulator(environment, chatbot);
        updateProgress(100, 100);
        updateTitle("DONE");


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
