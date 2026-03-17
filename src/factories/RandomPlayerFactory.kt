package factories

import model.Player

/**
 * Factory that creates [Player] instances of a random position.
 *
 * Delegates player creation to one of three specialized factories —
 * [DefendersFactory], [MidfielderFactory], or [StrikersFactory] —
 * chosen at random on each call to [createPlayer].
 *
 *
 * Example usage:
 * ```
 * val factory = RandomPlayerFactory(
 *     DefendersFactory(),
 *     MidfielderFactory(),
 *     StrikersFactory()
 * )
 * val player = factory.createPlayer() // could be any position
 * ```
 *
 * @param defendersFactory factory used to create [model.Defender] players.
 * @param midfielderFactory factory used to create [model.Midfielder] players.
 * @param strikersFactory factory used to create [model.Striker] players.
 *
 * @see PlayersFactory
 * @see DefendersFactory
 * @see MidfielderFactory
 * @see StrikersFactory
 */
class RandomPlayerFactory(
    private val defendersFactory: DefendersFactory,
    private val midfielderFactory: MidfielderFactory,
    private val strikersFactory: StrikersFactory
) : PlayersFactory {

    /**
     * Aggregates all specialized factories for random selection.
     */
    private val factories: List<PlayersFactory> = listOf(
        defendersFactory,
        midfielderFactory,
        strikersFactory
    )

    /**
     * Creates and returns a [Player] of a randomly selected position.
     *
     * Each call independently picks one of the three factories at random,
     * so consecutive calls may return players of different positions.
     *
     * @return a newly created [Player] whose concrete type depends
     * on which factory was selected.
     */
    override fun createPlayer(): Player =
        factories.random().createPlayer()
}