package model;

public abstract class Field {
    int positionX;
    int positionY;

    public abstract boolean isAccessible();

    public abstract void print();
}
