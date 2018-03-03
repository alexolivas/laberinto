package laberinto.generator.beans.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

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

        getUnvisitedNeighbors(curr, maze);

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

        // TODO: Conver the unvisited cells array into a hash table?
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
                    //value = PATH;
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
        // Neighbor y values => (y - 1) and (y + 2)
        // Neighbors         => combination of both that lie in the path

        // A cell can, at most, up to 8 neighbors
        int neighborX1 = x - 1;
        int neighborX2 = x + 2;
        int neighborY1 = y - 1;
        int neighborY2 = y + 2;

        for ( Cell cell : unvisitedCells ) {
            int neighborX = curr.getX();
            int neighborY = curr.getY();
            // if f
            // if row == column >> column must be +/- 1 difference
            System.out.println("X: " + x + " Y: " + y);
        }
        return null;
    }
}
