package laberinto.generator.beans;

import laberinto.generator.beans.algorithms.RecursiveBackTracking;

public class RecursiveBacktrackMaze extends Maze {

    public RecursiveBacktrackMaze() {
        this.setAlgorithm(new RecursiveBackTracking());
    }
}
