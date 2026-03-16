# football-game

## 1. Objetivo del proyecto
Simular un partido de fútbol modelado como un juego de cartas, donde cada jugador representa una carta con valores de ataque y defensa. El proyecto aplica los principios fundamentales de la Programación Orientada a Objetos (POO) en Kotlin: abstracción, herencia, polimorfismo y encapsulamiento, dentro de una arquitectura modular y extensible.

```
src/
├── controller/
│   ├── Game.kt                 # Orquesta la partida completa
│   └── GameInitializer.kt      # Construye los dos equipos aleatorios
├── model/
│   ├── Player.kt               # Clase abstracta base
│   ├── Defender.kt             # Subclase: mayor defensa que ataque
│   ├── Midfielder.kt           # Subclase: atributos equilibrados
│   ├── Striker.kt              # Subclase: mayor ataque que defensa
│   ├── Team.kt                 # Agrupa jugadores y expone operaciones de juego
│   ├── PlayerNames.kt          # Banco de nombres de futbolistas por posición
│   ├── Ranges.kt               # Rangos de atributos por posición
│   ├── PlayersFactory.kt       # Interface base (fun interface)
│   ├── DefendersFactory.kt     # Crea defensas con stats aleatorias válidas
│   ├── MidfielderFactory.kt    # Crea mediocampistas equilibrados
│   ├── StrikersFactory.kt      # Crea delanteros con stats aleatorias válidas
│   └── RandomPlayerFactory.kt  # Delega a una fábrica al azar
├── view/
│   └── Printer.kt              # Toda la salida en consola
└── Main.kt                     # Punto de entrada
```

### Descripción de cada componente

**`controller/`** — contiene la lógica de control del partido. `Game` orquesta los turnos, solicita la selección del atacante al usuario y registra cada resultado. `GameInitializer` separa la construcción de equipos de la lógica del partido, garantizando al menos un jugador de cada tipo por equipo.

**`model/Player` (abstracta)** — define las propiedades comunes (`nombre`, `ataque`, `defensa`, `disponible`) y el contrato `esValido()` que cada posición implementa con sus propias reglas.

**Subclases de `Player`** — `Defender`, `Midfielder` y `Striker` validan sus atributos en el bloque `init` usando `require`, garantizando que un objeto inválido nunca pueda ser creado.

**`model/Team`** — mantiene una única lista interna de jugadores y expone métodos para agregar jugadores, escoger atacantes (por índice del usuario) y defensores (al azar), y verificar si el equipo cumple las reglas del juego.

**`model/PlayerNames`** — centraliza los bancos de nombres de futbolistas reales separados por posición, evitando repeticiones dentro del mismo equipo.

**`model/Ranges`** — objeto singleton con los rangos de atributos por posición (`bajo`, `medio`, `alto`), eliminando números mágicos dispersos en las fábricas.

**Patrón Factory** — `PlayersFactory` es una `fun interface` implementada por cuatro clases. Cada fábrica específica genera jugadores con stats aleatorias que respetan las reglas de su posición. `RandomPlayerFactory` delega a una de las tres fábricas concretas elegida al azar.

**`view/Printer`** — única responsable de toda la salida en consola. `Game` no imprime nada directamente, solo delega.

# 3. Reglas del juego

Cada equipo tiene entre 5 y 10 jugadores, con al menos 1 defensa, 1 mediocampista y 1 delantero.

El partido se juega por turnos alternados: primero ataca el Equipo A, luego el Equipo B.

En cada turno, el equipo atacante elige un delantero o mediocampista disponible. El defensor se elige al azar del equipo contrario.

Gana el enfrentamiento si ataque del atacante > defensa del defensor. El equipo atacante suma un punto.

Un atacante solo puede usarse una vez. El juego termina cuando ningún equipo tiene atacantes disponibles.

Gana el equipo con más puntos. En caso de igualdad, es empate.

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

  🟦 Equipo A
── Equipo: Equipo A (7 jugadores) ──
  [0] ✅ Defender | Piqué | ATK: 22  DEF: 90
  [1] ✅ Midfielder | Valverde | ATK: 51  DEF: 50
  [2] ✅ Striker | Vinicius | ATK: 88  DEF: 29
  [3] ✅ Striker | Lautaro | ATK: 66  DEF: 34
  [4] ✅ Striker | Griezmann | ATK: 65  DEF: 41
  [5] ✅ Striker | Haaland | ATK: 88  DEF: 34
  [6] ✅ Defender | Skriniar | ATK: 40  DEF: 81


  🟦 Equipo B
