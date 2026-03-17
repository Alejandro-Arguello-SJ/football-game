# Football-game

## 1. Project Objective
To simulate a soccer match modeled as a card game, where each player represents a card with
attack and defense values. The project applies the fundamental principles of Object-Oriented Programming (OOP) in
Kotlin—abstraction, inheritance, polymorphism, and encapsulation—within a modular and extensible architecture.

## 2. Project Structure
```
src/
├── controller/
│   ├── Game.kt                  # Orchestrates the match turn by turn
│   ├── GameInitializer.kt       # Builds both teams before the match
│   ├── TurnContext.kt           # Context of the current turn (attacker and defender)
│   └── TurnResult.kt            # Result of a clash between two players
├── factories/
│   ├── constants/
│   │   ├── PlayerNames.kt       # Pool of real names by position
│   │   └── Ranges.kt            # Attribute ranges by position
│   ├── DefendersFactory.kt      # Creates defenders with high defense and low attack
│   ├── MidfielderFactory.kt     # Creates midfielders with balanced attributes
│   ├── PlayersFactory.kt        # Base interface for the Factory pattern
│   ├── RandomPlayerFactory.kt   # Delegates creation to a random factory
│   └── StrikersFactory.kt       # Creates strikers with high attack and low defense
├── model/
│   ├── Defender.kt              # Player subclass: defense specialist
│   ├── Midfielder.kt            # Player subclass: balanced player
│   ├── Player.kt                # Abstract base class for all players
│   └── Striker.kt               # Player subclass: attack specialist
├── view/
│   └── Printer.kt               # Handles all console output
├── Main.kt                      # Program entry point
└── Types.kt                     # Global type aliases (Team)
```

### Component Descriptions

**`controller/`** — contains the match control logic. `Game` orchestrates
the turn loop, resolves each clash, and delegates presentation to `Printer`.
`GameInitializer` separates team construction from match logic,
ensuring at least one player per position on each team. `TurnContext` and
`TurnResult` are data classes that encapsulate the state of each turn, avoiding
the need to pass multiple loose parameters between methods.

**`model/Player` (abstract)** — defines the common properties (`name`,
`attack`, `defense`) and shared behavior such as `toString()`. All
subclasses inherit these attributes without redefining them.

**`Player` subclasses** — `Defender`, `Midfielder`, and `Striker` represent
the three positions in the game. Each is created by its corresponding factory,
which is responsible for assigning stats consistent with its role in the match.

**`factories/`** — implements the Factory pattern to decouple player creation
from the rest of the game. `PlayersFactory` defines the common contract. Each
concrete factory generates players with random stats within the ranges for
their position. `RandomPlayerFactory` delegates to one of the three concrete
factories chosen at random, adding variety to team composition.

**`factories/constants/`** — centralizes the constant values used by the
factories. `PlayerNames` groups pools of real footballer names by position,
avoiding repetitions within the same team. `Ranges` defines the attribute
ranges (`low`, `medium`, `high`) as a singleton object,
eliminating magic numbers scattered across the factories.

**`view/Printer`** — solely responsible for all console output. `Game` does not
print anything directly; it only delegates to `Printer`, maintaining a clear
separation between business logic and presentation.

**`Types.kt`** — centralizes the global type aliases for the project. Defines
`Team` as `MutableList<Player>`, improving readability in method signatures
and classes without introducing additional logic.

**`Main.kt`** — the program entry point. Its sole responsibility is to
instantiate `GameInitializer`, build the teams, and start the match.

# 3. Game Rules

Each team has 7 players by default, with at least 1 defender, 1 midfielder,
and 1 striker. The remaining players are assigned to a random position.

The match is played in alternating turns: Team A attacks first, then Team B.

On each turn, a random player is selected from the attacking team and a random
player from the defending team. Both players are removed from their teams after
the clash, regardless of the outcome.

The attacker wins the clash if their attack value is strictly greater than the
defender's defense value. In the case of a tie, no point is awarded. The attacking
team earns one point if it wins.

The game ends when one of the two teams runs out of players.

The team with the most points at the end of the match wins. In case of a tie, it's a draw.

## 4. How to Run

### Running from IntelliJ IDEA

Open the project in IntelliJ IDEA.

Wait for Gradle to sync the dependencies.

Run the main function in `src/Main.kt`.


## 5. Sample Console Output

```
========================================
=          MATCH SIMULATION            =
========================================

========================================
Team A
========================================
Defender | Alaba | ATK: 40 | DEF: 61
Midfielder | Pedri | ATK: 57 | DEF: 51
Striker | Vinicius | ATK: 79 | DEF: 29
Defender | Dias | ATK: 22 | DEF: 59
Striker | Lautaro | ATK: 78 | DEF: 43
Midfielder | Camavinga | ATK: 43 | DEF: 56
Striker | Lewandowski | ATK: 55 | DEF: 26
========================================

========================================
Team B
========================================
Defender | Koulibaly | ATK: 25 | DEF: 59
Midfielder | Kante | ATK: 42 | DEF: 54
Striker | Griezmann | ATK: 87 | DEF: 25
Defender | Bonucci | ATK: 27 | DEF: 57
Midfielder | James | ATK: 57 | DEF: 45
Striker | Salah | ATK: 80 | DEF: 22
Striker | Kane | ATK: 85 | DEF: 29
========================================

The match begins!
──────────────────────────────────────────
Attacking: Team A
Score → 0 : 0

Lewandowski (ATK 55)
vs James (DEF 45)
✅  Point for Team A!
──────────────────────────────────────────
Attacking: Team B
Score → 1 : 0

Bonucci (ATK 27)
vs Vinicius (DEF 29)
⛔  No point.
──────────────────────────────────────────
Attacking: Team A
Score → 1 : 0

Camavinga (ATK 43)
vs Kane (DEF 29)
✅  Point for Team A!
──────────────────────────────────────────
Attacking: Team B
Score → 2 : 0

Kante (ATK 42)
vs Pedri (DEF 51)
⛔  No point.
──────────────────────────────────────────
Attacking: Team A
Score → 2 : 0

Dias (ATK 22)
vs Koulibaly (DEF 59)
⛔  No point.
──────────────────────────────────────────
Attacking: Team B
Score → 2 : 0

Griezmann (ATK 87)
vs Alaba (DEF 61)
✅  Point for Team B!
──────────────────────────────────────────
Attacking: Team A
Score → 2 : 1

Lautaro (ATK 78)
vs Salah (DEF 22)
✅  Point for Team A!
──────────────────────────────────────────

========================================
=           FINAL RESULT               =
========================================

  Team A             3 pts
  Team B             1 pts

  🏆  Team A wins the match!
 ```

