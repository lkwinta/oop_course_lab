package agh.ics.oop.model;

/**
 * Class representing Animal object that will be placed on WorldMap
 * */
public class Animal {
    private MapDirection orientation;
    private Vector2d position;

    /**
     * Default constructor, initializes Animal in position (x,y) = (2,2) and North orientation
     * */
    public Animal(){
        this(new Vector2d(2, 2));
    }

    /**
     * Constructor that sets new Animal at specified position with North orientation
     *
     * @param position Desired position of the anima
     * */
    public Animal(Vector2d position){
        orientation = MapDirection.NORTH;
        this.position = position;
    }

    /**
     * Checks if current animal's position is specified one
     *
     * @param position Position to compare with current animal's position
     **/
    public boolean isAt(Vector2d position){
        return this.position.equals(position);
    }

    /**
     * If possible moves animal with desired move direction
     *
     * @param direction {@link MoveDirection} with which animal will try to move
     * @param moveValidator {@link IMoveValidator} checks if desired move is allowed on the {@link IWorldMap}
     * */
    public void move(MoveDirection direction, IMoveValidator<Vector2d> moveValidator){
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

    /**
     * Getter for orientation attribute
     * */
    public MapDirection getOrientation() {
        return orientation;
    }

    /**
     * Getter for position attribute
     */
    public Vector2d getPosition() {
        return position;
    }

    /**
     * @return Current animals orientation arrow
     * */
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
