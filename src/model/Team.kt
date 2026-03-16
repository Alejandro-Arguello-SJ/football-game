package model

class Team(val nombre: String) {

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
    fun getAttacker(id: Int): Player? {
        val player = listAttackers().getOrNull(id) ?: return null
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
     * Lista los atacantes disponibles con su índice visible al usuario.
     * Solo incluye Delanteros y Mediocampistas disponibles.
     */
    fun listAttackers(): List<Player> =
        players.filter { it is Defender || it is Midfielder }

    fun availableAttackers(): Boolean =
        listAttackers().any { it.available }

    fun isValid(): Boolean {
        if (players.size !in MIN_PLAYERS..MAX_PLAYERS) return false
        if (players.none { it is Defender }) return false
        if (players.none { it is Midfielder }) return false
        if (players.none { it is Striker }) return false
        return true
    }

    override fun toString(): String = buildString {
        appendLine("── Equipo: $nombre (${players.size} jugadores) ──")
        listAttackers().forEachIndexed { i, j ->
            val estado = if (j.available) "✅" else "⛔"
            appendLine("  [$i] $estado $j")
        }
        players.filterIsInstance<Defender>().forEach { d ->
            appendLine("  [D] $d")
        }
    }
}