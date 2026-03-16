import controller.Game
import controller.GameInitializer

fun main() {
    val (teamA, teamB) = GameInitializer().initGame(
        nameA = "Equipo A",
        nameB = "Equipo B"
    )

    Game(teamA, teamB).play()
}