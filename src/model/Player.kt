package model

/**
 * This class is the base to create new players
 */
abstract class Player(
    val name: String,
    val attack: Int,
    val defense: Int
) {
    override fun toString(): String =
        "${this::class.simpleName} | $name | ATK: $attack | DEF: $defense"
}