import java.util.ArrayList;
import java.util.List;

//May implement k for each point, for now we keep it simple
public class Point {
    double x;
    double y;
    int degree;
    int id;
    boolean visited = false;
    List<Neighbour> neighbours = new ArrayList<>();

    public Point(int id, double x, double y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public void addNeighbour(int id, int distance) {
        Neighbour ne = new Neighbour(id, distance);
        neighbours.add(0, ne);
    }
}