## 6. Extensible Design for Other Sports

The project architecture was designed to easily adapt to other sports without modifying existing classes, following the Open/Closed principle.

### How to Extend the Project

To simulate another sport (e.g., basketball), you only need to:

1. Create the new Player subclasses
    ```kotlin
    class PointGuard(
      name: String,
      attack: Int,
      defense: Int
   ): Player(name, attack, defense)

    class SmallForward(
      name: String,
      attack: Int,
      defense: Int
   ): Player(name, attack, defense)

    class Center(
      name: String,
      attack: Int,
      defense: Int
   ): Player(name, attack, defense)
   ```

2. Create the new factories
   ```kotlin
   class PointGuardFactory : PlayersFactory {
       override fun createPlayer() = PointGuard(
       name    = BasketballNames.pointGuards.random(),
       attack  = Ranges.low.random(),
       defense = Ranges.low.random()
       )
   }
   ```

3. Create a sport-specific initializer
   ```kotlin
   class BasketballInitializer {
      fun initTeams(teamA: String, teamB: String): Pair<Team, Team> {
      // Same logic as GameInitializer, using the new factories
      }
   }
   ```

4. **Reuse `Team`, `Game`, and `Printer` without any changes**

The `Team`, `Game`, and `Printer` classes operate on `Player` generically — they have no knowledge of anything football-specific. They do not need to be modified to support a new sport.

### What Changes and What Doesn't

| Component           | Football                            | Other Sport                  |
|---------------------|-------------------------------------|------------------------------|
| `Player` (abstract) | ✅ No changes                        | ✅ No changes                 |
| `Team`              | ✅ No changes                        | ✅ No changes                 |
| `Game`              | ✅ No changes                        | ✅ No changes                 |
| `Printer`           | ✅ No changes                        | ✅ No changes                 |
| `Player` subclasses | `Defender`, `Midfielder`, `Striker` | New sport-specific classes   |
| Factories           | Football-specific                   | New sport-specific factories |
| Initializer         | `GameInitializer`                   | New initializer              |
| Names               | Footballers                         | Players of the sport         |

---

# 7. Kotlin Notes

## `object`

In Kotlin, `object` allows you to declare a singleton — a class that has exactly
one instance throughout the entire program. It does not need to be instantiated
with `new`, nor does its lifecycle need to be managed manually.

In this project it was used to centralize shared constants across the factories:
```kotlin
object PlayerNames {
    val defender = setOf("Ramos", "Van Dijk", ...)
    val midfielder = setOf("Modric", "Kroos", ...)
    val striker = setOf("Mbappé", "Haaland", ...)
}

object Ranges {
    val low    = 20..45
    val medium = 40..60
    val high   = 55..90
}
```
---

## `companion object`

A `companion object` is an object tied to a specific class. It works as the
Kotlin equivalent of Java's `static` members, but with idiomatic Kotlin syntax.

In this project it was used in `Game` to define the turn identifiers:
```kotlin
class Game(...) {
    companion object {
        const val TEAM_A = 0
        const val TEAM_B = 1
    }
}
```

Since they are constants on the companion object, they can be accessed directly
as `Game.TEAM_A` without needing to instantiate the class.

---

## Ranges (`IntRange`)

Kotlin allows value ranges to be expressed naturally using the `..` operator.
The result is an `IntRange` that can be used to generate random values,
iterate, or check whether a value falls within the range.

In this project, ranges were used to define the attribute bounds for each
position and generate random stats:
```kotlin
val low    = 20..45
val medium = 40..60
val high   = 55..90

val defense = Ranges.high.random()  // random number between 55 and 90
val attack  = Ranges.low.random()   // random number between 20 and 45
```

This ensures that each player type has stats consistent with their position,
without requiring any additional validation logic.

---

## `typealias`

`typealias` allows you to assign an alternative name to an existing type. It does
not create a new type, but rather a more expressive and readable way to refer to
an already existing one.

In this project it was used to represent the concept of a team:
```kotlin
typealias Team = MutableList<Player>
```

Without the alias, method signatures would be more verbose and less expressive:
```kotlin
// Without typealias
fun createTeam(size: Int): MutableList<Player>

// With typealias
fun createTeam(size: Int): Team
```

## `when`

`when` is Kotlin's alternative to Java's `switch`, but much cleaner.
It can be used as an expression (returning a value) or as a statement,
and supports arbitrary conditions in each branch.

```kotlin
val message = when {
    pointsA > pointsB -> "🏆  $teamAName wins the match!"
    pointsB > pointsA -> "🏆  $teamBName wins the match!"
    else              -> "🤝  It's a draw!"
}
```

When used as an expression, the result is assigned directly to `message`,
eliminating the need for a mutable variable and multiple assignments.