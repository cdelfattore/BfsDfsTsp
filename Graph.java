/* Assignment: Project 2 - Breadth First Search, Depth First Search, Shortest Path
** Name: Chris Del Fattore
** Email: crdelf01@cardmail.louisville.edu
** Description: This class is a interface to be implement in the AbstractGraph, UnweightedGraph and WeightedGraph class
*/
public interface Graph {
	//Return the number of vertices in the graph
	public int getSize();

	//Return the Nodes in the graph
	public Object getNodes();

	//Return the object for the specified vertex object
	public Object getNode(int index);

	//Return the index for the specified vertex object
	public int getIndex(Object v);

	//Return the neighbors of vertex v
	public java.util.List getNeighbors(int v);

	//Return the degree for a specified vertex
	//public int getDegree(int v);

	//Return the adjacency matrix
	//public int[][] getAdjacencyMatrix();

	//Print the edges
	public void printEdges();

	//Get the dfs tree
	public AbstractGraph.Tree dfs(int v);

	//get the bfs tree
	public AbstractGraph.Tree bfs(int v);

}