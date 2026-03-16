package model

class Defender(
    name: String,
    attack: Int,
    defense: Int): Player(name, attack, defense) {

    init {
        require(defense > attack) {
            "Un Defensa debe tener defensa ($defense) > ataque ($attack)"
        }
    }

    override fun isValid() = defense > attack
}