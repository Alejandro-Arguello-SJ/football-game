package model

/**
 * Represents a football defender player.
 *
 * A [Defender] is a specialized [Player] focused on high defense
 * and low attack stats. Instances are typically created through
 * [factories.DefendersFactory], which handles name uniqueness and stat distribution.
 *
 * @param name the display name of the defender.
 * @param attack the attack power of the defender. Expected to be a low value.
 * @param defense the defense power of the defender. Expected to be a high value.
 *
 * @see Player
 * @see factories.DefendersFactory
 */
class Defender(
    name: String,
    attack: Int,
    defense: Int
): Player(name, attack, defense)