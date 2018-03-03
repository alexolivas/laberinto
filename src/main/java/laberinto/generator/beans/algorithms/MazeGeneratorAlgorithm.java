package laberinto.generator.beans.algorithms;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// This is the strategy portion of the strategy design pattern
public abstract class MazeGeneratorAlgorithm {

    private String name;

    protected int PATH = 0;
    protected int WALL = 1;
    protected int ENTRANCE = 2;
    protected int EXIT = 3;

//    protected Cell[][] maze;

    void setName(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    public abstract int[][] run(int dimension);

    /*
    private List<Cell> getUnvisitedNeighbors(Cell curr, Cell[][] maze) {
        int x = curr.getX();
        int y = curr.getY();
        System.out.println("CURRENT ===>  X: " + x + " Y: " + y);

        // Neighbor x values => (x - 1) and (x + 1)
        // Neighbor y values => (y - 1) and (y + 1)
        // Neighbors         => combination of both that lie in the path

        // A cell can have, at most, up to 8 neighbors
        int neighborX1 = x - 1;
        int neighborX2 = x + 1;
        int neighborY1 = y - 1;
        int neighborY2 = y + 1;

        // Determine which of the calculated indices for neighbor cells fall within
        // the bounds of the maze, disregard any combinations that contain any of the
        // indices that may fall outside that range
        boolean x1WithinBounds = (neighborX1 >= 0 && neighborX1 < maze.length);
        boolean x2WithinBounds = (neighborX2 >= 0 && neighborX2 < maze.length);
        boolean y1WithinBounds = (neighborY1 >= 0 && neighborY1 < maze.length);
        boolean y2WithinBounds = (neighborY2 >= 0 && neighborY2 < maze.length);

        System.out.println("x : " + x);
        System.out.println("y : " + y);
        System.out.println("x1 : " + neighborX1);
        System.out.println("x2 : " + neighborX2);
        System.out.println("y1 : " + neighborY1);
        System.out.println("y2 : " + neighborY2);
        System.out.println("x1WithinBounds : " + x1WithinBounds);
        System.out.println("x2WithinBounds : " + x2WithinBounds);
        System.out.println("y1WithinBounds : " + y1WithinBounds);
        System.out.println("y2WithinBounds : " + y2WithinBounds);

        List<Cell> neighbors = new ArrayList<>();
        // Get the diagonal neighbors to left
        // 1. (neighborX1, neighborY1)
        // 2. (neighborX2, neighborY1)
        System.out.println("Diagonal left neighbors:");
        System.out.println("( " + neighborX1 + ", " + neighborY1 + " ) ");
        System.out.println("( " + neighborX2 + ", " + neighborY1 + " ) ");
        neighbors.add(maze[neighborX1][neighborY1]);
        neighbors.add(maze[neighborX2][neighborY1]);

        // Get the diagonal neighbors to right
        // 1. (neighborX1, neighborY2)
        // 2. (neighborX2, neighborY2)
        System.out.println("Diagonal right neighbors:");
        System.out.println("( " + neighborX1 + ", " + neighborY2 + " ) ");
        System.out.println("( " + neighborX2 + ", " + neighborY2 + " ) ");
        neighbors.add(maze[neighborX1][neighborY2]);
        neighbors.add(maze[neighborX2][neighborY2]);

        // Get the left and right neighbors
        // 1. (x, neighborY1)
        // 2. (x, neighborY2)
        System.out.println("Left and Right neighbors:");
        System.out.println("( " + x + ", " + neighborY1 + " ) ");
        System.out.println("( " + x + ", " + neighborY2 + " ) ");
        neighbors.add(maze[x][neighborY1]);
        neighbors.add(maze[x][neighborY2]);

        // Get the up and down neighbors
        // 1. (neighborX1, y)
        // 4. (neighborX2, y)
        System.out.println("Diagonal left neighbors:");
        System.out.println("( " + neighborX1 + ", " + y + " ) ");
        System.out.println("( " + neighborX2 + ", " + y + " ) ");
        neighbors.add(maze[neighborX1][y]);
        neighbors.add(maze[neighborX2][y]);

        List<Cell> unvisitedNeighbors = Collections.emptyList();
        // Go through every neighboring cell and determine if they are unvisited and not a wall
        for ( Cell neighbor : neighbors ) {
            boolean unvisited = neighbor.getStatus().equals(Cell.Status.UNVISITED) &&
                    !neighbor.getType().equals(Cell.Type.WALL);
            if ( unvisited ) {
                if ( unvisitedNeighbors.isEmpty() ) {
                    unvisitedNeighbors = new ArrayList<>();
                }
                System.out.println("Found unvisited neighbor: ( " + neighbor.getX() + ", " + neighbor.getY() + " )");
                unvisitedNeighbors.add(neighbor);
            }
        }
        return unvisitedNeighbors;
    }
    */
}
