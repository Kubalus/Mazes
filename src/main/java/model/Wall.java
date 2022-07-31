package model;

public class Wall extends Field {
    @Override
    public boolean isAccessible() {
        return false;
    }

    @Override
    public void print() {
        System.out.print("X");
    }
}
