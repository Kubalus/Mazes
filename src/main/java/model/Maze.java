package model;

import controler.MazeGenerationStrategy;

import java.util.HashMap;
import java.util.Map;

public class Maze {

    int width;
    int height;
    Field[][] mazeMap;
    MazeGenerationStrategy mazeGenerationStrategy;

    private Maze(MazeBuilder builder) {
        this.height = builder.height;
        this.width = builder.width;
        mazeGenerationStrategy = builder.mazeGenerationStrategy;
        mazeMap = mazeGenerationStrategy.generateMaze(this.width, this.height);
    }

    public static class MazeBuilder {
        private int width, height;
        private Field[][] mazeMap;
        private MazeGenerationStrategy mazeGenerationStrategy;

        public void withSize(int width, int height) {
            this.height = height;
            this.width = width;
        }

        public void withMazeGenerationStrategy(MazeGenerationStrategy strategy) {
            this.mazeGenerationStrategy = strategy;
        }

        public Maze build() {
            return new Maze(this);
        }
    }

    public Map<Direction, Field> findNeighbours(int positionX, int positionY) {
        Map<Direction, Field> neighbours = new HashMap<>();
        for (Direction direction : Direction.values()) {
            neighbours.put(direction, findNeighbour(direction, positionX, positionY));
        }
        return neighbours;
    }

    public void displayMaze() {
        for (int row = 0; row < this.height; row++) {
            for (int column = 0; column < this.width; column++) {
                getFieldFromMap(column, row).print();
            }
            printNewLine();
        }
    }

    private void printNewLine() {
        System.out.print(System.lineSeparator());
    }


    private Field findNeighbour(Direction direction, int positionX, int positionY) {
        int coordinateX = positionX + direction.getModifierX();
        int coordinateY = positionY + direction.getModifierY();
        if (isNeighbourExisting(coordinateX, coordinateY))
            return getFieldFromMap(coordinateX, coordinateY);
        else
            return null;
    }

    private boolean isNeighbourExisting(int coordinateX, int coordinateY) {
        return coordinateX < 0 ||
                coordinateX >= this.width ||
                coordinateY < 0 ||
                coordinateY >= this.height;
    }

    private Field getFieldFromMap(int positionX, int positionY) {
        return this.mazeMap[positionX][positionY];
    }
}
