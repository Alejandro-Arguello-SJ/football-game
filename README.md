# football-game

## 1. Objetivo del proyecto
Simular un partido de fútbol modelado como un juego de cartas, donde cada jugador representa una carta con valores 
de ataque y defensa. El proyecto aplica los principios fundamentales de la Programación Orientada a Objetos (POO) en Kotlin: abstracción, herencia, polimorfismo y encapsulamiento, dentro de una arquitectura modular y extensible.

## 2. Estructura del proyecto
```
src/
├── controller/
│   ├── Game.kt                  # Orquesta la partida turno a turno
│   ├── GameInitializer.kt       # Construye los dos equipos antes del partido
│   ├── TurnContext.kt           # Contexto del turno actual (atacante y defensor)
│   └── TurnResult.kt            # Resultado de un choque entre dos jugadores
├── factories/
│   ├── constants/
│   │   ├── PlayerNames.kt       # Banco de nombres reales por posición
│   │   └── Ranges.kt            # Rangos de atributos por posición
│   ├── DefendersFactory.kt      # Crea defensas con alta defensa y bajo ataque
│   ├── MidfielderFactory.kt     # Crea mediocampistas con atributos equilibrados
│   ├── PlayersFactory.kt        # Interfaz base del patrón Factory
│   ├── RandomPlayerFactory.kt   # Delega la creación a una fábrica al azar
│   └── StrikersFactory.kt       # Crea delanteros con alto ataque y baja defensa
├── model/
│   ├── Defender.kt              # Subclase de Player: especialista en defensa
│   ├── Midfielder.kt            # Subclase de Player: jugador equilibrado
│   ├── Player.kt                # Clase abstracta base de todos los jugadores
│   └── Striker.kt               # Subclase de Player: especialista en ataque
├── view/
│   └── Printer.kt               # Toda la salida en consola
├── Main.kt                      # Punto de entrada del programa
└── Types.kt                     # Type aliases globales (Team)
```

### Descripción de cada componente

**`controller/`** — contiene la lógica de control del partido. `Game` orquesta
el bucle de turnos, resuelve cada choque y delega la presentación a `Printer`.
`GameInitializer` separa la construcción de equipos de la lógica del partido,
garantizando al menos un jugador de cada posición por equipo. `TurnContext` y
`TurnResult` son data classes que encapsulan el estado de cada turno, evitando
pasar múltiples parámetros sueltos entre métodos.

**`model/Player` (abstracta)** — define las propiedades comunes (`name`,
`attack`, `defense`) y el comportamiento compartido como `toString()`. Todas
las subclases heredan estos atributos sin redefinirlos.

**Subclases de `Player`** — `Defender`, `Midfielder` y `Striker` representan
las tres posiciones del juego. Cada una es creada por su fábrica correspondiente,
que se encarga de asignar stats coherentes con su rol en el partido.

**`factories/`** — implementa el patrón Factory para desacoplar la creación de
jugadores del resto del juego. `PlayersFactory` define el contrato común. Cada
fábrica concreta genera jugadores con stats aleatorias dentro de los rangos de
su posición. `RandomPlayerFactory` delega a una de las tres fábricas concretas
elegida al azar, añadiendo variedad a la composición de los equipos.

**`factories/constants/`** — centraliza los valores constantes usados por las
fábricas. `PlayerNames` agrupa bancos de nombres reales de futbolistas por
posición, evitando repeticiones dentro del mismo equipo. `Ranges` define los
rangos de atributos (`low`, `medium`, `high`) como un objeto singleton,
eliminando números mágicos dispersos en las fábricas.

**`view/Printer`** — única responsable de toda la salida en consola. `Game` no
imprime nada directamente, solo delega en `Printer`, manteniendo una separación
clara entre lógica de negocio y presentación.

**`Types.kt`** — centraliza los type aliases globales del proyecto. Define
`Team` como `MutableList<Player>`, mejorando la legibilidad en firmas de
métodos y clases sin introducir lógica adicional.

**`Main.kt`** — punto de entrada del programa. Su única responsabilidad es
instanciar `GameInitializer`, construir los equipos e iniciar la partida

# 3. Reglas del juego

