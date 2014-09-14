/* Assignment: Project 2 - Breadth First Search, Depth First Search, Shortest Path
** Name: Chris Del Fattore
** Email: crdelf01@cardmail.louisville.edu
** Description: Simple class to create a unweighted graph, extends the AbstractGraph class
*/
import java.util.*;

public class UnweightedGraph extends AbstractGraph {
	
	//Constructo that takes a List of edges and a list of nodes
	public UnweightedGraph(List<Edge> edges, List nodes){

		//call the construct in AbstractGraph to create the graph
		super(edges,nodes);
	}

}