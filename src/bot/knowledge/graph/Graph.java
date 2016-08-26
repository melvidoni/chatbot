package bot.knowledge.graph;

import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Builds a graph and represents the vocabulary that will be used by the chatbot.
 * @author Melina Vidoni, INGAR CONICET-UTN, 2016.
 */
public class Graph {

    /**
     * List of the available nodes
     */
    private LinkedList<GraphNode> nodes;

    /**
     * TreeMap to create the graph
     */
    private Map<String, GraphNode> map;


    /**
     * An instance to itself
     */
    private static Graph instance = null;



    /**
     * Method that obtains and return the current instance of the
     * class, in order to avoid creating multiple instances.
     * @return The singleton instance.
     */
    public static Graph getInstance () {
        if(instance == null)
            instance = new Graph();
        return instance;
    }



    /**
     * Default private constructor of the class.
     */
    private Graph() {
        nodes = new LinkedList<GraphNode>();
        map = new TreeMap<String, GraphNode>();
    }


    /**
     * Getter to obtain all the nodes on the graph
     * @return All the available nodes in list format.
     */
    public LinkedList<GraphNode> getAllNodes() {
        return nodes;
    }



    /**
     * Get the names of a given node's successors
     * @param nodeName Key for the node that will be used to obtain the successors.
     * @return Name of all the successors of the given node
     */
    public LinkedList<String> successorsName(String nodeName) {
        // Prepare a list
        LinkedList<String> sNames = new LinkedList<String>();

        // Get the node to be studied
        GraphNode node = map.get(nodeName);

        // For each node
        for(GraphNode s : node.getSuccessors()) {
            // Add the successors to the list
            sNames.add(s.getName());
        }

        // Then return the new list
        return sNames;
    }



    /**
     * Add a new node in the graph, after evaluating if it is already
     * contained on it.
     * @param node The new node to be added
     */
    public void addNode(GraphNode node) {
        // Check if the node is already added
        if( !map.containsKey(node.getName()) ) {
            // Add it to the list
            nodes.add(node);

            // Then add it to the map
            map.put(node.getName(), node);
        }
    }



    /**
     * Adds a new set of nodes to the graph, without checking
     * if they already exist.
     * @param n New nodes to be added
     */
    public void addNodes(LinkedList<GraphNode> n) {
        nodes.addAll(n);
    }



    /**
     * Obtain the amount of nodes on the graph.
     * @return An positive integer representing the amount of nodes on the graph.
     */
    public int getNodeQty() {
        return nodes.size();
    }



    /**
     * Adds a new association on the graph, in order to identify the relation between
     * to given words.
     * @param origin Name of the starting node.
     * @param destination Name of the ending node.
     */
    public void addAssociation(String origin, String destination) {
        map.get( origin.toUpperCase()).addSuccessor( map.get(destination.toUpperCase() ) );
    }


    /**
     * Method that overrides the default comparison, in order to give this
     * class a custom one.
     * @param obj Object to be compared with.
     * @return true if the two objects are equals, false in the other case.
     */
    @Override
    public boolean equals(Object obj) {
        // Prepare an object
        Graph object;

        // If it is not a Graph instance
        if(!(obj instanceof Graph)) {
            // Then they are not equals
            return false;
        }
        // Otherwise
        else {
            // Cast the object
            object = ((Graph) obj);
            // Compare the nodes
            if (!nodes.equals(object.getAllNodes()))
                return false;
        }

        // If passed, then they are equal
        return true;
    }



    /**
     * Asks if the graph contains a specific node that has a name equal to the
     * one given on the parameter. It checks the existance of the word on the graph.
     * @param wordToExpand Key of the word that wants to be checked.
     * @return true if it exists, false otherwise.
     */
    public boolean hasNode(String wordToExpand) {
        // Check if it exists
        return map.containsKey(wordToExpand);
    }

}
