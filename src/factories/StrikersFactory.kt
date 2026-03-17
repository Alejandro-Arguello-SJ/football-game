package factories

import factories.PlayerNames
import factories.Ranges
import model.Striker

class StrikersFactory : PlayersFactory {
    private val usedNames = mutableSetOf<String>()

    override fun createPlayer(): Striker {
        val name  = (PlayerNames.striker - usedNames).randomOrNull()
            ?: "Delantero${usedNames.size + 1}"
        usedNames.add(name)

        val attack  = Ranges.high.random()
        val defense = Ranges.low.random()
        return Striker(name, attack, defense)
    }
}