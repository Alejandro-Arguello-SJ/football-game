package factories

import factories.constants.PlayerNames
import factories.constants.Ranges
import model.Defender

/**
 * Factory responsible for creating [Defender] instances.
 *
 * Generates defenders with unique names drawn from [factories.constants.PlayerNames.defender].
 * If all predefined names are exhausted, fallback names are generated
 * automatically (e.g. "Defensa1", "Defensa2").
 *
 * Stat distribution:
 * - Defense: high range ([factories.constants.Ranges.high])
 * - Attack: low range ([factories.constants.Ranges.low])
 *
 * @see PlayersFactory
 * @see Defender
 */
class DefendersFactory : PlayersFactory {
    /**
     * Tracks the names already assigned to avoid duplicates
     * within the same factory instance.
     */
    private val usedNames = mutableSetOf<String>()

    /**
     * Creates and returns a new [Defender] with a unique name and randomized stats.
     *
     * The name is picked randomly from the unused entries in [factories.constants.PlayerNames.defender].
     * If no predefined names remain, a fallback name is generated using
     * the current count of used names (e.g. "Defensa3").
     *
     * @return a newly created [Defender] instance.
     */
    override fun createPlayer(): Defender {
        val name  = (PlayerNames.defender - usedNames).randomOrNull()
            ?: "Defender${usedNames.size + 1}"
        usedNames.add(name)

        val defense = Ranges.high.random()
        val attack  = Ranges.low.random()
        return Defender(name, attack, defense)
    }
}