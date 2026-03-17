package controller

import factories.DefendersFactory
import factories.MidfielderFactory
import factories.RandomPlayerFactory
import factories.StrikersFactory
import model.Player
import Team

class GameInitializer {

    private fun createFactories() = Triple(
        DefendersFactory(),
        MidfielderFactory(),
        StrikersFactory()
    )

    fun createTeam(size: Int = 7): Team {
        val (defendersFactory, midfielderFactory, strikerFactory) = createFactories()
        val randomFactory = RandomPlayerFactory(defendersFactory, midfielderFactory, strikerFactory)

        val team = mutableListOf<Player>()

        team.add(defendersFactory.createPlayer())
        team.add(midfielderFactory.createPlayer())
        team.add(strikerFactory.createPlayer())

        val remainders = size - 3
        repeat(remainders) {
            team.add(randomFactory.createPlayer())
        }

        return team
    }

    fun initTeams(size: Int = 7): Pair<Team, Team> {
        val teamA = createTeam(size)
        val teamB = createTeam(size)
        return Pair(teamA, teamB)
    }
}