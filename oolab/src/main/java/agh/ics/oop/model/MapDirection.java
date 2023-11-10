package agh.ics.oop.model;

public enum MapDirection {
    NORTH("Polnoc", new Vector2d(0, 1)),
    EAST("Wschod", new Vector2d(1, 0)),
    SOUTH("Poludnie", new Vector2d(0, -1)),
    WEST("Zachod", new Vector2d(-1, 0));

    private final String stringRepresentation;
    private final Vector2d vectorRepresentation;

    MapDirection(String stringRepresentation, Vector2d vectorRepresentation){
        this.stringRepresentation = stringRepresentation;
        this.vectorRepresentation = vectorRepresentation;
    }

    @Override
    public String toString(){
        return stringRepresentation;
    }

    public MapDirection next(){
        return values()[(this.ordinal() + 1) % values().length];
    }

    public MapDirection previous() {
        return values()[(this.ordinal() + values().length - 1) % values().length];
    }

    public Vector2d toUnitVector() {
        return vectorRepresentation;
    }

}
