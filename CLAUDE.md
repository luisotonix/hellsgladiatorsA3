# CLAUDE.md - Hell's Gladiators Codebase Guide

**Last Updated:** 2025-11-22
**Project:** Hell's Gladiators - A turn-based gladiator combat game
**Language:** Java (JDK 8+)
**Purpose:** AI Assistant Reference for Development

---

## Table of Contents
1. [Project Overview](#project-overview)
2. [Architecture](#architecture)
3. [Directory Structure](#directory-structure)
4. [Core Components](#core-components)
5. [Design Patterns & Conventions](#design-patterns--conventions)
6. [Development Workflow](#development-workflow)
7. [Common Tasks](#common-tasks)
8. [Code Conventions](#code-conventions)
9. [Data Management](#data-management)
10. [Important Implementation Details](#important-implementation-details)
11. [Git Workflow](#git-workflow)

---

## Project Overview

**Hell's Gladiators** is a console-based, turn-based combat game developed as an academic project for Object-Oriented Programming (1st semester). The game simulates gladiatorial combat in a Roman Colosseum setting.

### Key Features
- 5 distinct gladiator classes with unique stats and weapons
- Turn-based combat system with strategic choices
- Status effects (paralysis, bleeding, disarm)
- AI-controlled opponents
- Ranking and statistics persistence
- Random arena events
- Combat animations and visual feedback

### Academic Context
- **Team Size:** 5 students
- **Development Period:** 12 days (Nov 11-23, 2024)
- **Focus:** Object-Oriented Programming principles
- **Platform:** Console/Terminal interface

---

## Architecture

### Architectural Pattern
The codebase follows a **modular object-oriented design** with clear separation of concerns:

```
┌─────────────────────────────────────────────────┐
│              Main.java (Entry Point)             │
└─────────────────┬───────────────────────────────┘
                  │
        ┌─────────▼─────────┐
        │ InterfaceConsole  │ (UI Layer)
        └─────────┬─────────┘
                  │
    ┌─────────────┼─────────────┐
    │             │             │
┌───▼────┐   ┌───▼────┐   ┌───▼────────┐
│ Arena  │   │Ranking │   │ Gladiador  │ (Domain Layer)
└───┬────┘   └───┬────┘   └───┬────────┘
    │            │            │
    │     ┌──────▼────────────▼──────┐
    │     │   SistemaCombate         │ (Game Logic)
    │     └──────┬────────────────────┘
    │            │
    └────────────┼──────────┬──────────┬─────────────┐
                 │          │          │             │
          ┌──────▼───┐  ┌──▼──────┐ ┌─▼─────────┐ ┌─▼──────────┐
          │Calculador│  │Gerenc.  │ │StatusBat. │ │ Plateia    │
          │Dano      │  │Turnos   │ └───────────┘ └────────────┘
          └──────────┘  └─────────┘
                 │
         ┌───────┴────────┐
         │                │
    ┌────▼─────┐    ┌────▼─────────┐
    │   Arma   │    │EventoAleatorio│
    └──────────┘    └──────────────┘
```

### Layer Responsibilities

1. **Entry Point (`Main.java`)**
   - Application initialization
   - Main game loop

2. **UI Layer (`InterfaceConsole.java`)**
   - Menu display and navigation
   - User input handling
   - Game flow coordination

3. **Domain Layer**
   - `Gladiador.java`: Core gladiator entity
   - `Arma.java`: Weapon definitions and special abilities
   - `ConfiguracaoClasse.java`: Class configurations (stats, weapons)
   - `StatusBatalha.java`: Battle status effects management

4. **Game Logic Layer**
   - `SistemaCombate.java`: Battle orchestration
   - `CalculadorDano.java`: Damage calculation and special abilities
   - `GerenciadorTurnos.java`: Turn order management
   - `Arena.java`: Battle environment
   - `EventoAleatorio.java`: Random events during combat
   - `Plateia.java`: Audience reactions

5. **AI Layer**
   - `Oponente.java`: AI opponent generation
   - (Previously: `IAOponente.java`, `EstrategiaMatchmaking.java` - removed)

6. **Persistence Layer**
   - `GerenciadorRanking.java`: Ranking system management
   - `GerenciadorArquivos.java`: File I/O operations
   - `Estatisticas.java`: Statistics data model
   - `HistoricoBatalha.java`: Battle history records

7. **Utility Layer**
   - `Aleatorio.java`: Random number generation utilities
   - `BarraVida.java`: ASCII health bar rendering
   - `AnimacaoCombate.java`: Visual combat effects

---

## Directory Structure

```
hellsgladiatorsA3/
├── src/                        # Source code (20 .java files)
│   ├── Main.java               # Entry point
│   ├── Gladiador.java          # Core gladiator class
│   ├── Arma.java               # Weapon system
│   ├── ConfiguracaoClasse.java # Class configurations
│   ├── StatusBatalha.java      # Status effects
│   ├── SistemaCombate.java     # Battle orchestration
│   ├── CalculadorDano.java     # Damage calculations
│   ├── GerenciadorTurnos.java  # Turn management
│   ├── Arena.java              # Battle environment
│   ├── EventoAleatorio.java    # Random events
│   ├── Plateia.java            # Audience system
│   ├── Oponente.java           # AI opponent
│   ├── GerenciadorRanking.java # Ranking system
│   ├── Estatisticas.java       # Statistics model
│   ├── HistoricoBatalha.java   # Battle history
│   ├── GerenciadorArquivos.java# File I/O
│   ├── InterfaceConsole.java   # UI/menus
│   ├── BarraVida.java          # Health bars
│   ├── AnimacaoCombate.java    # Combat animations
│   └── Aleatorio.java          # Random utilities
│
├── bin/                        # Compiled .class files
│
├── dados/                      # Persistent data
│   ├── ranking.txt             # Player rankings
│   └── historico.txt           # Battle history
│
├── docs/                       # Documentation
│   ├── Git e GitHub Para Leigos.docx
│   └── Jogo de Hell's Gladiator.docx
│
├── readme.md                   # Project README
└── CLAUDE.md                   # This file
```

---

## Core Components

### 1. Gladiador.java
**Responsibility:** Core gladiator entity with stats, weapons, and status management.

**Key Attributes:**
- `nome`: Gladiator's name
- `tipoClasse`: Class type (Tanque, Assassino, Bárbaro, Lanceiro, Arqueiro)
- `hp`: Current health points
- `hpMaximo`: Maximum health points
- `arma`: Weapon instance
- `armadura`: Armor value
- `reducaoDano`: Damage reduction (seems to overlap with armadura)
- `chanceCritico`: Critical hit chance
- `velocidadeAtaque`: Attack speed (determines turn order)
- `status`: Current battle status effects

**Key Methods:**
- `setGladiador(String nome, String tipoClasse, String nomeArma)`: Initialize gladiator
- `aplicarConfiguracao(int[] config)`: Apply class configuration
- `receberDano(int dano)`: Apply damage (armor already calculated)
- `estaVivo()`: Check if alive
- `restaurarParaNovaBatalha()`: Reset for new battle
- `quebrarArmadura()`: Set armor to 0

**Important:** The `receberDano()` method expects damage AFTER armor calculation. Armor is applied in `CalculadorDano.calcularDano()`.

---

### 2. ConfiguracaoClasse.java
**Responsibility:** Centralized class configuration definitions.

**Class Constants:**
```java
Tanque      // Heavy tank
Assassino   // Agile assassin
Bárbaro     // Versatile warrior
Lanceiro    // Spearman
Arqueiro    // Archer
```

**Configuration Array Format:**
```java
[HP, Redução Dano, Armadura, Chance Crítico, Velocidade Ataque]
// Velocidade: 0=Slow, 1=Normal, 2=Fast
```

**Class Configurations:**
- **Tanque:** `{10, 5, 3, 0, 0}` - Max HP, max armor, always last, no crits
- **Assassino:** `{7, 2, 0, 5, 2}` - Low HP, no armor, high speed
- **Bárbaro:** `{9, 3, 2, 2, 1}` - Balanced stats
- **Lanceiro:** `{9, 4, 1, 2, 1}` - High weapon damage, low armor
- **Arqueiro:** `{8, 2, 0, 3, 2}` - Low HP, ranged advantage

**Weapon Mapping:**
- Tanque → Machado (axe)
- Assassino → Rede e Adaga (net + dagger)
- Bárbaro → Espada (sword)
- Lanceiro → Lança (spear)
- Arqueiro → Arco (bow)

---

### 3. Arma.java
**Responsibility:** Weapon definitions and special abilities.

**Weapon Constants:**
```java
public static final String machado = "machado";
public static final String rede_adaga = "rede_adaga";
public static final String espada = "espada";
public static final String lanca = "lanca";
public static final String arco = "arco";
```

**Special Abilities:**
- **Machado:** 20% chance to break armor after 2 hits
- **Rede e Adaga:** 30% chance to paralyze opponent
- **Espada:** 20% chance for double attack, 20% chance bleeding
- **Lança:** 20% chance to disarm opponent
- **Arco:** Ignores armor completely, distance mechanics

---

### 4. SistemaCombate.java
**Responsibility:** Battle orchestration and turn management.

**Key Components:**
- `Gladiador jogador`: Player's gladiator
- `Gladiador oponente`: AI opponent
- `CalculadorDano calculador`: Damage calculator
- `GerenciadorTurnos gerenciadorTurnos`: Turn counter
- `Plateia plateia`: Audience reactions

**Battle Flow:**
```java
iniciarCombate() {
    while (both alive) {
        executarTurno()
        plateia.reagir()
    }
    exibirVencedor()
}

executarTurno() {
    1. Increment turn counter
    2. Display HP status
    3. Player chooses action (Attack/Defend/Dodge)
    4. Update status effects
    5. Determine attack order (by velocidadeAtaque)
    6. Execute attacks
    7. Apply bleeding damage
}
```

**Turn Order:** Determined by `velocidadeAtaque` (2=Fast, 1=Normal, 0=Slow)

---

### 5. CalculadorDano.java
**Responsibility:** Complex damage calculation with all modifiers.

**Damage Calculation Steps:**
1. Get base weapon damage
2. Check for critical hit (15% default chance)
3. Apply weapon special abilities
4. Apply armor reduction (unless ignored by weapon)
5. Apply status effect modifiers
6. Return final damage value

**Special Ability Processing:**
- Triggers weapon-specific effects
- Updates `StatusBatalha` for both combatants
- Handles armor penetration/reduction

**Important:** This class applies armor reduction BEFORE returning damage to `SistemaCombate`.

---

### 6. StatusBatalha.java
**Responsibility:** Manage temporary combat status effects.

**Status Effects:**
- `paralizado`: Cannot attack for N turns
- `sangrando`: Takes 1 damage per turn for N turns
- `desarmado`: Weapon disabled for N turns
- `contadorGolpesMachado`: Tracks axe hits for armor break

**Key Methods:**
- `setParalizado(int turnos)`: Apply paralysis
- `setSangrando(int turnos)`: Apply bleeding
- `setDesarmado(int turnos)`: Disarm target
- `atualizarStatus()`: Decrement turn counters
- `isParalizado()`, `isSangrando()`, `isDesarmado()`: Status checks

**Important:** Status durations are decremented each turn in `atualizarStatus()`.

---

### 7. InterfaceConsole.java
**Responsibility:** User interface and game flow.

**Key Methods:**
- `mostrarMenuPrincipal()`: Display main menu with loading animation
- `iniciarJogo()`: Handle class selection, naming, and battle loop
- `mostrarRegras()`: Display game rules
- `mostrarEstatisticas()`: Show player rankings

**Game Flow:**
```
Main Menu → Class Selection → Name Input → Battle(s) → Repeat/Exit
```

**Important:**
- Uses `Scanner` for input (not closed in `iniciarJogo()` to avoid breaking main loop)
- Supports multiple consecutive battles with same gladiator
- Restores gladiator HP/status between battles via `setGladiador()`

---

### 8. Arena.java
**Responsibility:** Battle environment and event coordination.

**Key Features:**
- Initiates combat via `SistemaCombate`
- Triggers random events via `EventoAleatorio`
- Manages combat environment state

---

### 9. GerenciadorArquivos.java
**Responsibility:** File I/O for persistence.

**File Structure:**
- **Location:** `dados/` directory
- **Files:**
  - `ranking.txt`: Player statistics
  - `historico.txt`: Battle history

**Key Methods:**
- `salvarRanking(ArrayList<Estatisticas>)`: Save rankings
- `carregarRanking()`: Load rankings
- `salvarHistorico(HistoricoBatalha)`: Append battle record
- `exibirHistorico()`: Display battle history

**File Format:**
- Uses `toString()` methods from `Estatisticas` and `HistoricoBatalha`
- One record per line
- Uses `fromString()` static method for deserialization

**Directory Management:** Auto-creates `dados/` directory if missing.

---

### 10. Oponente.java
**Responsibility:** AI opponent generation.

**Important Notes:**
- Randomly generates opponents from available classes
- Previously had `IAOponente.java` and `EstrategiaMatchmaking.java` for strategic counter-picking (removed in commit e4cb98c)
- Currently uses simpler random selection

---

## Design Patterns & Conventions

### 1. Object-Oriented Principles

**Encapsulation:**
- Private attributes with public getters/setters
- Example: `Gladiador` attributes are `protected`, accessed via getters

**Single Responsibility:**
- Each class has a focused purpose
- `CalculadorDano` only handles damage calculations
- `GerenciadorArquivos` only handles file I/O

**Separation of Concerns:**
- UI logic separated from game logic
- Data persistence isolated in manager classes

### 2. Configuration Pattern
`ConfiguracaoClasse.java` centralizes all class definitions, making balancing easy:
```java
// Easy to modify stats without touching Gladiador.java
int[] config = configuracao.getConfiguracao(tipoClasse);
```

### 3. Manager Pattern
Multiple "Gerenciador" classes manage specific domains:
- `GerenciadorRanking`: Rankings
- `GerenciadorArquivos`: File I/O
- `GerenciadorTurnos`: Turn counting

### 4. Status Effect Pattern
`StatusBatalha` acts as a state object attached to each gladiator, managing temporary effects with turn-based durations.

### 5. Factory-like Initialization
`setGladiador()` method acts as a factory, creating fully configured gladiators:
```java
gladiador.setGladiador(nome, tipoClasse, nomeArma);
// Automatically applies class config, creates weapon, initializes status
```

---

## Development Workflow

### Build and Run

**Compilation:**
```bash
# From project root
cd src
javac *.java -d ../bin

# Or compile all at once
javac src/*.java -d bin
```

**Execution:**
```bash
# From bin directory
cd bin
java Main

# Or from root
java -cp bin Main
```

**Clean:**
```bash
# Remove compiled files
rm bin/*.class

# Windows
del bin\*.class
```

### Development Environment
- **JDK:** Version 8 or higher
- **IDE:** Any Java IDE (IntelliJ IDEA, Eclipse, VS Code with Java extensions)
- **Terminal:** Required for game interaction

---

## Common Tasks

### Adding a New Gladiator Class

1. **Update `ConfiguracaoClasse.java`:**
```java
public static final String NovaClasse = "NovaClasse";

public int[] getConfiguracao(String tipoClasse) {
    case NovaClasse:
        return new int[]{HP, DanoBase, Armadura, ChanceCrit, Velocidade};
}

public String getArmaInicial(String tipoClasse) {
    case NovaClasse: return Arma.nova_arma;
}
```

2. **Update `Arma.java` (if new weapon):**
```java
public static final String nova_arma = "nova_arma";

public void configurarArma(String arma) {
    case nova_arma:
        this.nome = "Nova Arma";
        this.forcaBase = 4;
        this.habilidadeEspecial = "Descrição";
}
```

3. **Update `CalculadorDano.java` (if special ability):**
```java
// Add ability logic in calcularDano()
case Arma.nova_arma:
    if (chanceEspecial()) {
        // Apply special effect
    }
```

4. **Update `InterfaceConsole.java`:**
```java
// Add menu option in iniciarJogo()
System.out.println("6 - NovaClasse\n...");

case 6 -> {
    gladiador.setGladiador(nome, "NovaClasse", "nova_arma");
}
```

### Adding a New Weapon

1. **Define in `Arma.java`:**
```java
public static final String nova_arma = "nova_arma";
```

2. **Add configuration in `configurarArma()`:**
```java
case nova_arma:
    this.nome = "Display Name";
    this.forcaBase = X;
    this.habilidadeEspecial = "Description";
    this.ignoraArmadura = true/false;
```

3. **Implement special ability in `CalculadorDano.java`:**
```java
// In calcularDano() method
if (armaAtacante.getNome().equals(Arma.nova_arma)) {
    // Implement ability logic
}
```

### Adding a New Status Effect

1. **Add to `StatusBatalha.java`:**
```java
private int novoEfeito = 0;

public void setNovoEfeito(int turnos) {
    this.novoEfeito = turnos;
}

public boolean isNovoEfeito() {
    return novoEfeito > 0;
}

public void atualizarStatus() {
    // Add to existing method
    if (novoEfeito > 0) novoEfeito--;
}
```

2. **Trigger in `CalculadorDano.java`:**
```java
// When ability triggers
defensor.getStatus().setNovoEfeito(duracao);
```

3. **Apply effect in `SistemaCombate.java`:**
```java
// In executarTurno() or executarAtaque()
if (gladiador.getStatus().isNovoEfeito()) {
    // Apply effect consequences
}
```

### Modifying Combat Balance

**Adjust Class Stats:** Edit `ConfiguracaoClasse.getConfiguracao()`
**Adjust Weapon Damage:** Edit `Arma.configurarArma()`
**Adjust Critical Chance:** Modify base `chanceCritico` in class configs
**Adjust Ability Proc Rates:** Modify probability checks in `CalculadorDano.java`

### Adding Random Events

1. **Define in `EventoAleatorio.java`:**
```java
// Add new event logic
```

2. **Trigger in `Arena.java`:**
```java
// During battle initialization or turn processing
```

---

## Code Conventions

### Naming Conventions

**Classes:** PascalCase
```java
SistemaCombate, GerenciadorRanking, CalculadorDano
```

**Methods:** camelCase
```java
calcularDano(), exibirVencedor(), estaVivo()
```

**Variables:** camelCase
```java
int hpMaximo, boolean paralizado, String tipoClasse
```

**Constants:** UPPER_SNAKE_CASE (for file paths) or camelCase (for class names)
```java
private static final String ARQUIVO_RANKING = "dados/ranking.txt";
public static final String Tanque = "Tanque";
```

### Portuguese Language
- All code, variables, and comments are in **Brazilian Portuguese**
- Exception: Standard Java keywords and some technical terms

### Access Modifiers
- **Gladiador attributes:** `protected` (allows potential subclassing)
- **Manager classes:** `private` attributes with `public` methods
- **Configuration constants:** `public static final`

### Constructor Patterns
- Many classes use default constructors
- `Gladiador` uses `setGladiador()` method instead of parameterized constructor
- This pattern allows reuse of same object for multiple battles

### Scanner Management
- **Important:** Scanners wrapping `System.in` should NOT be closed in sub-methods
- Only close in `Main.java` after program exit
- `InterfaceConsole.iniciarJogo()` does NOT close its scanner

### Exception Handling
- `InterruptedException` thrown by `InterfaceConsole.mostrarMenuPrincipal()` for Thread.sleep()
- File I/O exceptions caught and printed in `GerenciadorArquivos`
- Generally uses print statements rather than sophisticated logging

---

## Data Management

### File Persistence

**Location:** `dados/` directory (auto-created)

**Files:**
1. **`ranking.txt`**
   - Format: One `Estatisticas` object per line (via `toString()`)
   - Loaded into `ArrayList<Estatisticas>` on startup
   - Overwritten on each save (full rewrite)

2. **`historico.txt`**
   - Format: One `HistoricoBatalha` object per line
   - Append-only (never overwritten)
   - Grows with each battle

**Serialization:**
- Uses custom `toString()` methods for serialization
- Uses static `fromString()` methods for deserialization
- **Important:** Any changes to data models must update both methods

### Ranking System

**Managed by:** `GerenciadorRanking.java`

**Data Model:** `Estatisticas.java`
- Player name
- Class type
- Wins/losses
- Kill count
- Win streak
- Win rate

**Display:** Top 10 players via `exibirTop10()`

### Battle History

**Managed by:** `GerenciadorArquivos.java`

**Data Model:** `HistoricoBatalha.java`
- Battle participants
- Winner
- Timestamp
- Combat stats

---

## Important Implementation Details

### Armor Calculation GOTCHA
**Critical:** Armor is applied in `CalculadorDano.calcularDano()`, NOT in `Gladiador.receberDano()`.

```java
// ✅ CORRECT: CalculadorDano.java
int dano = forcaArma - defensor.getArmadura();
if (dano < 0) dano = 0;
return dano;

// ✅ CORRECT: Gladiador.java
public void receberDano(int dano) {
    hp -= dano;  // Armor already applied
}
```

**Why this matters:** If you modify damage calculation, ensure armor is only applied once.

### Turn Order Logic
Turn order determined by `velocidadeAtaque`:
- `2` = Fast (Assassino, Arqueiro) - always first
- `1` = Normal (Bárbaro, Lanceiro) - middle
- `0` = Slow (Tanque) - always last

Implemented in `GerenciadorTurnos.determinarOrdem()`.

### Status Effect Timing
Status effects are:
1. Updated at START of turn (`atualizarStatus()`)
2. Checked BEFORE attacks (`isParalizado()`)
3. Applied AFTER damage calculation (bleeding damage)

**Order matters** - paralysis prevents attacking THAT turn.

### Special Ability Probability
Most weapon abilities use ~20-30% activation chance:
```java
if (Aleatorio.chance(20)) {  // 20% chance
    // Trigger ability
}
```

Check `Aleatorio.java` for implementation.

### Gladiator Restoration Between Battles
To reset a gladiator for another battle:
```java
// Current implementation (reinitializes completely)
gladiador.setGladiador(nome, tipoClasse, armaOriginal);

// Alternative (if restaurarParaNovaBatalha() is implemented)
gladiador.restaurarParaNovaBatalha();
```

The `restaurarParaNovaBatalha()` method exists but may not be fully utilized.

### HP Display
HP is displayed using:
- `BarraVida.java`: ASCII health bar rendering
- Direct HP values in console

### Combat Animation
`AnimacaoCombate.java` provides visual feedback during combat. Uses `Thread.sleep()` for timing effects.

---

## Git Workflow

### Branch Strategy
- **Main branch:** Stable production code
- **Feature branches:** Individual features (follow pattern: `feature/FeatureName`)
- **Current work:** Branch `claude/claude-md-mi9pfmiv0kbuxsxv-01WUJyJ4KnfkjLQ5Yr2rrrG1`

### Commit Message Pattern
Recent commits show Portuguese messages with descriptive details:
```
✅ Good: "Implementação do sistema de combate e gerenciamento de gladiadores..."
✅ Good: "correções de sintaxe"
❌ Avoid: "teste", "correção" (too vague)
```

### Recent Changes (from git log)
- **335cc51:** Major combat system implementation
- **3d31c1d:** Statistics display and turn info updates
- **e4cb98c:** Removed `EstrategiaMatchmaking.java` (simplified AI)
- **ac957df:** Various fixes ("consertos")
- **9e0b55a:** Refactored `Arma` class parameterization

### Compilation Artifacts
- `.class` files compiled to `bin/` directory
- **Important:** `.class` files are tracked in git (visible in commits)
- Consider adding `*.class` to `.gitignore` for cleaner commits

---

## Common Issues & Solutions

### Issue: Scanner Closed Error
**Symptom:** `java.util.NoSuchElementException` when returning to main menu
**Cause:** Scanner was closed in sub-method
**Solution:** Never close Scanner wrapping `System.in` except in `Main.java`

### Issue: Armor Applied Twice
**Symptom:** Damage is too low, gladiators nearly invincible
**Cause:** Armor subtracted in both `CalculadorDano` AND `receberDano`
**Solution:** Only apply armor in `CalculadorDano.calcularDano()`

### Issue: Status Effects Not Expiring
**Symptom:** Paralysis/bleeding lasts forever
**Cause:** `atualizarStatus()` not called each turn
**Solution:** Ensure `SistemaCombate.executarTurno()` calls `atualizarStatus()` for both gladiators

### Issue: Special Abilities Not Triggering
**Symptom:** Weapon abilities never activate
**Cause:** Missing implementation in `CalculadorDano.calcularDano()`
**Solution:** Add weapon-specific logic in damage calculation method

### Issue: File Persistence Not Working
**Symptom:** Rankings/history not saving between sessions
**Cause:** `dados/` directory doesn't exist
**Solution:** `GerenciadorArquivos` constructor auto-creates it, but verify permissions

---

## Testing Guidelines

### Manual Testing Checklist
- [ ] Each class can be selected and played
- [ ] Each weapon's special ability triggers occasionally
- [ ] Status effects (paralysis, bleeding, disarm) work correctly
- [ ] Armor reduction applies properly (not twice)
- [ ] Turn order respects velocidade (Fast → Normal → Slow)
- [ ] HP doesn't go negative
- [ ] Rankings save and load correctly
- [ ] Battle history persists
- [ ] Multiple battles in a row work without errors
- [ ] Menu navigation functions correctly

### Balance Testing
- [ ] No class is overwhelmingly overpowered
- [ ] Combat typically lasts 5-15 turns
- [ ] Critical hits feel impactful but not game-breaking
- [ ] Armor values provide meaningful protection

---

## Future Enhancement Ideas

Based on README.md "Melhorias Futuras":
- [ ] More gladiator classes (Dimachaerus, Provocator)
- [ ] Customizable equipment system
- [ ] Campaign mode with progression
- [ ] Local multiplayer
- [ ] GUI interface
- [ ] Achievement system
- [ ] Sound and music
- [ ] Adjustable AI difficulty
- [ ] Tournament mode with multiple opponents

---

## Key Files for Different Tasks

### UI/UX Changes
- `InterfaceConsole.java`: Menus, prompts, game flow
- `BarraVida.java`: Health bar display
- `AnimacaoCombate.java`: Combat visual effects

### Game Balance
- `ConfiguracaoClasse.java`: Class stats
- `Arma.java`: Weapon damage and abilities
- `CalculadorDano.java`: Damage formulas

### New Features
- `SistemaCombate.java`: Combat mechanics
- `Arena.java`: Battle environment
- `EventoAleatorio.java`: Random events

### Data & Persistence
- `GerenciadorArquivos.java`: File I/O
- `GerenciadorRanking.java`: Ranking logic
- `Estatisticas.java`, `HistoricoBatalha.java`: Data models

### AI Behavior
- `Oponente.java`: Opponent generation

---

## Summary of Key Principles

1. **Armor is applied in `CalculadorDano`, not in `receberDano()`**
2. **Never close Scanner(System.in) except in Main.java**
3. **Use `setGladiador()` to reset gladiators between battles**
4. **All text is in Portuguese (variable names, UI, comments)**
5. **File persistence uses `toString()`/`fromString()` pattern**
6. **Status effects require `atualizarStatus()` to expire**
7. **Turn order is determined by `velocidadeAtaque` (0/1/2)**
8. **Special abilities are handled in `CalculadorDano.calcularDano()`**
9. **Configuration is centralized in `ConfiguracaoClasse`**
10. **Separation of concerns: UI → Game Logic → Data Persistence**

---

## Contact & Contribution

This project was developed as an academic team project. For questions or improvements:
1. Review this CLAUDE.md file first
2. Check the detailed README.md for game mechanics
3. Examine the specific class implementation
4. Test changes thoroughly before committing

**Academic Discipline:** Programação Orientada a Objetos
**Development Period:** Nov 11-23, 2024

---

**End of CLAUDE.md** - Last Updated: 2025-11-22

*"Ave, Caesar! Morituri te salutant!"*