── Equipo: Equipo B (7 jugadores) ──
  [0] ✅ Defender | Dias | ATK: 26  DEF: 72
  [1] ✅ Midfielder | Modric | ATK: 60  DEF: 57
  [2] ✅ Striker | Vinicius | ATK: 78  DEF: 36
  [3] ✅ Midfielder | Pedri | ATK: 45  DEF: 53
  [4] ✅ Striker | Mbappé | ATK: 82  DEF: 42
  [5] ✅ Defender | Koulibaly | ATK: 21  DEF: 65
  [6] ✅ Defender | Van Dijk | ATK: 39  DEF: 89


¡Comienza el partido!
──────────────────────────────────────────

  Turno 1 — Ataca: Equipo A
  Marcador → 0 : 0

  🟦 Equipo A
── Equipo: Equipo A (7 jugadores) ──
  [0] ✅ Defender | Piqué | ATK: 22  DEF: 90
  [1] ✅ Midfielder | Valverde | ATK: 51  DEF: 50
  [2] ✅ Striker | Vinicius | ATK: 88  DEF: 29
  [3] ✅ Striker | Lautaro | ATK: 66  DEF: 34
  [4] ✅ Striker | Griezmann | ATK: 65  DEF: 41
  [5] ✅ Striker | Haaland | ATK: 88  DEF: 34
  [6] ✅ Defender | Skriniar | ATK: 40  DEF: 81

  Elige un atacante: 0
Selección inválida. Intenta de nuevo.
  Elige un atacante: 2

 Vinicius (ATK 88)
 vs Dias (DEF 72)
  ✅  ¡Punto para Equipo A!
──────────────────────────────────────────

  Turno 2 — Ataca: Equipo B
  Marcador → 1 : 0

  🟦 Equipo B
── Equipo: Equipo B (7 jugadores) ──
  [0] ✅ Defender | Dias | ATK: 26  DEF: 72
  [1] ✅ Midfielder | Modric | ATK: 60  DEF: 57
  [2] ✅ Striker | Vinicius | ATK: 78  DEF: 36
  [3] ✅ Midfielder | Pedri | ATK: 45  DEF: 53
  [4] ✅ Striker | Mbappé | ATK: 82  DEF: 42
  [5] ✅ Defender | Koulibaly | ATK: 21  DEF: 65
  [6] ✅ Defender | Van Dijk | ATK: 39  DEF: 89

  Elige un atacante: 4

 Mbappé (ATK 82)
 vs Skriniar (DEF 81)
  ✅  ¡Punto para Equipo B!
──────────────────────────────────────────

  Turno 3 — Ataca: Equipo A
  Marcador → 1 : 1

  🟦 Equipo A
── Equipo: Equipo A (7 jugadores) ──
  [0] ⛔ Defender | Piqué | ATK: 22  DEF: 90
  [1] ✅ Midfielder | Valverde | ATK: 51  DEF: 50
  [2] ⛔ Striker | Vinicius | ATK: 88  DEF: 29
  [3] ✅ Striker | Lautaro | ATK: 66  DEF: 34
  [4] ✅ Striker | Griezmann | ATK: 65  DEF: 41
  [5] ✅ Striker | Haaland | ATK: 88  DEF: 34
  [6] ✅ Defender | Skriniar | ATK: 40  DEF: 81

  Elige un atacante: 4

 Griezmann (ATK 65)
 vs Van Dijk (DEF 89)
  ⛔  Sin punto.
──────────────────────────────────────────

  Turno 4 — Ataca: Equipo B
  Marcador → 1 : 1

  🟦 Equipo B
── Equipo: Equipo B (7 jugadores) ──
  [0] ✅ Defender | Dias | ATK: 26  DEF: 72
  [1] ✅ Midfielder | Modric | ATK: 60  DEF: 57
  [2] ✅ Striker | Vinicius | ATK: 78  DEF: 36
  [3] ✅ Midfielder | Pedri | ATK: 45  DEF: 53
  [4] ⛔ Striker | Mbappé | ATK: 82  DEF: 42
  [5] ✅ Defender | Koulibaly | ATK: 21  DEF: 65
  [6] ✅ Defender | Van Dijk | ATK: 39  DEF: 89

  Elige un atacante: 1

 Modric (ATK 60)
 vs Skriniar (DEF 81)
  ⛔  Sin punto.
──────────────────────────────────────────

  Turno 5 — Ataca: Equipo A
  Marcador → 1 : 1

  🟦 Equipo A
── Equipo: Equipo A (7 jugadores) ──
  [0] ⛔ Defender | Piqué | ATK: 22  DEF: 90
  [1] ✅ Midfielder | Valverde | ATK: 51  DEF: 50
  [2] ⛔ Striker | Vinicius | ATK: 88  DEF: 29
  [3] ✅ Striker | Lautaro | ATK: 66  DEF: 34
  [4] ⛔ Striker | Griezmann | ATK: 65  DEF: 41
  [5] ✅ Striker | Haaland | ATK: 88  DEF: 34
  [6] ✅ Defender | Skriniar | ATK: 40  DEF: 81

  Elige un atacante: 1

 Valverde (ATK 51)
 vs Van Dijk (DEF 89)
  ⛔  Sin punto.
