package laberinto.generator.beans.algorithms;

import java.util.*;

public class RecursiveBackTracking extends MazeGeneratorAlgorithm {

    protected Stack pathStack;
    private Random random;
    private ArrayList<Cell> unvisitedCells;

    public RecursiveBackTracking() {
        random = new Random();
        unvisitedCells = new ArrayList<>();
    }

    // http://weblog.jamisbuck.org/2010/12/27/maze-generation-recursive-backtracking
    public int[][] run(int dimension) {
        Cell[][] maze = generateInitialState(dimension);

        // 1. Make the initial cell the current cell and mark it as visited
        Cell curr = unvisitedCells.remove(random.nextInt(unvisitedCells.size()));

        List<Cell> unvisitedNeighbors = getUnvisitedNeighbors(curr, maze);
        curr = unvisitedNeighbors.get(random.nextInt(unvisitedNeighbors.size()));

        // 2. While there are unvisited cells:
        //while ( !this.unvisitedCells.isEmpty() ) {
            // 1. if the current cell has neighbors which have not been visited
                // 1. choose randomly one of the unvisited neighbors
                // 2. push the current cell to the stack
                // 3. Remove the wall between the current cell and the chosen cell
                // 4. Make the chosen cell the current cell and mark it as visited
            // 2. Else if stack is not empty
                // 1. pop a cell from the stack
                // 2. make it the current cell
        //}
        return new int[1][1];
    }

    /**
     * The initial state consists of alternating paths and walls e.g. a chess board.
     * @param dimension {@link int}
     */
    private Cell[][] generateInitialState(int dimension) {
        // TODO: This should be only for the recursive backtracking algorithm, if I find this should be
        // TODO: generic then I'll move it back to the parent class: MazeGeneratorAlgorithm
        // http://ozuduru.com/2016/02/10/recursive-backtracking-maze-generator-in-java-with-gui/
        // A number 2 represents the exit

        // TODO: Convert the unvisited cells array into a hash table?
        unvisitedCells = new ArrayList<>();

        Cell[][] maze = new Cell[dimension + 2][dimension + 2];
        for ( int i = 0; i < dimension + 2; i++ ) {
            for ( int j = 0; j < dimension + 2; j++ ) {
                Cell currCell = new Cell(i, j);
                currCell.setType(Cell.Type.WALL);
                // int value = WALL;

                boolean isBorder = (i == 0 || i == dimension + 1 || j == 0 || j == dimension + 1);
                boolean isAlternatingIndex = (i % 2 == 0 && j % 2 != 0 || i % 2 != 0 && j % 2 == 0);
                if ( !isBorder && isAlternatingIndex ) {
                    // Keep track of the unvisited cells in a separate array list
                    currCell.setType(Cell.Type.PATH);
                    unvisitedCells.add(currCell);
                }
                maze[i][j] = currCell;
            }
        }
        return maze;
    }

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

        // The neighbor's x1 values all fall within the range
        // The neighbor's x2 values all fall within the range
        // The neighbor's y1 values all fall within the range
        // The neighbor's y2 values all fall within the range
        List<Cell> unvisitedNeighbors = Collections.emptyList();
        for ( Cell neighbor : neighbors ) {
            boolean unvisited = neighbor.getStatus().equals(Cell.Status.UNVISITED) &&
                    !neighbor.getType().equals(Cell.Type.WALL);
            if ( unvisited ) {
                neighbor.markVisited();
                if ( unvisitedNeighbors.isEmpty() ) {
                    unvisitedNeighbors = new ArrayList<>();
                }
                System.out.println("Found unvisited neighbor: ( " + neighbor.getX() + ", " + neighbor.getY() + " )");
                unvisitedNeighbors.add(neighbor);
            }
        }
        return unvisitedNeighbors;
    }
}
