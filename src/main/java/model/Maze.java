package model;

import controler.MazeGenerationStrategy;

public class Maze {

    int width;
    int length;
    Field[][] mazeMap;
    MazeGenerationStrategy mazeGenerationStrategy;

    private Maze(MazeBuilder builder) {
        this.length = builder.length;
        this.width = builder.width;
        mazeGenerationStrategy = builder.mazeGenerationStrategy;
        mazeMap = mazeGenerationStrategy.generateMaze(this.width, this.length);
    }

    public static class MazeBuilder {
        private int width, length;
        private Field[][] mazeMap;
        private MazeGenerationStrategy mazeGenerationStrategy;

        public void withSize(int width, int length) {
            this.length = length;
            this.width = width;
        }

        public void withMazeGenerationStrategy(MazeGenerationStrategy strategy) {
            this.mazeGenerationStrategy = strategy;
        }

        public Maze build() {
            return new Maze(this);
        }
    }

    // TODO: Change to map with directions as keys
    public Field[] findNeighbours(int positionX, int positionY) {
        Field[] neighbours = new Field[4];
        neighbours[0] = findNeighbour(Direction.NORTH, positionX, positionY);
        neighbours[1] = findNeighbour(Direction.EAST, positionX, positionY);
        neighbours[2] = findNeighbour(Direction.SOUTH, positionX, positionY);
        neighbours[3] = findNeighbour(Direction.WEST, positionX, positionY);
        return neighbours;
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
                coordinateY >= this.length;
    }

    private Field getFieldFromMap(int positionX, int positionY) {
        return this.mazeMap[positionX][positionY];
    }
}
