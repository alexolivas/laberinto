package laberinto.generator.beans.algorithms;

class Cell {

    private int x;
    private int y;
    private Type type;
    private Status status;

    Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.type = Type.WALL;
        this.status = Status.UNVISITED;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    void setType(Type type) {
        this.type = type;
    }

    int getValue() {
        return this.type.getValue();
    }

    void markVisited() {
        this.status = Status.VISITED;
    }

    enum Status {
        VISITED,
        UNVISITED
    }

    enum Type {
        PATH(0),
        WALL(1),
        ENTRANCE(2),
        EXIT(3);

        private int value;
        Type(int value) {
            this.value = value;
        }

        int getValue() {
            return value;
        }
    }
}
