package factories

import factories.constants.PlayerNames
import factories.constants.Ranges
import model.Defender
import model.Midfielder

/**
 * Factory responsible for creating [Midfielder] instances.
 *
 * Generates defenders with unique names drawn from [factories.constants.PlayerNames.midfielder].
 * If all predefined names are exhausted, fallback names are generated
 * automatically (e.g. "Mediocampista1", "Mediocampista2").
 *
 * Stat distribution:
 * - Defense: medium range ([factories.constants.Ranges.medium])
 * - Attack: medium range ([factories.constants.Ranges.medium])
 *
 * @see PlayersFactory
 * @see Midfielder
 */
class MidfielderFactory: PlayersFactory {
    /**
     * Tracks the names already assigned to avoid duplicates
     * within the same factory instance.
     */
    private val usedNames = mutableSetOf<String>()

    /**
     * Creates and returns a new [Midfielder] with a unique name and randomized stats.
     *
     * The name is picked randomly from the unused entries in [factories.constants.PlayerNames.midfielder].
     * If no predefined names remain, a fallback name is generated using
     * the current count of used names (e.g. "Mediocampista3").
     *
     * @return a newly created [Midfielder] instance.
     */
    override fun createPlayer(): Midfielder {
        val name  = (PlayerNames.midfielder - usedNames).randomOrNull()
            ?: "Mediocampista${usedNames.size + 1}"
        usedNames.add(name)

        val attack  = Ranges.medium.random()
        val defense = Ranges.medium.random()
        return Midfielder(name, attack, defense)
    }
}