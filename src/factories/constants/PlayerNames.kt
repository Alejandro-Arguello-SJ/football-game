package factories.constants

/**
 * Contains the predefined name pools used by player factories
 * when generating new players.
 *
 * Each property holds a set of real-world football player names
 * grouped by their position. Names are used by their corresponding
 * factory to assign unique identifiers to newly created players.
 *
 * If all names in a pool are exhausted, the factory falls back
 * to auto-generated names (e.g. "Defensa1", "Delantero1").
 *
 * @see factories.DefendersFactory
 * @see factories.MidfielderFactory
 * @see factories.StrikersFactory
 */
object PlayerNames {

    /**
     * Name pool for [model.Defender] players.
     * Based on world-class defenders recognized for their defensive skills.
     */
    val defender = setOf(
        "Ramos", "Van Dijk", "Chiellini", "Piqué", "Bonucci",
        "Alaba", "Koulibaly", "Militão", "Skriniar", "Dias"
    )

    /**
     * Name pool for midfielder players.
     * Based on world-class midfielders recognized for their playmaking skills.
     */
    val midfielder = setOf(
        "Modric", "Kroos", "De Bruyne", "Busquets", "Kante",
        "Pedri", "Valverde", "Bellingham", "Camavinga", "James"
    )

    /**
     * Name pool for striker players.
     * Based on world-class strikers recognized for their goal-scoring ability.
     */
    val striker = setOf(
        "Mbappé", "Haaland", "Benzema", "Lewandowski", "Salah",
        "Vinicius", "Osimhen", "Kane", "Lautaro", "Griezmann"
    )
}