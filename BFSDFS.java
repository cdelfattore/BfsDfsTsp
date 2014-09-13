import java.util.*;
import java.io.*;
import java.util.regex.*;

public class BFSDFS {
	public static void main(String[] args) throws IOException {
		List<String> graphEdgesList = Arrays.asList("1-2,3,4","2-3","3-4,5","4-5,6,7","5-7,8","6-8","7-9,10","8-9,10,11","9-11","10-11");
		List<Edge> edges = new ArrayList<Edge>();
		List<WeightedEdge> wedges = new ArrayList<WeightedEdge>();
		List<Integer> nodes = new ArrayList<Integer>();
		
		//need to add 0 to keep arrays big enough when nodes.size() is used.
		nodes.add(0);

		//Because we are only using one file, we can simply declare it in the program
		String filename = "11PointDFSBFS.tsp";

		//The point class is defined at the bottom of this file.
		//The point class is a basic class to store information about a point.
		//The Below list is used to store the point information from the input file
		List<Point> points = new ArrayList<Point>();

		//BufferedReader used to read input from a file
		BufferedReader reader = new BufferedReader(new FileReader(filename));

		//pattern is the regular expression used to parse throught the input file and find the point number and the point's x and y value.
		//The pattern will find all of the points in the file
		String pattern = "(?m)^\\d+\\s\\d+\\.\\d+\\s\\d+\\.\\d+";
		Pattern r = Pattern.compile(pattern);

		String value = null;

		//the below while loop with go through the file line by line and see if a match has been made with the regular expression.
		//If a match is made, the line is parsed, retrieving the piont name, x and y coordinate values
		//the points are saved in the points list.
		while((value = reader.readLine()) != null){
			Matcher m = r.matcher(value);
			if(m.find()) {
				//add the point to the List of points
				points.add( new Point(Integer.parseInt(value.split(" ")[0]), Double.parseDouble(value.split(" ")[1]), Double.parseDouble(value.split(" ")[2])) );
			}
		}

		HashMap<Integer,Point> pointsMap = new HashMap<Integer,Point>();
		for (Point p : points) {
			pointsMap.put(p.name, p);
		}

		/*for (Integer p : pointsMap.keySet()) {
			System.out.println(pointsMap.get(p).name + " " + pointsMap.get(p).x + " " + pointsMap.get(p).y);
		}*/

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

		//11 is the last node in the graph.
		//We need to add it to the list of nodes.
		nodes.add(11);

		//wedges is the list of weighted edges
		for(Edge e : edges) {
			if(e.pTo != 0) {
				//System.out.println(pointsMap.get(e.pFrom) + " " + pointsMap.get(e.pTo));
				Double dis = computeDistance(pointsMap.get(e.pFrom), pointsMap.get(e.pTo));
				//System.out.println(dis);
				WeightedEdge we = new WeightedEdge(e.pFrom, e.pTo, dis);
				wedges.add(we);
			}
		}

		/*for(WeightedEdge we : wedges){
			System.out.println(we.pFrom + "-" + we.pTo + ": " + we.weight);
		}*/

		/*for(Integer i : nodes){
			System.out.println(i);
		}*/

		/*for(Edge e : edges){
			System.out.println(e.pFrom + "-" +  e.pTo);
		}*/

		UnweightedGraph ugraph = new UnweightedGraph(edges,nodes);
		AbstractGraph.Tree tbfs = ugraph.bfs(1);
		AbstractGraph.Tree tdfs = ugraph.dfs(1);
		System.out.println("BFS: " + tbfs.getSearchOrders());
		System.out.println("DFS: " + tdfs.getSearchOrders());

		WeightedGraph wgraph = new WeightedGraph(wedges, nodes);
		WeightedGraph.ShortestPathTree spt = wgraph.getShortestPath(1);
		//need to fix
		spt.printAllPaths();
		//System.out.println("The shortest path from 1 to 11 is: " + spt.getCost(11));

	}

	//Method to compute distance
	//Takes to points as parameters and computes the distance between them.
	//Uses distance formula
	public static double computeDistance(Point a, Point b){
		return Math.sqrt( ((a.x - b.x) * (a.x - b.x)) + ((a.y - b.y) * (a.y - b.y) ) );
	}	
}

class Point {
	int name;
	double x, y;
	Point(int node, double x, double y){
		this.name = node;
		this.x = x;
		this.y = y;
	}
	//point for 0 special case
	//represents a node who has no edges leaving it
	Point(int node){
		name = node;
	}
}
