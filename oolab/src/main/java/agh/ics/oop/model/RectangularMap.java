package agh.ics.oop.model;

public class RectangularMap extends AbstractWorldMap {
    private final Vector2d MAP_TOP_RIGHT;
    private final Vector2d MAP_BOTTOM_LEFT;

    public RectangularMap(int width, int height){
        super();

        MAP_TOP_RIGHT = new Vector2d(width - 1, height - 1);
        MAP_BOTTOM_LEFT = new Vector2d(0, 0);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return super.canMoveTo(position) && position.follows(MAP_BOTTOM_LEFT) && position.precedes(MAP_TOP_RIGHT);
    }

    @Override
    public String toString() {
        return mapVisualizer.draw(MAP_BOTTOM_LEFT, MAP_TOP_RIGHT);
    }
}
