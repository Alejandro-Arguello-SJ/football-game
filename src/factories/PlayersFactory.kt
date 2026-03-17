package factories

import model.Player

interface PlayersFactory {
    fun createPlayer(): Player
}