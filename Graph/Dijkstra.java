import java.io.*;
import java.util.*;
import java.util.regex.*;

public class Dijkstra {
	
	public static void main(String[] args) throws IOException {
		//Because we are only using one file, we can simply declare it in the program
		String filename = "11PointDFSBFS.tsp";

		//Below is list of edges in the graph. This will not change.
		List<String> graphEdgesList = Arrays.asList("1-2,3,4","2-3","3-4,5","4-5,6,7","5-7,8","6-8","7-9,10","8-9,10,11","9-11","10-11");

		//The point class is defined at the bottom of this file.
		//The point class is a basic class to store information about a point.
		//The Below list is used to store the point information from the input file
		List<Point> points = new ArrayList<Point>();
		List<Edgea> edges = new ArrayList<Edgea>();

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

		Map<String,Point> pointsMap = new HashMap<String,Point>();
		for (Point p : points) {
			pointsMap.put(p.getName(), p);
		}

		for(String s : graphEdgesList){
			String[] parChd = s.split("-");
			//System.out.println(s);
			//System.out.println(parChd[0] + " " + parChd[1]);
			List<Edgea> tmp = new ArrayList<Edgea>();
			for(String a : parChd[1].split(",")){
				Edgea e = new Edgea(pointsMap.get(parChd[0]), pointsMap.get(a));
				//System.out.println(e.getName() + " " + e.getDistance());
				edges.add(e);
				//System.out.println(parChd[0] + " " + a);
			}
		}

		/*for(Point p : points){
			System.out.println(p.getName());
		}*/

		/*for(Edgea e : edges){
			System.out.println(e.getName() + " " + e.getDistance());
		}*/

		//Now we have a list of edges and points
		AbstractGraph agraph = new AbstractGraph(edges,points);

	}

		//Method to compute distance
	//Takes to points as parameters and computes the distance between them.
	//Uses distance formula
	public static double computeDistance(Point a, Point b){
		return Math.sqrt( ((a.getX() - b.getX()) * (a.getX() - b.getX())) + ((a.getY() - b.getY()) * (a.getY() - b.getY()) ) );
	}
}

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

class Edgea {
	String name;
	Point pointFrom, pointTo;
	Double distance;

	Edgea(Point from, Point to){
		this.pointFrom = from;
		this.pointTo = to;
		this.distance = Dijkstra.computeDistance(from,to);
		this.name = from.getName() + "-" + to.getName();
	}

	Edgea(){
		distance = null;
	}

	String getName(){
		return name;
	}

	String nodeTo(){
		return pointTo.getName();
	}

	String nodeFrom(){
		return pointFrom.getName();
	}

	Double getDistance(){
		return distance;
	}
}

