package controller

import factories.DefendersFactory
import factories.MidfielderFactory
import factories.RandomPlayerFactory
import factories.StrikersFactory
import model.Player
import Team

/**
 * Responsible for initializing teams before the game starts.
 *
 * Handles the creation of balanced teams by guaranteeing at least
 * one player of each position (defender, midfielder, striker) per team.
 * Remaining slots are filled with players of random positions.
 *
 * Typical usage:
 * ```
 * val initializer = GameInitializer()
 * val (teamA, teamB) = initializer.initTeams(size = 7)
 * ```
 *
 * @see RandomPlayerFactory
 * @see Team
 */
class GameInitializer {

    /**
     * Creates the three specialized factories used during team assembly.
     *
     * Each call produces a fresh set of factories, ensuring name pools
     * are independent between teams.
     *
     * @return a [Triple] containing a [DefendersFactory], [MidfielderFactory],
     * and [StrikersFactory].
     */
    private fun createFactories() = Triple(
        DefendersFactory(),
        MidfielderFactory(),
        StrikersFactory()
    )

    /**
     * Creates a single [Team] with a guaranteed positional distribution.
     *
     * The first three slots are always filled with one defender, one midfielder,
     * and one striker. The remaining slots are filled with randomly positioned players.
     *
     * Example for `size = 7`:
     * - Slot 1: Defender
     * - Slot 2: Midfielder
     * - Slot 3: Striker
     * - Slots 4–7: Random positions
     *
     * @param size the total number of players in the team. Must be at least 3.
     * Defaults to 7.
     * @return a [Team] containing [size] players.
     */
    fun createTeam(size: Int = 7): Team {
        val (defendersFactory, midfielderFactory, strikerFactory) = createFactories()
        val randomFactory = RandomPlayerFactory(defendersFactory, midfielderFactory, strikerFactory)

        val team = mutableListOf<Player>()

        team.add(defendersFactory.createPlayer())
        team.add(midfielderFactory.createPlayer())
        team.add(strikerFactory.createPlayer())

        val remainders = size - 3
        repeat(remainders) {
            team.add(randomFactory.createPlayer())
        }

        return team
    }

    /**
     * Creates and returns two independent [Team]s ready to play.
     *
     * Each team is assembled separately with its own factory instances,
     * ensuring no name collisions between teams.
     *
     * @param size the number of players per team. Defaults to 7.
     * @return a [Pair] where [Pair.first] is team A and [Pair.second] is team B.
     *
     * @see createTeam
     */
    fun initTeams(size: Int = 7): Pair<Team, Team> {
        val teamA = createTeam(size)
        val teamB = createTeam(size)
        return Pair(teamA, teamB)
    }
}