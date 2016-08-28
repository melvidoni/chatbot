package bot.agent.operators.rules;


import bot.agent.operators.MoveToWordAction;
import bot.knowledge.graph.Graph;
import bot.knowledge.graph.GraphNode;
import bot.readers.auxialiary.WordNormalizer;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;




/**
 * Singleton class that keeps track of the leaf words on the graph, their rules
 * and the actions that the agent can take about
 * @author Melina Vidoni, INGAR CONICET-UTN, 2016.
 */
public class OperatorRulesMap {

    /**
     * Map that handles the rules associated to an operator
     */
    private Map<String, OperatorRules> rulesRelatedToOperatorMap;

    /**
     * Map that handles the relation between a graph node and its operator
     */
    private Map<String, MoveToWordAction> actionsRelatedToOperatorMap;

    /**
     * Unordered list with all the actions
     */
    private LinkedList<MoveToWordAction> actionsList;


    /**
     * Id of the rule
     */
    private int ruleNumber;


    /**
     * A reference to itself, because this is a singleton class.
     */
    private static OperatorRulesMap instance = null;


    /**
     * Method that obtains and return the current instance of the
     * class, in order to avoid creating multiple instances.
     * @return The singleton instance.
     */
    public static OperatorRulesMap getInstance() {
        if(instance == null)
            instance = new OperatorRulesMap();
        return instance;
    }


    /**
     * Private empty default constructor of the class
     */
    private OperatorRulesMap() {
        rulesRelatedToOperatorMap = new HashMap<>();
        actionsRelatedToOperatorMap = new HashMap<>();
        actionsList = new LinkedList<>();
        ruleNumber = 0;

        // Initialize the maps
        initMaps();
    }


    /**
     * Method that initializes the maps and the action list, with the
     * information available on the glossary graph. It is still incomplete.
     */
    private void initMaps() {
        // Get a reference to the graph
        Graph glossaryGraph = Graph.getInstance();

        // Now go through all the nodes, no matter the order
        for(GraphNode graphNode: glossaryGraph.getAllNodes()) {
            // Get the name of the node
            String nodeName = graphNode.getName();

            // Create a new action for this node
            MoveToWordAction newAction = new MoveToWordAction(nodeName);

            // Add the action to the maps
            rulesRelatedToOperatorMap.put(nodeName, new OperatorRules(nodeName));
            actionsRelatedToOperatorMap.put(nodeName, newAction);

            // Also, add the action to the end of the list
            actionsList.addLast(newAction);
        }
    }





    /**
     * This method normalizes the questions received. Then, for the last word of each question
     * (a leaf word on the map) adds rules for all the possible answers that were received as
     * parameters.
     * @param questions The questions that can be answered. Not normalized.
     * @param answers The answers that answer the question.
     */
    public void createRulesFor(LinkedList<String> questions, LinkedList<String> answers) {
        // Normalize all the questions
        LinkedList< LinkedList<String> > normalizedQuestions = new LinkedList<>();

        // For each string of questions (each one has several words
        for(String q: questions) {
            // Normalize it, and add it
            normalizedQuestions.add( WordNormalizer.normalizeSentence(q.split(" ")) );
        }

        // Now, for each normalized question
        for(LinkedList<String> nQuestion: normalizedQuestions) {
            // I get the last word
            String lastWord = nQuestion.getLast();

            // Now I need to add each answer to this leaf node word
            for(String anAnswer: answers) {
                // Try and get the ID for this rule
                String ruleID = ifExistsGetRuleID(anAnswer);

                // If the rule was added, the ID is different than empty
                if(!ruleID.equals("")) {
                    // The simply add a reference to that rule, with the same ID
                    rulesRelatedToOperatorMap.get(lastWord).addRule(nQuestion, anAnswer, ruleID);
                }
                // If the rule was not added
                else {
                    // Get the entry for the last word, and add a new rule
                    rulesRelatedToOperatorMap.get(lastWord).addRule(nQuestion, anAnswer, "R"+ruleNumber);

                    // Then increase the rule number
                    ruleNumber++;
                }
            }
        }
    }


    /**
     * Method that checks if the map contains a rule (for any given operator) with an textual
     * answer that matches the one received as parameter. If found, it returns the ID, otherwise
     * it returns an empty string.
     * @param anAnswer The textual answer to look for on the map.
     * @return If found, the ID of the rule, otherwise an empty string.
     */
    private String ifExistsGetRuleID(String anAnswer) {
        // Iterate on the map
        for(Map.Entry<String, OperatorRules> mapEntry: rulesRelatedToOperatorMap.entrySet()) {
            // Get the ID of the rule
            String ruleID = mapEntry.getValue().ifExistsGetRuleID(anAnswer);

            // If the ID is not empty, we found the rule and return it
            if(!ruleID.equals("")) return ruleID;
        }

        // Otherwise, we got here, the rule does not yet exists
        return "";
    }






    /**
     * Links the rules that were generated from the file, to the operators
     * generated based on the search tree.
     */
    public void linkActionsWithRules() {
        /*
        Go through all the entries on the map that stores the relations between
        the rules and the operators (last words, or leaf words).
         */
        for(Map.Entry entry: rulesRelatedToOperatorMap.entrySet()) {
            // For the same leaf word on the other map...
            actionsRelatedToOperatorMap.get(entry.getKey()).
                    setRulesList( ((OperatorRules) entry.getValue()).getRelatedRules() );
            // ...set the rules that are related to this entry
        }
    }




    /**
     * Getter to obtain all the available actions.
     * @return A list of unordered actions.
     */
    public LinkedList<MoveToWordAction> getActionsList() {
        return actionsList;
    }



    /**
     * Method that obtains the rules related to a MoveToWordAction, were the
     * destination word is the same as the parameter. This method is used to
     * find the rules related to the first word of the statement.
     * @param word Must match the destination word of the operator.
     * @return The list of rules for the operator.
     */
    public LinkedList<Rule> getRulesOperators(String word) {
        return rulesRelatedToOperatorMap.get(word).getRelatedRules();

    }

}
