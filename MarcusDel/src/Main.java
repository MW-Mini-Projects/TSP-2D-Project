import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int k = 2;
        //Read all the input from kattis
        Scanner sc = new Scanner(System.in);

        //Number of points
        int nop = sc.nextInt();
        Point[] points = new Point[nop];

        //If it would happen that we have less than 20 points in the graph
        /*if(nop > 20)
            k = 20;
        else
            k = nop;*/

        //Read in all the points
        for(int i = 0; i < nop; i++) {
            double x = sc.nextDouble();
            double y = sc.nextDouble();
            points[i] = new Point(i, x, y);
        }

        calcAllNeighbours(points, nop, k);

        //Just a printout method
        for(int i = 0; i < points.length; i++) {
            System.out.println();
            System.out.print("Point: " + (i+1) + " Neighbours: ");
            for(int j = 0; j < points[i].neighbours.size(); j++)
                System.out.print(points[i].neighbours.get(j).id+1 + ", ");
            System.out.println();
        }
    }

    public static void calcAllNeighbours(Point[] points, int nop, int k) {
        //We need to calculate the neighbours for all points
        for(int i = 0; i < nop; i++) {

            //Throwaway list when finished with i:th k closest neighbours
            List<Neighbour> Ineighbours = new ArrayList<>();

            //Each point has to look calculate the distance to all other points
            for(int j = 0; j < nop; j++) {
                //Distance from point i to point j
                int distance = euclidean(points[i], points[j]);
                //Add the new point to i's neighbours
                for(int v = 0; v < Ineighbours.size(); v++) {
                    if(v > k)
                        break;
                    if(Ineighbours.get(v).distance >= distance && i != j) {
                        Neighbour neigh = new Neighbour(points[j].id, distance);
                        Ineighbours.add(v, neigh);
                        break;
                    }
                    else if(v == Ineighbours.size()-1 && v < k && i != j) {
                        Neighbour neigh = new Neighbour(points[j].id, distance);
                        Ineighbours.add(v+1, neigh);
                        break;
                    }
                }
                //When we have no elements in the list
                if(Ineighbours.size() == 0 && i != j) {
                    Neighbour neigh = new Neighbour(points[j].id, distance);
                    Ineighbours.add(neigh);
                }
            }

            //Only add the k closest neighbours to points[i] neighbour list
            for(int v = 0; v < k; v++) {
                points[i].neighbours.add(Ineighbours.get(v));
            }
        }
    }

    public static int euclidean(Point a, Point b) {
        //square root of (x1 - x2)^2 + (y1 - y2)^2
        return (int) Math.round(Math.sqrt((Math.pow((a.x - b.x), 2) + Math.pow((a.y - b.y), 2))));
    }
}
