package agh.ics.oop.model

enum class MoveDirection(private val directionString: String) {
    FORWARD("Forward"),
    BACKWARD("Backward"),
    LEFT("Left"),
    RIGHT("Right");

    override fun toString(): String {
        return directionString
    }
}