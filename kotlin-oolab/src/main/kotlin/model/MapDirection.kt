package agh.ics.oop.model

enum class MapDirection(
    private val stringRepresentation: String,
) {
    NORTH("North"),
    EAST("East"),
    SOUTH("South"),
    WEST("West");

    override fun toString() = stringRepresentation
    fun next() = entries[(this.ordinal + 1) % entries.size]
    fun previous()= entries[(this.ordinal + entries.size - 1) % entries.size]
    }