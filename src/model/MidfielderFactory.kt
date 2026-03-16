package model

class MidfielderFactory: PlayersFactory {
    private val usedNames = mutableSetOf<String>()

    override fun createPlayer(): Midfielder {
        val name  = (PlayerNames.midfielder - usedNames).randomOrNull()
            ?: "Mediocampista${usedNames.size + 1}"
        usedNames.add(name)

        val attack  = Ranges.medium.random()
        val defense = Ranges.medium.random()
        return Midfielder(name, attack, defense)
    }
}