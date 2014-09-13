//Chris Del Fattore - crdelf01@cardmail.louisville.edu
//Class to represent a Edge of the graph
public class Edge {
	//pFrom points to pTo
	//ex A->B
	//pFrom = A, pTo = B
	int pFrom;
	int pTo;

	Edge(int u, int v){
		this.pFrom = u;
		this.pTo = v;
	}
}