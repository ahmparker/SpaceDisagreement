import java.awt.Point;
import java.util.*;

import edu.princeton.cs.algs4.*; 
public class SpaceDisagreement  {

	ArrayList<Point> starList = new ArrayList<Point>(); //create our list of Points("stars").
	ArrayList<Boolean> isRed = new  ArrayList<Boolean>(); //create our list of nodes that are articulation points

	public void createStars(){
		Scanner in = new Scanner(System.in);
		System.out.println("Enter Number of Stars: ");
		int starCount = in.nextInt();
		
		System.out.println("Enter X Y coordinates of your stars: ");
		int i = 0;
		while (i < starCount){
			int sX = (in.nextInt() * 100); //creates x coordinate of a star
			int sY = (in.nextInt() * 100); //creates y coordinate of a star
			Point star = new Point(sX,sY); //create the point
			starList.add(star); //add to our list. the first inputed star is at index <1>
			System.out.println("Star Inputed."); //use starList.get(index) to get a Point. use Point.getX() or getY() to get x/y coordinates for that point
			i++;
		}
	}
	
	public void createGraph() {
		Graph starGraph = new Graph(starList.size());
		for (int i = 0; i < starList.size(); i++) {
			  for (int j = i+1; j < starList.size(); j++) {
			    int X = ((int) starList.get(i).getX() - ((int) starList.get(j).getX()));// get difference between the two x coordinates
			    int Y = ((int) starList.get(i).getY()) - ((int) starList.get(j).getY());// get difference between the two y coordinates
			    double distance = Math.hypot(X, Y); //finds distance between the two points
			    if ((distance / 100) < 0.15) { //there's an edge here
			    	starGraph.addEdge(i,j); //adds edges to all applicable points in the graph in the order we input the points
			    }
			  }
			}
		Biconnected checkMe = new Biconnected(starGraph); //check for articulation points
		for (int i = 0; i < starList.size(); i++){
			isRed.add(checkMe.isArticulation(i)); //index will go from 0 -> n in size. If index is true, node is red. 
		}
	}
	
	public void main() {
		createStars();
		createGraph();
		//draw the graph
	}

}
