package controler.solving;

import controler.MazeGenerationStrategy;
import model.Field;
import model.Floor;
import model.Wall;

import java.util.Random;

public class RecursiveDivisionMazeGenerationStrategy implements MazeGenerationStrategy {

    private Field[][] maze;
    private int width;
    private int height;

    private final Random RANDOM = new Random();


    @Override
    public Field[][] generateMaze(int width, int height) {
        dataStructurePreparations(width, height);
        generateExternalWalls();
        generateMazeByDivision();
        placeEntry();
        return this.maze;
    }

    private void generateMazeByDivision() {
        generateMazeByDivision(1, 1, width - 2, height - 2);
    }

    private void generateMazeByDivision(int leftSideX, int upperSideY, int rightSideX, int lowerSideY) {
        // TODO: STOP CONDITION
        int fieldWidth = Math.abs(leftSideX - rightSideX);
        int fieldHeight = Math.abs(upperSideY - lowerSideY);
        if (isFieldHorizontal(fieldWidth, fieldHeight)) {
            divideHorizontalField(leftSideX, upperSideY, rightSideX, lowerSideY);
        } else {
            divideVerticalField(leftSideX, upperSideY, rightSideX, lowerSideY);
        }
    }

    private void divideVerticalField(int leftSideX, int upperSideY, int rightSideX, int lowerSideY) {
        int divider = chooseRandomlyWithoutEdges(upperSideY, lowerSideY);
        int passage = chooseRandomlyWithEdges(leftSideX, rightSideX);
        for (int i = leftSideX; i <= rightSideX; i++) {
            this.maze[i][divider] = new Wall();
        }
        this.maze[passage][divider] = new Floor();
        generateMazeByDivision(leftSideX, upperSideY, rightSideX, divider - 1);
        generateMazeByDivision(leftSideX, divider + 1, rightSideX, lowerSideY);
    }

    private void divideHorizontalField(int leftSideX, int upperSideY, int rightSideX, int lowerSideY) {
        int divider = chooseRandomlyWithoutEdges(leftSideX, rightSideX);
        int passage = chooseRandomlyWithEdges(upperSideY, lowerSideY);
        for (int i = upperSideY; i <= lowerSideY; i++) {
            this.maze[divider][i] = new Wall();
        }
        this.maze[divider][passage] = new Floor();
        generateMazeByDivision(leftSideX, upperSideY, divider - 1, lowerSideY);
        generateMazeByDivision(divider + 1, upperSideY, rightSideX, lowerSideY);
    }

    private int chooseRandomlyWithoutEdges(int rangeMin, int rangeMax) {
        return RANDOM.nextInt(rangeMax - rangeMin - 2) + rangeMin;
    }

    private int chooseRandomlyWithEdges(int rangeMin, int rangeMax) {
        return RANDOM.nextInt(rangeMax - rangeMin) + rangeMin;
    }

    private boolean isFieldHorizontal(int fieldWidth, int fieldHeight) {
        return fieldHeight < fieldWidth;
    }

    private void placeEntry() {

    }


    private void dataStructurePreparations(int width, int height) {
        this.width = width;
        this.height = height;
        this.maze = new Field[width][maze.length];

    }

    private void generateExternalWalls() {
        generateHorizontalWalls();
        generateVerticalWalls();
    }

    private void generateVerticalWalls() {
        for (int y = 0; y < width; y++) {
            maze[0][y] = new Wall();
            maze[width - 1][y] = new Wall();
        }
    }

    private void generateHorizontalWalls() {
        for (int x = 0; x < width; x++) {
            maze[x][0] = new Wall();
            maze[x][height - 1] = new Wall();
        }
    }
}
