package factories

import model.Player

/**
 * Factory responsible for creating [Player] instances.
 *
 * Implementations of this interface define the specific strategy
 * used to build players, allowing the creation logic to vary
 * independently of the rest of the game.
*/
interface PlayersFactory {
    /**
     * Creates and returns a new [Player] instance.
     *
     * The creation strategy (e.g. random stats, user input, predefined values)
     * depends on the concrete implementation.
     *
     * @return a newly created [Player] instance.
     */
    fun createPlayer(): Player
}