package laberinto.generator.beans;

import laberinto.generator.beans.algorithms.MazeGeneratorAlgorithm;

public abstract class Maze {

    private int[][] maze;
    private MazeGeneratorAlgorithm algorithm;

    public void setAlgorithm(MazeGeneratorAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    public int[][] getMaze() {
        return this.maze;
    }

    public void generate() {
        this.maze = this.algorithm.run();
    }
}
