package model

abstract class Player(
    val name: String,
    val attack: Int,
    val defense: Int,
    var available: Boolean = true
) {
    /**
     * Cada subclase define si sus atributos son válidos
     * según las reglas de su posición.
     */
    abstract fun isValid(): Boolean

    override fun toString(): String =
        "$name | ATK: $attack  DEF: $defense"
}