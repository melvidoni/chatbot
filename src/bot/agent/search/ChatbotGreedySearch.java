package bot.agent.search;

import frsf.cidisi.faia.solver.search.InformedSearchStrategy;
import frsf.cidisi.faia.solver.search.NTree;

import java.util.Vector;



/**
 * Class that represents the cost function of a node in the tree.
 * @author Melina Vidoni, INGAR CONICET-UTN, 2016.
 */
public class ChatbotGreedySearch extends InformedSearchStrategy{


    /**
     * Default constructor of the class
     */
    public ChatbotGreedySearch() {
        super(new CostFunction());
    }


    /**
     * Method to add the cost of each node to a complete graph of
     * nodes existing within the agent.
     * @param nodes Nodes that need their cost calculated.
     */
    @Override
    public void addNodesToExpand(Vector<NTree> nodes) {
        // Cast the cost function
        CostFunction function = (CostFunction) this.getEstimatedCostFunction();

        // Assign the costs for each node on the tree
        for (NTree nt : nodes) {
            nt.setCost(function.getEstimatedCost(nt));
        }

        // Order the nodes
        this.orderNodes(nodes);
    }



    /**
     * Method that adds the cost to a solitary node of the graph.
     * @param node Node that requires its value to be added.
     */
    @Override
    public void addNodeToExpand(NTree node) {
        // Cast the cost function
        CostFunction function = (CostFunction) this.getEstimatedCostFunction();

        // Estimate the cost of this node
        node.setCost(function.getEstimatedCost(node));

        // Prepare a vector of one element only
        Vector<NTree> oneElementVector = new Vector<NTree>();
        oneElementVector.add(node);

        // Ordenamos el vectorUnico
        this.orderNodes(oneElementVector);
    }


    /**
     * Method that receives a list of unordered nodes, and orders them
     * regarding the cost they have. Biggest cost at the bottom.
     * @param unorderedNodes List of nodes to order.
     */
    private void orderNodes(Vector<NTree> unorderedNodes) {
        // Prepare the vector of nodes to order
        Vector<NTree> orderedNodes = new Vector<NTree>();

        // Go through the unordered nodes
        for(NTree uNode: unorderedNodes) {
            // If the list is empty, simply add it
            if( orderedNodes.isEmpty() ) {
                // Add it anywhere
                orderedNodes.addElement(uNode);
            }
            // Otherwise, we need to evaluate
            else{
                // If the cost is 2...
                if(uNode.getCost() == 2) {
                    // ...the node goes at the end
                    orderedNodes.addElement(uNode);
                }
                // If cost is 0...
                else if(uNode.getCost() == 0) {
                    // ... it goes first
                    orderedNodes.add(0, uNode);
                }
                // Otherwise, we need to study it
                else {
                    // Get the current first element
                    NTree currentFirstNode = orderedNodes.firstElement();

                    // If there is a node with cost zero
                    if(currentFirstNode.getCost() == 0) {
                        // Add this one on the second place
                        orderedNodes.add(1, uNode);
                    }
                    // If there are no zero cost nodes...
                    else {
                        // ...then it goes first
                        orderedNodes.add(0, uNode);
                    }
                }
            }
        }

        // Add nodes to expand
        nodesToExpand.addAll(orderedNodes);
    }




    /**
     * Getter to obtain the search name on a string.
     * @return The search converted to a string.
     */
    @Override
    public String getStrategyName() {
        return "Chatbot Greedy Search";
    }


}
