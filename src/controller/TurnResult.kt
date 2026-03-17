package controller

import model.Player

data class TurnResult(
    val attacker: Player,
    val defender: Player,
    val attackerTeam: String,
    val attackerWin: Boolean,
)
