package agh.ics.oop.model;

public class RectangularMap extends AbstractWorldMap {
    private final Vector2d mapTopRight;
    private final Vector2d mapBottomLeft;

    public RectangularMap(int width, int height){
        mapTopRight = new Vector2d(width - 1, height - 1);
        mapBottomLeft = new Vector2d(0, 0);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return super.canMoveTo(position) && position.follows(mapBottomLeft) && position.precedes(mapTopRight);
    }

    @Override
    public Boundary getCurrentBounds() {
        return new Boundary(mapBottomLeft, mapTopRight);
    }
}