Cada equipo tiene 7 jugadores por defecto, con al menos 1 defensa, 1 mediocampista
y 1 delantero. Los jugadores restantes se asignan a una posición aleatoria.

El partido se juega por turnos alternados: primero ataca el Equipo A, luego el Equipo B.

En cada turno, se selecciona un jugador al azar del equipo atacante y un jugador
al azar del equipo defensor. Ambos jugadores son eliminados del equipo tras el choque,
independientemente del resultado.

Gana el enfrentamiento si el ataque del atacante es estrictamente mayor que la defensa
del defensor. En caso de empate de stats, no se otorga punto. El equipo atacante suma
un punto si gana.

El juego termina cuando alguno de los dos equipos se queda sin jugadores.

Gana el equipo con más puntos al final del partido. En caso de igualdad, es empate.

## 4. Instrucciones para ejecutar

### Ejecutar desde IntelliJ IDEA

Abrir el proyecto en IntelliJ IDEA.

Esperar a que Gradle sincronice las dependencias.

Ejecutar la función main en src/Main.kt.


## 5. Ejemplo de salida en consola

```
========================================
=       SIMULACIÓN DE PARTIDO          =
========================================

========================================
Equipo A
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
Equipo B
========================================
Defender | Koulibaly | ATK: 25 | DEF: 59
Midfielder | Kante | ATK: 42 | DEF: 54
Striker | Griezmann | ATK: 87 | DEF: 25
Defender | Bonucci | ATK: 27 | DEF: 57
Midfielder | James | ATK: 57 | DEF: 45
Striker | Salah | ATK: 80 | DEF: 22
Striker | Kane | ATK: 85 | DEF: 29
========================================

¡Comienza el partido!
──────────────────────────────────────────
Ataca: Equipo A
Marcador → 0 : 0

Lewandowski (ATK 55)
vs James (DEF 45)
✅  ¡Punto para Equipo A!
──────────────────────────────────────────
Ataca: Equipo B
Marcador → 1 : 0

Bonucci (ATK 27)
vs Vinicius (DEF 29)
⛔  Sin punto.
──────────────────────────────────────────
Ataca: Equipo A
Marcador → 1 : 0

Camavinga (ATK 43)
vs Kane (DEF 29)
✅  ¡Punto para Equipo A!
──────────────────────────────────────────
Ataca: Equipo B
Marcador → 2 : 0

Kante (ATK 42)
vs Pedri (DEF 51)
⛔  Sin punto.
──────────────────────────────────────────
Ataca: Equipo A
Marcador → 2 : 0

Dias (ATK 22)
vs Koulibaly (DEF 59)
⛔  Sin punto.
──────────────────────────────────────────
Ataca: Equipo B
Marcador → 2 : 0

Griezmann (ATK 87)
vs Alaba (DEF 61)
✅  ¡Punto para Equipo B!
──────────────────────────────────────────
Ataca: Equipo A
Marcador → 2 : 1

Lautaro (ATK 78)
vs Salah (DEF 22)
✅  ¡Punto para Equipo A!
──────────────────────────────────────────

========================================
=           RESULTADO FINAL            =
========================================

  Equipo A             3 pts
  Equipo B             1 pts

  🏆  ¡Equipo A gana el partido!
 ```

## 6. Diseño extensible para otros deportes

La arquitectura del proyecto fue diseñada para adaptarse fácilmente a otros deportes sin modificar las clases existentes, siguiendo el principio Abierto/Cerrado.

Cómo extender el proyecto

Para simular otro deporte (por ejemplo, baloncesto), solo necesitas:

1. Crear las nuevas subclases de Jugador
    ```kotlin
    class Base(
      name: String,
      attack: Int,
      defense: Int
   ): Jugador(nombre, ataque, defensa)
    
    class Alero(
      name: String,
      attack: Int,
      defense: Int
   ): Jugador(nombre, ataque, defensa)
    
    class Pivot(
      name: String,
      attack: Int,
      defense: Int
   ):Jugador(nombre, ataque, defensa)
   ```

2. Crear las nuevas fábricas
   ```kotlin
   class FabricaBases : FabricaJugadores {
       override fun crearJugador() = Base(
       nombre  = NombresBasquetbol.bases.random(),
       ataque  = Ranges.low.random(),
       defensa = Ranges.low.random()
       )
   }
   ```
