/* Assignment: Project 2 - Breadth First Search, Depth First Search, Shortest Path
** Name: Chris Del Fattore
** Email: crdelf01@cardmail.louisville.edu
** Description: This class is used to represent a weighted edge in the Weighted Graph
** This class is a sub class to the Edge class.
*/
public class WeightedEdge extends Edge implements Comparable<WeightedEdge> {
	public double weight; //Distance between two cities

	//Constructor that takes to integers representing the nodes in the edge.
	//also takes the weight of the edge for the two nodes/
	public WeightedEdge(int u, int v, double weight){
		super(u,v); //the super class is the edge class.
		this.weight = weight;
	}

	//Method used to compare two weighted edges to 
	public int compareTo(WeightedEdge edge){
		if(weight > edge.weight){
			return 1;
		}
		else if(weight == edge.weight){
			return 0;
		}
		else {
			return -1;
		}
	}
}