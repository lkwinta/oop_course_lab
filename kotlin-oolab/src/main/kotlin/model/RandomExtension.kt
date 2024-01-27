package agh.ics.oop.model

fun <T> Map<Vector2d, T>.randomPosition() = this.keys.toList().random()

fun <S, T> List<S>.cartesianProduct(other: List<T>) = this.flatMap { thisIt ->
    other.map { otherIt -> thisIt to otherIt }
}

fun <T> Map<Vector2d, T>.randomFreePosition(mapSize: Vector2d): Vector2d? {
    val freePositions = (0..<mapSize.x).toList().cartesianProduct((0..<mapSize.y).toList())
        .map { Vector2d(it.first, it.second) }
        .filter { !this.containsKey(it) }

    if(freePositions.isEmpty()) return null
    return freePositions.random()
}