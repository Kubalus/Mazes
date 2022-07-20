package controler;

import model.Field;

public interface MazeGenerationStrategy {

    Field[][] generateMaze(int width, int length);

}
