import controller.Game
import controller.GameInitializer

fun main() {
    val (teamA, teamB) = GameInitializer().initTeams()
    Game("Equipo A", teamA, "Equipo B", teamB).play()
}