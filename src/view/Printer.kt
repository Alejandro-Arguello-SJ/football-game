package view

import controller.TurnResult
import model.Team

class Printer {

    fun printHome(teamA: Team, teamB: Team) {
        println()
        println("========================================")
        println("=       SIMULACIÓN DE PARTIDO          =")
        println("========================================")
        println()
        printTeam(teamA)
        println()
        printTeam(teamB)
        println()
        println("¡Comienza el partido!")
        println("─".repeat(42))
    }

     fun printTeam(team: Team) {
        println("  🟦 ${team.name}")
        println(team)
    }

    fun printCurrentState(
        turnNumber: Int,
        teamForward: Team,
        pointsA: Int,
        pointsB: Int
    ) {
        println()
        println("  Turno $turnNumber — Ataca: ${teamForward.name}")
        println("  Marcador → $pointsA : $pointsB")
        println()
    }

    fun printTurnResult(result: TurnResult) {
        val attacker = result.attacker
        val defender = result.defender
        val symbol  = if (result.attackerWin) "✅" else "⛔"
        val text    = if (result.attackerWin) "¡Punto para ${result.attackerTeam}!"
        else "Sin punto."

        println()
        println(" ${attacker.name} (ATK ${attacker.attack})")
        println(" vs ${defender.name} (DEF ${defender.defense})")
        println("  $symbol  $text")
        println("─".repeat(42))
    }

    fun printInvalidInput() {
        println("Selección inválida. Intenta de nuevo.")
    }


    fun printWinner(
        teamA: Team,
        teamB: Team,
        pointsA: Int,
        pointsB: Int
    ) {
        println()
        println("========================================")
        println("=           RESULTADO FINAL            =")
        println("========================================")
        println()
        println("  ${teamA.name.padEnd(20)} $pointsA pts")
        println("  ${teamB.name.padEnd(20)} $pointsB pts")
        println()

        val mensaje = when {
            pointsA > pointsB -> "🏆  ¡${teamA.name} gana el partido!"
            pointsB > pointsA -> "🏆  ¡${teamB.name} gana el partido!"
            else              -> "🤝  ¡Empate!"
        }
        println("  $mensaje")
        println()
    }
}