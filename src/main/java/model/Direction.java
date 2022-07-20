package model;

public enum Direction {
    NORTH(0, 1),
    EAST(1, 0),
    SOUTH(0, -1),
    WEST(-1, 0);

    private final int modifierX;
    private final int modifierY;

    Direction(int modifierX, int modifierY) {
        this.modifierX = modifierX;
        this.modifierY = modifierY;
    }

    public int getModifierX() {
        return modifierX;
    }

    public int getModifierY() {
        return modifierY;
    }
}
