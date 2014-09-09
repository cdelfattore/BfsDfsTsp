import java.io.*;
import java.util.*;
import java.util.regex.*;

public class BfsDfsTsp {
	public static HashMap<String,Point> pointsMap; //A hashmap used to retrieve point information easier
	//Below is list of edges in the graph. This will not change.
	public static List<String> graphEdgesList = Arrays.asList("1-2,3,4","2-3","3-4,5","4-5,6,7","5-7,8","6-8","7-9,10","8-9,10,11","9-11","10-11");
	//public static Map<String,String> graphEdgesMap
	public static void main(String args[]) throws IOException{
	
		//Takes the filename as a parameter. File contains points and the x and y cooridnates.
		String filename = args[0];

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
				points.add( new Point(value.split(" ")[0], Double.parseDouble(value.split(" ")[1]), Double.parseDouble(value.split(" ")[2])) );
			}
		}

		pointsMap = new HashMap<String,Point>();
		for (Point p : points) {
			pointsMap.put(p.getName(), p);
		}

		/*for(Point p : points) {
			System.out.println(p.getName() + " " + p.getX() + " " + p.getY());
		}*/

		/*for(String s : graphEdges){
			System.out.println(s);
		}*/

		//Create a map of the Points with a List of their edges.
		//Edges contains info about
		Map<Integer,List<Edge>> nodeAndEdges = new HashMap<Integer,List<Edge>>();
		for(String s : graphEdgesList){
			String[] parChd = s.split("-");
			//System.out.println(parChd[0] + " " + parChd[1]);
			List<Edge> tmp = new ArrayList<Edge>();
			for(String a : parChd[1].split(",")){
				Edge e = new Edge(pointsMap.get(parChd[0]), pointsMap.get(a));
				//System.out.println(e.getName() + " " + e.getDistance());
				tmp.add(e);
				//System.out.println(parChd[0] + " " + a);
			}
			nodeAndEdges.put(Integer.parseInt(parChd[0]),tmp);
		}

		/*for(int i = 1; i < 12; i++){
			//System.out.println(i + " " + nodeAndEdges.get(i));
			for(Edge e : nodeAndEdges.get(i)){
				//System.out.println(i + " Edge: " + e.getName() + " Distance: " + e.getDistance());	
			}
		}*/

		LinkedList<Integer> que = new LinkedList<Integer>();
		LinkedList<Integer> finOrderQueue = new LinkedList<Integer>();
		que.addLast(1);
		//need to keep track of nodes already visited.
		while(!que.isEmpty()){
			List<Edge> le = nodeAndEdges.get(que.element());
			for(Edge e : le){
				System.out.println(e.nodeTo());
				que.add(Integer.parseInt(e.nodeTo()));
			}
			System.out.println(que.poll());
		}

		
	}
	//Method to compute distance
	//Takes to points as parameters and computes the distance between them.
	//Uses distance formula
	public static double computeDistance(Point a, Point b){
		return Math.sqrt( ((a.getX() - b.getX()) * (a.getX() - b.getX())) + ((a.getY() - b.getY()) * (a.getY() - b.getY()) ) );
	}
}

//Object used to represent a single point
//Point Stores the Name, X and Y Value
//with methods to retrieve the name, x and y value
//and a method to set the name.

//need to add a list of edges somewhere
class Point {
	String name;
	double x, y;
	//constructor
	Point(String name, double x, double y) {
		this.name = name;
		this.x = x;
		this.y = y;
	}
	//method to retrieve a points name
	String getName(){
		return name;
	}
	//get X value
	double getX(){
		return x;
	}
	//get y value
	double getY(){
		return y;
	}
	//needed when converting a number to a letter and vise versa
	void setName(String a) {
		this.name = a;
	}

}

class Edge {
	String name;
	Point pointFrom, pointTo;
	Double distance;

	Edge(Point from, Point to){
		this.pointFrom = from;
		this.pointTo = to;
		this.distance = BfsDfsTsp.computeDistance(from,to);
		this.name = from.getName() + "-" + to.getName();
	}

	String getName(){
		return name;
	}

	String nodeTo(){
		return pointTo.getName();
	}

	Double getDistance(){
		return distance;
	}
}