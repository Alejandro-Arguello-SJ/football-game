package model

/**
 * Represents a football striker player.
 *
 * A [Striker] is a specialized [Player] focused on high attack
 * and low defense stats. Instances are typically created through
 * [factories.StrikersFactory], which handles name uniqueness and stat distribution.
 *
 * @param name the display name of the striker.
 * @param attack the attack power of the striker. Expected to be a high value.
 * @param defense the defense power of the striker. Expected to be a low value.
 *
 * @see Player
 * @see factories.DefendersFactory
 */
class Striker(
    name: String,
    attack: Int,
    defense: Int
): Player(name, attack, defense)