3. Crear un inicializador específico
   ```kotlin
   class BasketballInitializer {
      fun initTeams(teamA: String, teamB: String): Pair<teamA, teamB> {
      // Misma lógica que InicializadorJuego, con las nuevas fábricas
      }
   }
   ```

4. Reutilizar `Team`, `Game` y `Printer` sin cambios**

Las clases `Team`, `Game` y `Printer` operan sobre `Player` de forma genérica — no conocen nada específico del fútbol. No necesitan modificarse para soportar el nuevo deporte.

### Qué cambiaría y qué no

| Componente            | Fútbol                              | Otro deporte |
|-----------------------|-------------------------------------|---|
| `Player` (abstracta)  | ✅ Sin cambios                       | ✅ Sin cambios |
| `Team`                | ✅ Sin cambios                       | ✅ Sin cambios |
| `Game`                | ✅ Sin cambios                       | ✅ Sin cambios |
| `Printer`             | ✅ Sin cambios                       | ✅ Sin cambios |
| Subclases de `Player` | `Defender`, `Midfielder`, `Striker` | Nuevas clases del deporte |
| Fábricas              | Específicas de fútbol               | Nuevas fábricas del deporte |
| Inicializador         | `GameInitializer`                   | Nuevo inicializador |
| Nombres               | Futbolistas                         | Jugadores del deporte |

---

# 7 Observaciones sobre Kotlin

## `object`

En Kotlin, `object` permite declarar un singleton — una clase que tiene exactamente
una sola instancia en todo el programa. No es necesario instanciarlo con `new` ni
gestionar su ciclo de vida manualmente.

En este proyecto se usó para centralizar constantes compartidas entre las fábricas:
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

Un `companion object` es un objeto vinculado a una clase específica. Funciona como
el equivalente a los miembros `static` de Java, pero con la sintaxis idiomática de Kotlin.

En este proyecto se usó en `Game` para definir los identificadores de turno:
```kotlin
class Game(...) {
    companion object {
        const val TEAM_A = 0
        const val TEAM_B = 1
    }
}
```

Al ser constantes del companion object, se puede acceder a ellas directamente
como `Game.TEAM_A` sin necesidad de instanciar la clase.

---

## Rangos (`IntRange`)

Kotlin permite expresar rangos de valores de forma natural usando el operador `..`.
El resultado es un `IntRange` que puede usarse para generar valores aleatorios,
iterar, o verificar si un valor está dentro del rango.

En este proyecto los rangos se usaron para definir los límites de los atributos
de cada posición y generar stats aleatorias:
```kotlin
val low    = 20..45
val medium = 40..60
val high   = 55..90

val defense = Ranges.high.random()  // número aleatorio entre 55 y 90
val attack  = Ranges.low.random()   // número aleatorio entre 20 y 45
```

Esto garantiza que cada tipo de jugador tenga stats coherentes con su posición,
sin necesidad de lógica adicional de validación.

---

## `typealias`

`typealias` permite asignar un nombre alternativo a un tipo existente. No crea
un tipo nuevo, sino una forma más expresiva y legible de referirse a uno ya existente.

En este proyecto se usó para representar el concepto de equipo:
```kotlin
typealias Team = MutableList
```

Sin el alias, las firmas de métodos serían más verbosas y menos expresivas:
```kotlin
// Sin typealias
fun createTeam(size: Int): MutableList

// Con typealias
fun createTeam(size: Int): Team
```

## `when`

`when` es la alternativa de Kotlin a `switch` en Java, pero mucho más limpia.
Puede usarse como expresión (retornando un valor) o como sentencia, y admite
condiciones arbitrarias en cada rama.

```kotlin
val message = when {
    pointsA > pointsB -> "🏆  ¡${teamAName} gana el partido!"
    pointsB > pointsA -> "🏆  ¡${teamBName} gana el partido!"
    else              -> "🤝  ¡Empate!"
}
```

Al usarlo como expresión, el resultado se asigna directamente a `message`,
eliminando la necesidad de una variable mutable y múltiples asignaciones.
