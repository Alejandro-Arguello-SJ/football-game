package model

/**
 * Represents a football midfielder player.
 *
 * A [Midfielder] is a specialized [Player] with balanced stats
 * Instances are typically created through
 * [factories.MidfielderFactory], which handles name uniqueness and stat distribution.
 *
 * @param name the display name of the midfielder.
 * @param attack the attack power of the midfielder. Expected to be a medium value.
 * @param defense the defense power of the midfielder. Expected to be a medium value.
 *
 * @see Player
 * @see factories.MidfielderFactory
 */
class Midfielder(
    name: String,
    attack: Int,
    defense: Int
): Player(name, attack, defense)