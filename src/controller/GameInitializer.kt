package controller

import model.DefendersFactory
import model.MidfielderFactory
import model.RandomPlayerFactory
import model.StrikersFactory
import model.Team

class GameInitializer {

    private fun createFactories() = Triple(
        DefendersFactory(),
        MidfielderFactory(),
        StrikersFactory()
    )

    fun createTeam(name: String, size: Int = 7): Team {
        require(size in Team.MIN_PLAYERS..Team.MAX_PLAYERS) {
            "El tamaño del equipo debe estar entre " +
                    "${Team.MIN_PLAYERS} y ${Team.MAX_PLAYERS}"
        }

        val (defendersFactory, midfielderFactory, strikerFactory) = createFactories()
        val randomFactory = RandomPlayerFactory(defendersFactory, midfielderFactory, strikerFactory)

        val team = Team(name)

        team.addPlayer(defendersFactory.createPlayer())
        team.addPlayer(midfielderFactory.createPlayer())
        team.addPlayer(strikerFactory.createPlayer())

        val remainders = size - 3
        repeat(remainders) {
            team.addPlayer(randomFactory.createPlayer())
        }

        return team
    }


    fun initGame(
        nameA: String = "Equipo A",
        nameB: String = "Equipo B",
        size: Int = 7
    ): Pair<Team, Team> {
        val teamA = createTeam(nameA, size)
        val teamB = createTeam(nameB, size)
        return Pair(teamA, teamB)
    }
}