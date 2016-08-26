package bot.knowledge.graph;

import java.util.LinkedList;

/**
 * Represents a node in the graph of words that is used by the chatbot.
 * @author Melina Vidoni, INGAR CONICET-UTN, 2016.
 */
public class GraphNode {

    /**
     * Name of the node.
     */
    private String name;

    /**
     * Successors for this node
     */
    private LinkedList<GraphNode> successors;



    /**
     * Default constructor for the node
     * @param n Word that represents the node
     */
    public GraphNode(String n){
        // Convert the word to uppercase
        name = n.toUpperCase();

        // Prepare the list
        successors = new LinkedList<GraphNode>();
    }



    /**
     * Getter to obtain the word of the node
     * @return the word of this node
     */
    public String getName() {
        return name;
    }



    /**
     * Obtain the successors words.
     * @return Returns the words that this node knows that can be following
     *         up to the word represented by the current node.
     */
    public LinkedList<GraphNode> getSuccessors() {
        return successors;
    }


    /**
     * Adds a new node as a successor of the current node. This happens only
     * if the node is not already contained on the list.
     * @param aNode The node to be added.
     */
    public void addSuccessor(GraphNode aNode) {
        // If the node is not on the list
        if(!successors.contains(aNode))
            // Then add it
            successors.add(aNode);
    }



    /**
     * Adds a list of successors nodes. Each of them is evaluated to test if they
     * were already listed on the node as successor words.
     * @param nodes List of nodes to be evaluated and added
     */
    public void addSuccessors(LinkedList<GraphNode> nodes) {
        // For each node
        for(GraphNode n: nodes)
            // If it is not already contained
            if(!successors.contains(n))
                // Then add it to the list
                successors.add(n);
    }


    /**
     * Override the equals method of the Object class, in order to give this
     * element a custom comparison method.
     * @param obj The other node to compare this to.
     * @return true if they are equals (same name and successors), or false otherwise.
     */
    @Override
    public boolean equals (Object obj) {
        // Prepare an object
        GraphNode object;

        // If it is no a node, then they are not equals
        if( !(obj instanceof GraphNode) )
            return false;
        // Otherwise
        else {
            // Cast it and save it
            object = ((GraphNode) obj);

            // If the name is not the same
            if( !(this.getName().equals(object.getName())) )
                return false;

            // If the successors are not the same
            if( !(this.getSuccessors().equals(object.getSuccessors())) )
                return false;
        }

        // If everything went through, then it is true;
        return true;
    }

}
