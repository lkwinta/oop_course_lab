package model

import agh.ics.oop.model.Vector2d

interface IMoveValidator {
    fun canMoveTo(position: Vector2d) : Boolean
}
