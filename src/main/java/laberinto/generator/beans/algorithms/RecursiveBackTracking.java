package laberinto.generator.beans.algorithms;

import java.util.*;

public class RecursiveBackTracking extends MazeGeneratorAlgorithm {

    protected Stack<Cell> pathStack;
    private Random random;
    private ArrayList<Cell> unvisitedCells;

    public RecursiveBackTracking() {
        random = new Random();
        unvisitedCells = new ArrayList<>();
        pathStack = new Stack();
    }

    // http://weblog.jamisbuck.org/2010/12/27/maze-generation-recursive-backtracking
    public int[][] run(int dimension) {
        Cell[][] maze = generateInitialState(dimension);

        // 1. Make the initial cell the current cell and mark it as visited
        Cell curr = unvisitedCells.remove(random.nextInt(unvisitedCells.size()));
        System.out.println("Unvisited cells: " + unvisitedCells.size());
//        // TODO: improve this
        curr.markVisited();
        maze[curr.getX()][curr.getY()] = curr;

        // 2. While there are unvisited cells:
        while ( !this.unvisitedCells.isEmpty() ) {
            System.out.println(" ---- --- ----- ----- ");
            System.out.println("Current cell: ( " + curr.getX() + ", " + curr.getY() + " ) ");
            System.out.println("Unvisited cells: " + unvisitedCells.size());

            // TODO: I don't have a base case
            if ( unvisitedCells.size() == 1 ) {
                curr = unvisitedCells.remove(0);
            }

            // 1. if the current cell has neighbors which have not been visited
            List<Cell> unvisitedNeighbors = getUnvisitedNeighbors(curr, maze);
            if ( !unvisitedNeighbors.isEmpty() ) {
                // 1. choose randomly one of the unvisited neighbors
                Cell unvisitedNeighbor = unvisitedNeighbors.get(random.nextInt(unvisitedNeighbors.size()));
                unvisitedCells.remove(unvisitedNeighbor);
                System.out.println("Randomly selected unvisited neighbor: ( " + unvisitedNeighbor.getX() +
                        ", " + unvisitedNeighbor.getY() + " )");
                // 2. push the current cell to the stack
                pathStack.push(curr);
                // 3. Remove the wall between the current cell and the chosen cell
                System.out.println("Remove the wall between current and chosen neighbor...");
                Cell tornWall = tearDownWallBetweenCells(curr, unvisitedNeighbor);
                maze[tornWall.getX()][tornWall.getY()] = tornWall;

                // 4. Make the chosen cell the current cell and mark it as visited
                curr = unvisitedNeighbor;
                curr.markVisited();
                maze[curr.getX()][curr.getY()] = curr;
            } else if ( !pathStack.empty()) {
                System.out.println("no unvisited neighbors popping cell from stack...");
                // 2. Else if stack is not empty
                    // 1. pop a cell from the stack
                    // 2. make it the current cell
                curr = pathStack.pop();
//                System.out.println("Popped cell: ( " + curr.getX() + ", " + curr.getY() + " ) ");

                // TODO: delete after I find the bug
//                this.unvisitedCells.clear();
            }
        }
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
//        System.out.println("CURRENT ===>  X: " + x + " Y: " + y);

        // Neighbor x values => (x - 1) and (x + 1)
        // Neighbor y values => (y - 1) and (y + 1)
        // Neighbors         => combination of both that lie in the path

        // A cell can have, at most, up to 8 neighbors
        int neighborX1 = x - 2;
        int neighborX2 = x + 2;
        int neighborY1 = y - 2;
        int neighborY2 = y + 2;

        // Determine which of the calculated indices for neighbor cells fall within
        // the bounds of the maze, disregard any combinations that contain any of the
        // indices that may fall outside that range
        boolean x1WithinBounds = (neighborX1 >= 0 && neighborX1 < maze.length);
        boolean x2WithinBounds = (neighborX2 >= 0 && neighborX2 < maze.length);
        boolean y1WithinBounds = (neighborY1 >= 0 && neighborY1 < maze.length);
        boolean y2WithinBounds = (neighborY2 >= 0 && neighborY2 < maze.length);

//        System.out.println("x : " + x);
//        System.out.println("y : " + y);
//        System.out.println("x1 : " + neighborX1);
//        System.out.println("x2 : " + neighborX2);
//        System.out.println("y1 : " + neighborY1);
//        System.out.println("y2 : " + neighborY2);
//        System.out.println("x1WithinBounds : " + x1WithinBounds);
//        System.out.println("x2WithinBounds : " + x2WithinBounds);
//        System.out.println("y1WithinBounds : " + y1WithinBounds);
//        System.out.println("y2WithinBounds : " + y2WithinBounds);

        List<Cell> neighbors = new ArrayList<>();
        // Get the diagonal neighbors to left
        // 1. (neighborX1, neighborY1)
        // 2. (neighborX2, neighborY1)
//        System.out.println("Diagonal left neighbors:");
//        System.out.println("( " + neighborX1 + ", " + neighborY1 + " ) ");
//        System.out.println("( " + neighborX2 + ", " + neighborY1 + " ) ");
//        neighbors.add(maze[neighborX1][neighborY1]);
//        neighbors.add(maze[neighborX2][neighborY1]);

        // Get the diagonal neighbors to right
        // 1. (neighborX1, neighborY2)
        // 2. (neighborX2, neighborY2)
//        System.out.println("Diagonal right neighbors:");
//        System.out.println("( " + neighborX1 + ", " + neighborY2 + " ) ");
//        System.out.println("( " + neighborX2 + ", " + neighborY2 + " ) ");
//        neighbors.add(maze[neighborX1][neighborY2]);
//        neighbors.add(maze[neighborX2][neighborY2]);

        // Get the left and right neighbors
        // 1. (x, neighborY1)
        // 2. (x, neighborY2)
//        System.out.println("Left and Right neighbors:");
//        System.out.println("( " + x + ", " + neighborY1 + " ) ");
//        System.out.println("( " + x + ", " + neighborY2 + " ) ");
        if ( y1WithinBounds ) {
            neighbors.add(maze[x][neighborY1]);
        }

        if ( y2WithinBounds ) {
            neighbors.add(maze[x][neighborY2]);
        }

        // Get the up and down neighbors
        // 1. (neighborX1, y)
        // 4. (neighborX2, y)
//        System.out.println("Up and Down neighbors:");
//        System.out.println("( " + neighborX1 + ", " + y + " ) ");
//        System.out.println("( " + neighborX2 + ", " + y + " ) ");
        if ( x1WithinBounds ) {
            neighbors.add(maze[neighborX1][y]);
        }

        if ( x2WithinBounds ) {
            neighbors.add(maze[neighborX2][y]);
        }

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

    private Cell tearDownWallBetweenCells(Cell curr, Cell neighbor) {
        int x = 0;
        int y = 0;

        // Use the sum of numbers formula to determine the number missing between the either
        // the x or y values
        if ( curr.getX() == neighbor.getX() ) {
            // if the x values are equal: tear down (x, [(y1 + y2) / 2])
            // e.g. (10,9) and (10,7) ==> tear down (10, 8)
            x = curr.getX();
            y = (curr.getY() + neighbor.getY()) / 2;
        } else if ( curr.getY() == neighbor.getY() ) {
            // if the y values are equal: tear down ([(x1 + x2) / 2], y)
            // e.g. (9,8) and (7,8)   ==> tear down (8,8)
            x = (curr.getX() + neighbor.getX()) / 2;
            y = curr.getY();
        } else {
            System.out.println("ERROR: handle this better!!!!!");
        }

        System.out.println("Tearing down wall at: ( " + x + ", " + y + " ) ");
        // Tear down the number in between the values
        Cell tornWall = new Cell(x, y);
        tornWall.markVisited();
        tornWall.setType(Cell.Type.PATH);
        return tornWall;
    }
}