──────────────────────────────────────────

  Turno 6 — Ataca: Equipo B
  Marcador → 1 : 1

  🟦 Equipo B
── Equipo: Equipo B (7 jugadores) ──
  [0] ✅ Defender | Dias | ATK: 26  DEF: 72
  [1] ⛔ Midfielder | Modric | ATK: 60  DEF: 57
  [2] ✅ Striker | Vinicius | ATK: 78  DEF: 36
  [3] ✅ Midfielder | Pedri | ATK: 45  DEF: 53
  [4] ⛔ Striker | Mbappé | ATK: 82  DEF: 42
  [5] ✅ Defender | Koulibaly | ATK: 21  DEF: 65
  [6] ✅ Defender | Van Dijk | ATK: 39  DEF: 89

  Elige un atacante: 3

 Pedri (ATK 45)
 vs Piqué (DEF 90)
  ⛔  Sin punto.
──────────────────────────────────────────

  Turno 7 — Ataca: Equipo A
  Marcador → 1 : 1

  🟦 Equipo A
── Equipo: Equipo A (7 jugadores) ──
  [0] ⛔ Defender | Piqué | ATK: 22  DEF: 90
  [1] ⛔ Midfielder | Valverde | ATK: 51  DEF: 50
  [2] ⛔ Striker | Vinicius | ATK: 88  DEF: 29
  [3] ✅ Striker | Lautaro | ATK: 66  DEF: 34
  [4] ⛔ Striker | Griezmann | ATK: 65  DEF: 41
  [5] ✅ Striker | Haaland | ATK: 88  DEF: 34
  [6] ✅ Defender | Skriniar | ATK: 40  DEF: 81

  Elige un atacante: 
Selección inválida. Intenta de nuevo.
  Elige un atacante: 5

 Haaland (ATK 88)
 vs Dias (DEF 72)
  ✅  ¡Punto para Equipo A!
──────────────────────────────────────────

  Turno 8 — Ataca: Equipo B
  Marcador → 2 : 1

  🟦 Equipo B
── Equipo: Equipo B (7 jugadores) ──
  [0] ✅ Defender | Dias | ATK: 26  DEF: 72
  [1] ⛔ Midfielder | Modric | ATK: 60  DEF: 57
  [2] ✅ Striker | Vinicius | ATK: 78  DEF: 36
  [3] ⛔ Midfielder | Pedri | ATK: 45  DEF: 53
  [4] ⛔ Striker | Mbappé | ATK: 82  DEF: 42
  [5] ✅ Defender | Koulibaly | ATK: 21  DEF: 65
  [6] ✅ Defender | Van Dijk | ATK: 39  DEF: 89

  Elige un atacante: 2

 Vinicius (ATK 78)
 vs Piqué (DEF 90)
  ⛔  Sin punto.
──────────────────────────────────────────

========================================
=           RESULTADO FINAL            =
========================================

  Equipo A             2 pts
  Equipo B             1 pts

  🏆  ¡Equipo A gana el partido!


Process finished with exit code 0
```

## 6. Extra — Diseño extensible para otros deportes

La arquitectura del proyecto fue diseñada para adaptarse fácilmente a otros deportes sin modificar las clases existentes, siguiendo el principio Abierto/Cerrado.

Cómo extender el proyecto

Para simular otro deporte (por ejemplo, baloncesto), solo necesitas:

1. Crear las nuevas subclases de Jugador
    ```kotlin
    class Base(nombre: String, ataque: Int, defensa: Int) : Jugador(nombre, ataque, defensa) {
        init { require(defensa > ataque) { "Base debe tener defensa > ataque" } }
        override fun esValido() = defensa > ataque
    }
    
    class Alero(nombre: String, ataque: Int, defensa: Int) : Jugador(nombre, ataque, defensa) {
        init { require(kotlin.math.abs(ataque - defensa) <= 20) { "Alero debe ser equilibrado" } }
        override fun esValido() = kotlin.math.abs(ataque - defensa) <= 20
    }
    
    class Pivot(nombre: String, ataque: Int, defensa: Int) : Jugador(nombre, ataque, defensa) {
        init { require(ataque > defensa) { "Pivot debe tener ataque > defensa" } }
        override fun esValido() = ataque > defensa
    }
   ```

2. Crear las nuevas fábricas
   ```kotlin
   class FabricaBases : FabricaJugadores {
       override fun crearJugador() = Base(
       nombre  = NombresBasquetbol.bases.random(),
       ataque  = Rangos.bajo.random(),
       defensa = Rangos.alto.random()
       )
   }
   ```
3. Crear un inicializador específico
   ```kotlin
   class InicializadorBasquetbol {
      fun inicializar(nombreA: String, nombreB: String): Pair<Equipo, Equipo> {
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