package agh.ics.oop.model

import model.IMoveValidator

interface IWorldMap : IMoveValidator {
    fun place(animal: Animal)

    fun move(animal: Animal, direction: MoveDirection)

    fun isOccupied(position: Vector2d): Boolean

    fun objectAt(position: Vector2d): Animal?

    fun getMapSize(): Vector2d?
}