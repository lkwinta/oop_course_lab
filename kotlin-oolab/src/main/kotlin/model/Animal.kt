package agh.ics.oop.model

import model.IMoveValidator

class Animal (
    position: Vector2d = Vector2d(2, 2)
) {
    var orientation: MapDirection = MapDirection.NORTH
        get() = field
        private set(value) { field = value }

    var position = position
        get() = field
        set(value) { field = value }

    fun isAt(position: Vector2d) = this.position == position
    fun move(direction: MoveDirection, moveValidator: IMoveValidator) {
        when (direction){
            MoveDirection.FORWARD -> {
                val newPosition = position + orientation.toUnitVector()
                if (moveValidator.canMoveTo(newPosition)) position = newPosition
            }
            MoveDirection.BACKWARD -> {
                val newPosition = position - orientation.toUnitVector()
                if (moveValidator.canMoveTo(newPosition)) position = newPosition
            }
            MoveDirection.RIGHT -> orientation = orientation.next()
            MoveDirection.LEFT -> orientation = orientation.previous()
        }
    }

    override fun toString(): String {
        return "Position: $position, Orientation: $orientation"
    }
}