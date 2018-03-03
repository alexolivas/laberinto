package laberinto.generator.beans.algorithms;


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
}
