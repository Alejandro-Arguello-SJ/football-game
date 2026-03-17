package factories.constants

/**
 * Defines the stat ranges used when generating player attributes.
 *
 * Each range represents a difficulty tier assigned to a specific
 * player stat (attack or defense) depending on their position.
 * Ranges are consumed by player factories during player creation.
 *
 * Range overview:
 * - [low]:    20–45  (weak stat)
 * - [medium]: 40–60  (balanced stat)
 * - [high]:   55–90  (strong stat)
 *
 * @see factories.DefendersFactory
 * @see factories.MidfielderFactory
 * @see factories.StrikersFactory
 */
object Ranges {

    /** Represents a weak stat. Typically assigned to the secondary attribute of a player. */
    val low = 20..45

    /** Represents a balanced stat. Typically assigned to well-rounded players. */
    val medium = 40..60

    /** Represents a strong stat. Typically assigned to the primary attribute of a player. */
    val high = 55..90
}