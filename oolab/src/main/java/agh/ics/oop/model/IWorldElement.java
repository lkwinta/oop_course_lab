package agh.ics.oop.model;

public interface IWorldElement<T> {
    T getPosition();

    boolean isAt(T position);

    @Override
    String toString();
}
