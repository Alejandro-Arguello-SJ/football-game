package view

import controller.TurnResult
import Team

/**
 * Handles all console output for the game.
 *
 * Acts as the presentation layer, formatting and printing each stage
 * of the game: the opening screen, turn-by-turn results, and the
 * final scoreboard. All game output is centralized here to keep
 * [controller.Game] free of display logic.
 *
 * @see controller.Game
 * @see TurnResult
 */
class Printer {

    /**
     * Prints the opening screen with both team rosters.
     *
     * Displays a welcome header followed by the full player list
     * of each team before the game begins.
     *
     * @param teamAName the display name of team A.
     * @param teamA the roster of players for team A.
     * @param teamBName the display name of team B.
     * @param teamB the roster of players for team B.
     */
    fun printHome(
        teamAName: String,
        teamA: Team,
        teamBName: String,
        teamB: Team
    ) {
        println()
        println("========================================")
        println("=       SIMULACIÓN DE PARTIDO          =")
        println("========================================")
        println()
        printTeam(teamAName, teamA)
        println()
        printTeam(teamBName, teamB)
        println()
        println("¡Comienza el partido!")
        println("─".repeat(42))
    }

    /**
     * Prints the full roster of a single team.
     *
     * Each player is printed using their [model.Player.toString] representation.
     *
     * @param teamName the display name of the team.
     * @param team the roster of players to display.
     */
    fun printTeam(teamName: String, team: Team) {
        println("========================================")
        println(teamName)
        println("========================================")
        team.forEach {
            println(it)
        }
        println("========================================")
    }

    /**
     * Prints the current score and which team is attacking.
     *
     * Displayed at the beginning of each turn before the clash occurs.
     *
     * @param teamName the display name of the attacking team.
     * @param pointsA the current score of team A.
     * @param pointsB the current score of team B.
     */
    fun printCurrentState(
        teamName: String,
        pointsA: Int,
        pointsB: Int
    ) {
        println("Ataca: $teamName")
        println("Marcador → $pointsA : $pointsB")
    }

    /**
     * Prints the outcome of a single clash between two players.
     *
     * Displays the attacker's attack stat vs the defender's defense stat,
     * along with a visual indicator of the result:
     * - ✅ if the attacking team earned a point.
     * - ⛔ if no point was awarded.
     *
     * @param result the [TurnResult] from the resolved clash.
     */
    fun printTurnResult(result: TurnResult) {
        val attacker = result.attacker
        val defender = result.defender
        val symbol = if (result.attackerWin) "✅" else "⛔"
        val text = if (result.attackerWin) "¡Punto para ${result.attackerTeam}!"
        else "Sin punto."

        println()
        println("${attacker.name} (ATK ${attacker.attack})")
        println("vs ${defender.name} (DEF ${defender.defense})")
        println("$symbol  $text")
        println("─".repeat(42))
    }

    /**
     * Prints the final scoreboard and declares the winner.
     *
     * Displays the total points for each team and announces the result:
     * - 🏆 the team with more points wins.
     * - 🤝 a tie is declared if both teams have equal points.
     *
     * @param teamAName the display name of team A.
     * @param teamBName the display name of team B.
     * @param pointsA the final score of team A.
     * @param pointsB the final score of team B.
     */
    fun printWinner(
        teamAName: String,
        teamBName: String,
        pointsA: Int,
        pointsB: Int
    ) {
        println()
        println("========================================")
        println("=           RESULTADO FINAL            =")
        println("========================================")
        println()
        println("  ${teamAName.padEnd(20)} $pointsA pts")
        println("  ${teamBName.padEnd(20)} $pointsB pts")
        println()

        val message = when {
            pointsA > pointsB -> "🏆  ¡${teamAName} gana el partido!"
            pointsB > pointsA -> "🏆  ¡${teamBName} gana el partido!"
            else -> "🤝  ¡Empate!"
        }
        println("  $message")
        println()
    }
}