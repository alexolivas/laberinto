package laberinto.generator.beans;

import laberinto.generator.beans.algorithms.EllersAlgorithm;

public class EllersMaze extends Maze {

    public EllersMaze() {
        this.setAlgorithm(new EllersAlgorithm());
    }
}
