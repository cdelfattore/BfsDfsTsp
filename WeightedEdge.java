public class WeightedEdge extends Edge implements Comparable<WeightedEdge> {
	public double weight; //Distance between two cities

	public WeightedEdge(int u, int v, double weight){
		super(u,v);
		this.weight = weight;
	}

	//Method used to help order the edges in increasing weight or distance
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