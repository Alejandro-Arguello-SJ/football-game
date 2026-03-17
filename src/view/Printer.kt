package view

import controller.TurnResult
import Team

class Printer {

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

     fun printTeam(teamName: String, team: Team) {
         println("========================================")
         println(teamName)
         println("========================================")
         team.forEach {
             println(it)
         }
         println("========================================")
    }

    fun printCurrentState(
        teamName: String,
        pointsA: Int,
        pointsB: Int
    ) {
        println("Ataca: $teamName")
        println("Marcador → $pointsA : $pointsB")
    }

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