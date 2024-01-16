package agh.ics.oop.model;

public class Grass implements IWorldElement<Vector2d> {
    private final Vector2d position;

    public Grass(Vector2d position){
        this.position = position;
    }

    public Vector2d getPosition() {
        return position;
    }

    public boolean isAt(Vector2d position){
        return this.position.equals(position);
    }

    @Override
    public String toString(){
        return "*";
    }

    @Override
    public String getResourceName() {
        return "grass.png";
    }
}
