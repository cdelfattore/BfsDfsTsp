import java.util.*;

public class WeightedGraph extends AbstractGraph {
	private PriorityQueue<WeightedEdge>[] queues;

	//contructor for a weighted graph
	public WeightedGraph(List<WeightedEdge> edges, List nodes){
		super((List)edges, nodes);
		createQueues(edges, nodes.size());
	}

	public void createQueues(List<WeightedEdge> edges, int numOfNodes){
		queues = new PriorityQueue[numOfNodes];
		for (int i = 0;i < queues.length;i++ ) {
			queues[i] = new java.util.PriorityQueue();
		}

		for (WeightedEdge edge: edges) {
			queues[edge.pFrom].offer(edge);
		}
	}

	public void printWeightedEdges() {
		for (int i=0;i<queues.length;i++ ) {
			System.out.print("Vertex " + i + ": ");
			for (WeightedEdge edge : queues[i]) {
				System.out.print("(" + edge.pFrom + ", " + edge.pTo + ", " + edge.weight + ") ");
			}
			System.out.println();
		}
	}

	private PriorityQueue<WeightedEdge>[] deepClone(PriorityQueue<WeightedEdge>[] queues){
		PriorityQueue<WeightedEdge>[] copiedQueues = new PriorityQueue[queues.length];

		for (int i = 0;i < queues.length; i++ ) {
			copiedQueues[i] = new PriorityQueue<WeightedEdge>();
			for(WeightedEdge e: queues[i]){
				copiedQueues[i].add(e);
			}
		}

		return copiedQueues;
	}

	public ShortestPathTree getShortestPath(int sourceVertex){
		//T stores the vertices whose path found so far
		Set<Integer> T = new HashSet<Integer>();
		//T initially contains the sourcevertex
		T.add(sourceVertex);
		//number of nodes
		int numOfNodes = nodes.length;
		//System.out.println(numOfNodes);
		//parent stores the previous vertex of v in the path
		int[] parent = new int[numOfNodes];

		parent[sourceVertex] = -1;

		//cost[v] stores the cost of v to the source
		double[] costs = new double[numOfNodes];
		for(int i = 0;i < costs.length;i++){
			costs[i] = Double.MAX_VALUE;//
		}
		costs[sourceVertex] = 0.0; //cost of the source is 0

		//Get a copy of queues
		PriorityQueue<WeightedEdge>[] queues = deepClone(this.queues);

		Boolean stop = false;
		//Expand nodes found
		while(T.size() < numOfNodes){
			int v = -1;
			double smallestCost = Double.MAX_VALUE; //infinity to start
			for(int u : T){
				//System.out.println(u);
				if(u == 11){
					stop = true;
					break;
				}
				while(!queues[u].isEmpty() && T.contains(queues[u].peek().pTo)) {
					queues[u].remove();//remove nodes in nodes found
				}

				if(queues[u].isEmpty()){
					continue;
				}

				WeightedEdge e = queues[u].peek();
				if (costs[u] + e.weight < smallestCost){
					v = e.pTo;
					smallestCost = costs[u] + e.weight;
					//u will be the parent of v, if v is added to the tree
					parent[v] = u;
				}
			}//end for

			if(v != -1) {
				T.add(v);
				costs[v] = smallestCost;
			}
			//We reached the edge of the graph
			else {
				break;
			}
			
			
		}//end while

		//System.out.print(sourceVertex);

		/*for (Integer i : parent) {
			System.out.println("parent: " + i);
		}*/
		
		/*for(int i=0;i<costs.length;i++){
			System.out.println("Cost of " + i + ": " + costs[i]);
		}*/

		return new ShortestPathTree(sourceVertex, parent, costs);
	}

	public class ShortestPathTree extends Tree {
	private	double[] costs;

	public ShortestPathTree(int source, int[] parent, double[] costs){
		super(source, parent);
		this.costs = costs;
	}

	//Return the cost of the root node to a node v
	public double getCost(int v){
		return costs[v];
	}

	public void printAllPaths() {
		System.out.println("All Shortest paths from " + nodes[getRoot()] + " are:");
		for (int i = 1;i < costs.length; i++ ) {
			printPath(i);
			System.out.println("(costs: " + costs[i] + ")");
		}
	}
}
}

