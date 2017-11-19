import java.awt.Point;
import java.util.*;

import edu.princeton.cs.algs4.*;

public class SpaceDisagreement {

	private ArrayList<Point> starList; // create our list of Points("stars").
	private int stars;

	public SpaceDisagreement() {
		starList = new ArrayList<Point>();
		StdOut.println("Enter the number of stars in the map");
		stars = StdIn.readInt();
		createGraph();
	}

	public double starDistance(int i, int j) {
		int X = ((int) starList.get(i).getX() - ((int) starList.get(j).getX()));// get
																				// difference
																				// between
																				// the
																				// two
																				// x
																				// coordinates
		int Y = ((int) starList.get(i).getY()) - ((int) starList.get(j).getY());// get
																				// difference
																				// between
																				// the
																				// two
																				// y
																				// coordinates
		double distance = Math.hypot(X, Y);// finds distance between the two
											// points
		return distance;
	}

	public void createGraph() {
		StdDraw.setCanvasSize();
		StdDraw.filledRectangle(0, 0, 1, 1);
		StdDraw.setScale(0, 100);
		StdDraw.setPenColor(StdDraw.WHITE);
		StdOut.println(stars);
		for (int i = 0; i < stars; i++) {
			int sX = (int) (Math.random() * 100); // creates random x coordinate
													// of a star
			int sY = (int) (Math.random() * 100); // creates random y coordinate
													// of a star
			starList.add(new Point(sX, sY));
		}
		Graph starGraph = new Graph(starList.size());
		for (int i = 0; i < starList.size(); i++) {
			for (int j = i + 1; j < starList.size(); j++) {
				double distance = starDistance(i, j);
				if ((distance / 100) < 0.15) { // there's an edge here
					starGraph.addEdge(i, j); // adds edges to all applicable
												// points in the graph in the
												// order we input the points
					StdDraw.line(starList.get(i).getX(),
							starList.get(i).getY(), starList.get(j).getX(),
							starList.get(j).getY());
				}
			}
		}
		Biconnected checkMe = new Biconnected(starGraph); // check for
															// articulation
															// points
		for (int i = 0; i < starList.size(); i++) {
			if (checkMe.isArticulation(i)) {
				StdDraw.setPenColor(StdDraw.RED);
			} else {
				StdDraw.setPenColor(StdDraw.WHITE);
			}
			StdDraw.filledCircle(starList.get(i).getX(),
					starList.get(i).getY(), .75);
		}
	}

	public static void main(String args[]) {
		SpaceDisagreement sd = new SpaceDisagreement();
		// draw the graph
	}

}
