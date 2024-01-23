package agh.ics.oop.model

import kotlin.math.max
import kotlin.math.min

data class Vector2d(
    val x: Int,
    val y: Int
) {

    operator fun compareTo(other: Vector2d): Int {
        if(x <= other.x && y <= other.y)
            return -1
        else if(x >= other.x && y >= other.y)
            return 1

        return 0
    }

    operator fun plus(other: Vector2d) = Vector2d(x + other.x, y + other.y)
    operator fun minus(other: Vector2d) = Vector2d(x - other.x, y - other.y)
    fun opposite() = Vector2d(-x, y)
    fun upperRight(other: Vector2d) = Vector2d(max(x, other.x), max(y, other.y))
    fun lowerLeft(other: Vector2d) = Vector2d(min(x, other.x), min(y, other.y))
    override fun toString() = "($x, $y)"
    override fun equals(other: Any?): Boolean {
        if (other is Vector2d) return other.x == x && other.y == y
        return false
    }
    override fun hashCode() = x.hashCode().times(31).plus(y.hashCode())
}

fun MapDirection.toUnitVector()= when(this){
    MapDirection.NORTH -> Vector2d(0, 1)
    MapDirection.EAST -> Vector2d(1, 0)
    MapDirection.SOUTH -> Vector2d(0, -1)
    MapDirection.WEST -> Vector2d(-1, 0)
}
