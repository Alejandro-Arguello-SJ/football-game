package factories

import factories.constants.PlayerNames
import factories.constants.Ranges
import model.Defender
import model.Midfielder
import model.Striker

/**
 * Factory responsible for creating [Striker] instances.
 *
 * Generates defenders with unique names drawn from [factories.constants.PlayerNames.striker].
 * If all predefined names are exhausted, fallback names are generated
 * automatically (e.g. "Mediocampista1", "Mediocampista2").
 *
 * Stat distribution:
 * - Defense: low range ([factories.constants.Ranges.low])
 * - Attack: high range ([factories.constants.Ranges.high])
 *
 * @see PlayersFactory
 * @see Midfielder
 */
class StrikersFactory : PlayersFactory {
    /**
     * Tracks the names already assigned to avoid duplicates
     * within the same factory instance.
     */
    private val usedNames = mutableSetOf<String>()

    /**
     * Creates and returns a new [Striker] with a unique name and randomized stats.
     *
     * The name is picked randomly from the unused entries in [factories.constants.PlayerNames.striker].
     * If no predefined names remain, a fallback name is generated using
     * the current count of used names (e.g. "Delantero3").
     *
     * @return a newly created [Striker] instance.
     */
    override fun createPlayer(): Striker {
        val name  = (PlayerNames.striker - usedNames).randomOrNull()
            ?: "Delantero${usedNames.size + 1}"
        usedNames.add(name)

        val attack  = Ranges.high.random()
        val defense = Ranges.low.random()
        return Striker(name, attack, defense)
    }
}