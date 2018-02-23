package laberinto.generator.beans.algorithms;

// This is the strategy portion of the strategy design pattern
public abstract class MazeGeneratorAlgorithm {
    private String name;
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public abstract int[][] run();
}
