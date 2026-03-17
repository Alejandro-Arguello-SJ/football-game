package controller

import model.Player
import view.Printer
import Team

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

    private fun popPlayer(team: Team): Player {
        val playerIndex = team.indices.random()
        val player = team[playerIndex]
        team.removeAt(playerIndex)
        return player
    }

    fun getTurnContext() = if (turn == TEAM_A)
        TurnContext(teamAName, teamA, teamB)
    else
        TurnContext(teamBName, teamB, teamA)

    private fun updatePoints(result: TurnResult) {
        if (result.attackerWin) {
            if (turn == TEAM_A) pointsA++ else pointsB++
        }
    }

    private fun shiftTurn() {
        turn = if (turn == TEAM_A) TEAM_B else TEAM_A
    }
}