package laberinto.generator.beans;

import laberinto.generator.beans.algorithms.CityStreetsAlgorithm;

public class CityMap extends Maze {

    public CityMap() {
        this.setAlgorithm(new CityStreetsAlgorithm());
    }
}
