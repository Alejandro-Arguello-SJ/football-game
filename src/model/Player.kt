package model

/**
 * Represents the base structure for all players in the game.
 *
 * This abstract class defines the core attributes and behavior shared
 * across all player types. Concrete subclasses must extend this class
 * to define specialized player variants.
 *
 * @property name the display name of the player.
 * @property attack the attack power of the player, used during clashes.
 * @property defense the defense power of the player, used to resist attacks.
 */
abstract class Player(
    val name: String,
    val attack: Int,
    val defense: Int
) {
    override fun toString(): String =
        "${this::class.simpleName} | $name | ATK: $attack | DEF: $defense"
}