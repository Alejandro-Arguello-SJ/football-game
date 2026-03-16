package model

class Midfielder(name: String, attack: Int, defense: Int): Player(name, attack, defense){

    init {
        require(kotlin.math.abs(attack - defense) <= 20) {
            "Un Mediocampista debe tener atributos equilibrados " +
                    "(|$attack - $defense| = ${kotlin.math.abs(attack - defense)} > 20)"
        }
    }

    override fun isValid(): Boolean = kotlin.math.abs(attack - defense) <= 20
}