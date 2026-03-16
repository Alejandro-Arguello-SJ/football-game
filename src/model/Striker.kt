package model

class Striker(name: String, attack: Int, defense: Int): Player(name, attack, defense) {
    init {
        require(attack > defense) {
            "Un Delantero debe tener ataque ($attack) > defensa ($defense)"
        }
    }
    override fun isValid(): Boolean = attack > defense

}