package agh.ics.oop.model;

import java.util.List;

/**
 * The interface responsible for interacting with the map of the world.
 * Assumes that MoveDirection classes are defined.
 *
 * @author apohllo, idzik
 */
public interface IWorldMap<T, P> extends IMoveValidator<P> {

    /**
     * Place a T type object on the map.
     *
     * @param object The animal to place on the map.
     */
    void place(T object) throws PositionAlreadyOccupiedException;

    /**
     * Moves an T object (if it is present on the map) according to specified direction.
     * If the move is not possible, this method has no effect.
     */
    void move(T object, MoveDirection direction);

    /**
     * Return true if given position on the map is occupied. Should not be
     * confused with canMove since there might be empty positions where the animal
     * cannot move.
     *
     * @param position Position to check.
     * @return True if the position is occupied.
     */
    boolean isOccupied(P position);

    /**
     * Return an object at a given position.
     *
     * @param position The position of the object.
     * @return animal or null if the position is not occupied.
     */
    T objectAt(P position);

    /**
     * Return all objects on the map
     *
     * @return copy of the list containing all elements on the map
     */
    List<T> getElements();

    /**
     * Return current top right and bottom left corner of the map
     *
     * @return container for top right and bottom left vector
     */
    Boundary getCurrentBounds();
}