package factories

import model.Defender
import factories.PlayerNames
import factories.Ranges

class DefendersFactory : PlayersFactory {
    private val usedNames = mutableSetOf<String>()

    override fun createPlayer(): Defender {
        val name  = (PlayerNames.defender - usedNames).randomOrNull()
            ?: "Defensa${usedNames.size + 1}"
        usedNames.add(name)

        val defense = Ranges.high.random()
        val attack  = Ranges.low.random()
        return Defender(name, attack, defense)
    }
}