package agh.ics.oop.model;

public class Animal {
    private MapDirection orientation;
    private Vector2d position;

    public Animal(){
        this(new Vector2d(2, 2));
    }

    public Animal(Vector2d position){
        orientation = MapDirection.NORTH;
        this.position = position;
    }

    public boolean isAt(Vector2d position){
        return this.position.equals(position);
    }

    public void move(MoveDirection direction, IMoveValidator moveValidator){
        switch(direction) {
            case LEFT       -> orientation = orientation.previous();
            case RIGHT      -> orientation = orientation.next();
            case FORWARD   -> {
                Vector2d newPosition = position.add(orientation.toUnitVector());

                if(moveValidator.canMoveTo(newPosition))
                    position = newPosition;
            }
            case BACKWARD   -> {
                Vector2d newPosition = position.subtract(orientation.toUnitVector());

                if(moveValidator.canMoveTo(newPosition))
                    position = newPosition;
            }
        }
    }

    public MapDirection getOrientation() {
        return orientation;
    }

    public Vector2d getPosition() {
        return position;
    }

    @Override
    public String toString(){
        return switch(orientation){
            case EAST -> ">";
            case SOUTH -> "v";
            case WEST -> "<";
            case NORTH -> "^";
        };
    }
}
