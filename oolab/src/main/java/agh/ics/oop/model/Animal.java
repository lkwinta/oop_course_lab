package agh.ics.oop.model;

public class Animal {
    private final Vector2d MAP_RIGHT_TOP = new Vector2d(4, 4);
    private final Vector2d MAP_LEFT_BOTTOM = new Vector2d(0, 0);
    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2, 2);

    public Animal(){}

    public Animal(Vector2d position){
        this.position = position;
    }

    public boolean isAt(Vector2d position){
        return this.position.equals(position);
    }

    public void move(MoveDirection direction){
        switch(direction) {
            case LEFT       -> orientation = orientation.previous();
            case RIGHT      -> orientation = orientation.next();
            case FORWARD   -> {
                Vector2d newPosition = position.add(orientation.toUnitVector());

                if(newPosition.precedes(MAP_RIGHT_TOP) && newPosition.follows(MAP_LEFT_BOTTOM))
                    position = newPosition;
            }
            case BACKWARD   -> {
                Vector2d newPosition = position.subtract(orientation.toUnitVector());

                if(newPosition.precedes(MAP_RIGHT_TOP) && newPosition.follows(MAP_LEFT_BOTTOM))
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
        return "position: %s, orientation: %s".formatted(position.toString(), orientation.toString());
    }
}
