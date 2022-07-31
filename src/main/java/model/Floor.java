package model;

public class Floor extends Field {
    @Override
    public boolean isAccessible() {
        return true;
    }

    @Override
    public void print() {
        System.out.print(" ");
    }
}
