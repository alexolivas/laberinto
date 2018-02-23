package laberinto.generator.beans;

import laberinto.generator.beans.algorithms.RecursiveBackTracking;

public class RecurisveBacktrackMaze extends Maze {

    public RecurisveBacktrackMaze() {
        this.setAlgorithm(new RecursiveBackTracking());
    }
}
