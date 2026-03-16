package model

class Team(val name: String) {

    private val players: MutableList<Player> = mutableListOf()

    companion object {
        const val MIN_PLAYERS = 5
        const val MAX_PLAYERS = 10
    }

    /**
     * Retorna true si se agregó con éxito.
     * Falla si: el equipo ya está lleno, o el jugador no es válido.
     */
    fun addPlayer(player: Player): Boolean {
        if (players.size >= MAX_PLAYERS) return false
        if (!player.isValid()) return false
        players.add(player)
        return true
    }

    /**
     * Recibe el índice del jugador elegido por el usuario.
     * Solo puede ser Delantero o Mediocampista, y debe estar disponible.
     * Lo marca como no disponible y lo retorna.
     */
    fun getPlayer(id: Int): Player? {
        val player = players.getOrNull(id) ?: return null
        if (!player.available) return null
        player.available = false
        return player
    }

    /**
     * Elige un Defensa al azar de la lista.
     * Los defensores no se marcan como no disponibles
     * (pueden repetirse durante el juego).
     */
    fun getDefender(): Player? {
        val defensores = players.filterIsInstance<Defender>()
        return defensores.randomOrNull()
    }

    /**
     * Lista todos los miembros del equipo
     */
    fun listTeam() = players.toList()

    /**
     * Lista los atacantes disponibles con su índice visible al usuario.
     * Solo incluye Delanteros y Mediocampistas disponibles.
     */
    fun listAttackers(): List<Player> =
        players.filter { it is Midfielder || it is Striker }

    fun availableAttackers(): Boolean =
        listAttackers().any { it.available }

    override fun toString(): String = buildString {
        appendLine("── Equipo: $name (${players.size} jugadores) ──")
        listTeam().forEachIndexed { i, j ->
            val state = if (j.available) "✅" else "⛔"
            appendLine("  [$i] $state $j")
        }
    }
}