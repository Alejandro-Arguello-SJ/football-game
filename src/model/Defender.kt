package model

/**
 * This class represents a football defender
 */
class Defender(
    name: String,
    attack: Int,
    defense: Int
): Player(name, attack, defense)