package controller

import Team

/**
 * Holds the context needed to execute a single turn in the game.
 *
 * Encapsulates which team is attacking and which is defending,
 * along with the attacker's display name. Created by [Game.getTurnContext]
 * at the start of each turn.
 *
 * @property attackerTeamName the display name of the attacking team.
 * @property attackerTeam the roster of players for the attacking team.
 * @property defenderTeam the roster of players for the defending team.
 *
 * @see Game.getTurnContext
 * @see TurnResult
 */
data class TurnContext(
    val attackerTeamName: String,
    val attackerTeam: Team,
    val defenderTeam: Team
)