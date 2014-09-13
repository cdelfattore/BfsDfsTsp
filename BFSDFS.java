import java.util.*;
public class BFSDFS {
	public static void main(String[] args){
		List<String> graphEdgesList = Arrays.asList("1-2,3,4","2-3","3-4,5","4-5,6,7","5-7,8","6-8","7-9,10","8-9,10,11","9-11","10-11","11-0");
		List<Edge> edges = new ArrayList<Edge>();
		List<Integer> nodes = new ArrayList<Integer>();
		nodes.add(0);

		//get the edges and nodes
		for(String s : graphEdgesList){
			String[] parChd = s.split("-");
			//System.out.println(s);
			//System.out.println(parChd[0] + " " + parChd[1]);
			nodes.add(Integer.parseInt(parChd[0]));
			for(String a : parChd[1].split(",")){
				Edge e = new Edge(Integer.parseInt(parChd[0]),Integer.parseInt(a));
				//System.out.println(e.getName() + " " + e.getDistance());
				edges.add(e);
				//System.out.println(parChd[0] + " " + a);
			}
		}

		/*for(Integer i : nodes){
			System.out.println(i);
		}

		for(Edge e : edges){
			System.out.println(e.pFrom + "-" +  e.pTo);
		}*/

		UnweightedGraph ugraph = new UnweightedGraph(edges,nodes);
		AbstractGraph.Tree tbfs = ugraph.bfs(1);
		AbstractGraph.Tree tdfs = ugraph.dfs(1);
		System.out.println(tbfs.getSearchOrders());
		System.out.println(tdfs.getSearchOrders());

	}
	
}