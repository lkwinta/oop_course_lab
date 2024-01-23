package agh.ics.oop.model

class BouncyMap (
    private val width: Int,
    private val height: Int
) : IWorldMap {
    private val animalsMap = mutableMapOf<Vector2d, Animal>()

    override fun place(animal: Animal) {
        if (!animalsMap.containsKey(animal.position)) {
            animalsMap[animal.position] = animal
        } else {
            val randomPosition = animalsMap.randomFreePosition(getMapSize())

            if (randomPosition != null) {
                animal.position = randomPosition
            } else {
                animal.position = animalsMap.randomPosition()
                animalsMap[animal.position] = animal
            }
        }
    }

    override fun move(animal: Animal, direction: MoveDirection) {
        val oldPosition = animal.position
        animal.move(direction, this)
        val newPosition = animal.position

        if(oldPosition != newPosition) {
            animalsMap.remove(oldPosition)
            place(animal)
        }
    }

    override fun isOccupied(position: Vector2d) = objectAt(position) != null

    override fun objectAt(position: Vector2d) = animalsMap[position]

    override fun getMapSize() = Vector2d(width, height)

    override fun canMoveTo(position: Vector2d) = position.x in 0..<width && position.y in 0..<height

}