/* Assignment: Project 2 - Breadth First Search, Depth First Search, Shortest Path
** Name: Chris Del Fattore
** Email: crdelf01@cardmail.louisville.edu
** Description: This class is used to represent an edge in the Unweighted Graph Class.
** Edge is the super class to the WeightedEdge class
*/
public class Edge {
	//pFrom points to pTo
	//ex A->B
	//pFrom = A, pTo = B
	int pFrom;
	int pTo;

	//Constructor that takes to integers representing the nodes in the edge.
	Edge(int u, int v){
		this.pFrom = u;
		this.pTo = v;
	}
}