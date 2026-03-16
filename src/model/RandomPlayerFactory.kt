package model

class RandomPlayerFactory(
    private val defendersFactory: DefendersFactory,
    private val midfielderFactory: MidfielderFactory,
    private val strikersFactory: StrikersFactory
) : PlayersFactory {

    private val factories: List<PlayersFactory> = listOf(
        defendersFactory,
        midfielderFactory,
        strikersFactory
    )

    override fun createPlayer(): Player =
        factories.random().createPlayer()
}