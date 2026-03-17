package controller

import Team

data class TurnContext(
    val attackerTeamName: String,
    val attackerTeam: Team,
    val defenderTeam: Team
)