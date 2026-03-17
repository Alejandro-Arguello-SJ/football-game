package controller

import model.Player

/**
 * Represents the outcome of a single clash between two players.
 *
 * Produced by [Game.clash] after comparing the attacker's attack stat
 * against the defender's defense stat. Consumed by [Game.updatePoints]
 * and [view.Printer.printTurnResult] to update the score and display the result.
 *
 * @property attacker the [Player] who performed the attack.
 * @property defender the [Player] who received the attack.
 * @property attackerTeam the display name of the attacking team.
 * @property attackerWin `true` if the attacker's attack strictly exceeded
 * the defender's defense, `false` otherwise.
 *
 * @see Game.clash
 * @see TurnContext
 */
data class TurnResult(
    val attacker: Player,
    val defender: Player,
    val attackerTeam: String,
    val attackerWin: Boolean,
)
