package laberinto.generator.beans;

import laberinto.generator.beans.algorithms.MazeGeneratorAlgorithm;

import java.util.ArrayList;
import java.util.List;

public abstract class Maze {

    private int[][] maze;
    // TODO: Create an object for this and only log the cells that were updated
    private List<MazeState> state;
    private MazeGeneratorAlgorithm algorithm;

    public void setAlgorithm(MazeGeneratorAlgorithm algorithm) {
        this.state = new ArrayList<>();
        this.algorithm = algorithm;
    }

    public void setMaze(int[][] maze) {
        this.maze = maze;
    }

    public int[][] getMaze() {
        return this.maze;
    }

    public List<MazeState> getState() {
        return state;
    }

    public void setState(List<MazeState> state) {
        this.state = state;
    }

    public void generate(int dimension) {
        this.maze = this.algorithm.run(dimension);
    }
}
