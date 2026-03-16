package controller

import model.Defender
import model.Player
import model.Team
import view.Printer

enum class Turn { TEAM_A, TEAM_B }

data class TurnResult(
    val turn: Int,
    val attacker: Player,
    val defender: Player,
    val attackerTeam: String,
    val attackerWin: Boolean
)

class Game(
    private val teamA: Team,
    private val teamB: Team,
    private val printer: Printer = Printer()
) {
    private var pointsA = 0
    private var pointsB = 0
    private var turn = Turn.TEAM_A
    private var turnNumber = 0
    

    fun play() {
        printer.printHome(teamA, teamB)

        while (teamA.availableAttackers() && teamB.availableAttackers()) {
            turnNumber++
            val (attackerTeam, defenderTeam) = teamsByTurn()

            printer.printCurrentState(turnNumber, attackerTeam, pointsA, pointsB)

            val attacker = getAttacker(attackerTeam) ?: break
            val defender = defenderTeam.getDefender() ?: break

            val result = clash(turnNumber,attacker, defender, attackerTeam.name)

            printer.printTurnResult(result)
            updatePoints(result)
            shiftTurn()
        }

        printer.printWinner(teamA, teamB, pointsA, pointsB)
    }

    private fun clash(
        turnNumber: Int,
        attacker: Player,
        defender: Player,
        attackerTeam: String
    ): TurnResult {
        val win = attacker.attack > defender.defense
        return TurnResult(
            turn           = turnNumber,
            attacker        = attacker,
            defender        = defender,
            attackerTeam  = attackerTeam,
            attackerWin    = win
        )
    }

    private fun getAttacker(team: Team): Player? {
        val available  = team.listTeam().filter { it.available }

        if (available.isEmpty()) return null

        printer.printTeam(team)

        var player: Player? = null
        while (player == null) {
            print("  Elige un atacante: ")
            val input = readLine()?.trim()?.toIntOrNull()
            player = if (input != null) team.getPlayer(input) else null
            if (player == null != player is Defender) {
                player = null
                printer.printInvalidInput()
            }
        }
        return player
    }

    private fun teamsByTurn(): Pair<Team, Team> =
        if (turn == Turn.TEAM_A) Pair(teamA, teamB)
        else Pair(teamB, teamA)

    private fun updatePoints(result: TurnResult) {
        if (result.attackerWin) {
            if (turn == Turn.TEAM_A) pointsA++ else pointsB++
        }
    }

    private fun shiftTurn() {
        turn = if (turn == Turn.TEAM_A) Turn.TEAM_B else Turn.TEAM_A
    }
}