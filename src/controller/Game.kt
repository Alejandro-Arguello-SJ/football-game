package controller

import model.Player
import view.Printer
import Team

/**
 * Orchestrates the flow of a football clash game between two teams.
 *
 * Manages the turn-based loop where players from each team face off
 * one at a time. The attacker wins a clash if their attack stat
 * exceeds the defender's defense stat. The game ends when either
 * team runs out of players.
 *
 * Game flow:
 * 1. Display initial team rosters.
 * 2. Each turn: pick a random player from each team and clash them.
 * 3. Award a point to the attacking team if they win the clash.
 * 4. Alternate turns until both teams are empty.
 * 5. Declare the winner based on total points.
 *
 * @param teamAName the display name of team A.
 * @param teamA the roster of players for team A.
 * @param teamBName the display name of team B.
 * @param teamB the roster of players for team B.
 * @param printer the [Printer] instance used to display game output.
 * Defaults to a new [Printer] instance.
 *
 * @see GameInitializer
 * @see Printer
 */
class Game(
    private val teamAName: String,
    private val teamA: Team,
    private val teamBName: String,
    private val teamB: Team,
    private val printer: Printer = Printer()
) {
    companion object {
        const val TEAM_A = 0
        const val TEAM_B = 1
    }

    private var pointsA = 0
    private var pointsB = 0
    private var turn = TEAM_A

    /**
     * Starts and runs the game until completion.
     *
     * Executes the full turn-based loop, printing the game state
     * at each step and declaring a winner once both teams are empty.
     */
    fun play() {
        printer.printHome(teamAName, teamA, teamBName, teamB)

        while (!teamA.isEmpty() && !teamB.isEmpty()) {
            val (attackerTeamName, attackerTeam, defenderTeam) = getTurnContext()

            printer.printCurrentState(attackerTeamName, pointsA, pointsB)

            val attacker = popPlayer(attackerTeam)
            val defender = popPlayer(defenderTeam)

            val result = clash(attacker, defender, attackerTeamName)

            printer.printTurnResult(result)
            updatePoints(result)
            shiftTurn()
        }

        printer.printWinner(teamAName, teamBName, pointsA, pointsB)
    }

    /**
     * Resolves a clash between an attacker and a defender.
     *
     * The attacker wins if their [Player.attack] stat strictly
     * exceeds the defender's [Player.defense] stat.
     *
     * @param attacker the player performing the attack.
     * @param defender the player receiving the attack.
     * @param attackerTeam the name of the attacking team.
     * @return a [TurnResult] describing the outcome of the clash.
     */
    private fun clash(
        attacker: Player,
        defender: Player,
        attackerTeam: String
    ): TurnResult {
        val win = attacker.attack > defender.defense
        return TurnResult(
            attacker = attacker,
            defender = defender,
            attackerTeam = attackerTeam,
            attackerWin = win
        )
    }

    /**
     * Removes and returns a random [Player] from the given team.
     *
     * @param team the team from which a player will be removed.
     * @return the randomly selected and removed [Player].
     */
    private fun popPlayer(team: Team): Player {
        val playerIndex = team.indices.random()
        val player = team[playerIndex]
        team.removeAt(playerIndex)
        return player
    }

    /**
     * Returns the [TurnContext] for the current turn.
     *
     * Determines which team is attacking and which is defending
     * based on the current [turn] value.
     *
     * @return a [TurnContext] containing the attacker's name,
     * attacking team, and defending team.
     */
    fun getTurnContext() = if (turn == TEAM_A)
        TurnContext(teamAName, teamA, teamB)
    else
        TurnContext(teamBName, teamB, teamA)

    /**
     * Updates the score based on the result of the current clash.
     *
     * Increments [pointsA] or [pointsB] only if the attacker won.
     *
     * @param result the [TurnResult] from the most recent clash.
     */
    private fun updatePoints(result: TurnResult) {
        if (result.attackerWin) {
            if (turn == TEAM_A) pointsA++ else pointsB++
        }
    }

    /**
     * Advances the turn to the opposing team.
     *
     * Alternates between [TEAM_A] and [TEAM_B] after each clash.
     */
    private fun shiftTurn() {
        turn = if (turn == TEAM_A) TEAM_B else TEAM_A
    }
}