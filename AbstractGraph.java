import java.util.*;

public abstract class AbstractGraph implements Graph {
	protected Object[] nodes; //store nodes
	protected List<Integer>[] neighbors; //Adjaceny list

	//Contructor to make abstract graph from a List of edges and a list of nodes
	protected AbstractGraph(List<Edge> ledges, List lnodes){
		this.nodes = lnodes.toArray();
		createAdjacencyLists(ledges, lnodes.size() + 1);
	}

	//method to create adjaceny list for each node
	private void createAdjacencyLists(List<Edge> edges, int numOfNodes){
		//Create a linked list
		neighbors = new LinkedList[numOfNodes];
		for(int i = 0; i < numOfNodes;i++){
			neighbors[i] = new java.util.LinkedList<Integer>();
		}
		//create the list of neighbors
		for(Edge e : edges){
			neighbors[e.pFrom].add(e.pTo);
		}
	}
	
	//get the amount of nodes in the graph
	public int getSize() {
		return nodes.length;
	}
	
	//get the nodes in the graph
	public Object[] getNodes(){
		return nodes;
	}
	
	//Return the object for the specified node
	public Object getNode(int v){
		return nodes[v];
	}
	
	//Return the index for the specified vertex object
	public int getIndex(Object node){
		for(int i = 0; i < getSize(); i++){
			if(node.equals(nodes[i])){
				return i;
			}
		}
		return -1; //not in the graph
	}

	//return the nodes of a vertex
	public java.util.List getNeighbors(int v){
		return neighbors[v];
	}

	public void printEdges() {
		for(int i = 0; i < neighbors.length; i++){
			System.out.print("Node " + i + ": ");
			for(int j = 0; j < neighbors[i].size(); j++){
				System.out.print(neighbors[i].get(j) + ", ");
			}
			System.out.println();
		}
	}

	//Obtain a depth first search tree starting from node v
	public Tree dfs(int v){
		List<Integer> searchOrders = new ArrayList<Integer>();
		int[] parent = new int[nodes.length];
		for(int i = 0; i < parent.length; i++){
			parent[i] = -1;
		}
		
		//Mark visited nodes
		boolean[] isVisited = new boolean[nodes.length];

		//Start the recursive search
		dfs(v, parent, searchOrders, isVisited);

		return new Tree(v, parent, searchOrders);
	}

	//Method that is recursivly called for depth first search
	private void dfs(int v, int[] parent, List<Integer> searchOrders, boolean[] isVisited){
		//store the visited node v 
		searchOrders.add(v);
		isVisited[v] = true;

		for(int i : neighbors[v]){
			if(!isVisited[i]){
				parent[i] = v;
				dfs(i, parent, searchOrders, isVisited);//Recursivly search
			}
		}
	}

	public Tree bfs(int v){
		List<Integer> searchOrders = new ArrayList<Integer>();
		int[] parent = new int[nodes.length];
		for(int i = 0; i < parent.length; i++){
			parent[i] = -1;
		}
		//queue represented by a linked list
		java.util.LinkedList<Integer> queue =  new java.util.LinkedList<Integer>();
		
		//Mark visited nodes
		boolean[] isVisited = new boolean[nodes.length];

		//added the root node to the queue
		queue.offer(v);
		//mark it as visited
		isVisited[v] = true;

		while(!queue.isEmpty()){
			int u = queue.poll(); //dequeue the node to u
			searchOrders.add(u);
			for(int w : neighbors[u]){
				if(!isVisited[w]){
					queue.offer(w); //enqueue w
					parent[w] = u;//w's parent is u
					isVisited[w] = true;
				}
			}
		}
		return new Tree(v, parent, searchOrders);
	}

	public class Tree {
	private int root; // the number of root node in the tree
	private int[] parent; // stores the parent of each node
	private List<Integer> searchOrders; // store the search order

	public Tree(int root, int[] parent, List<Integer> searchOrders){
		this.root = root;
		this.parent = parent;
		this.searchOrders = searchOrders;
	}

	//get the root of the tree
	public int getRoot(){
		return root;
	}

	//get the parent of any node
	public int getParent(int v){
		return parent[v];
	}

	//Return the order that the nodes were searched in
	public List<Integer> getSearchOrders(){
		return searchOrders;
	}

	//return the number of verticies found
	public int getNumOfVerticesFound(){
		return searchOrders.size();
	}

	public java.util.Iterator pathIterator(int v){
		return new PathIterator(v);
	}

	//PathIterator class is need for printing the path
	public class PathIterator implements java.util.Iterator {
		private Stack<Integer> stack;
		public PathIterator(int v){
			stack = new Stack<Integer>();
			do {
				stack.add(v);
				v = parent[v];
			}
			while (v != -1) ;
		}

		//has a next element in the iterator?
		public boolean hasNext(){
			return !stack.isEmpty();
		}

		//Gets the current element in the iterator and move
		//the iterator to point to the next element
		public Object next(){
			return nodes[stack.pop()];
		}

		//defined in iterator method, I don't need it so won't implement it
		public void remove(){ }
	}

	//Print path from the root node to vertex v
	public void printPath(int v){
		Iterator it = pathIterator(v);
		System.out.print("A path from " + nodes[root] + " to " + nodes[v] + ": ");
		while(it.hasNext()){
			System.out.print(it.next() + " ");
		}
	}
}

}