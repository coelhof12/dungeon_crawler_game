# Dungeon Crawler Game with Procedural Generation ⚔️

![Project Cover](https://raw.githubusercontent.com/coelhof12/dungeon_crawler_game/main/assets/images/Repo_Cover.jpg)

## The Concept 💡

This project is a 2D top-down dungeon crawler where the player explores procedurally generated dungeons, battles enemies, and seeks an exit. The dungeon layout is randomly generated using Depth-First Search (DFS), ensuring each playthrough is unique. The game is built using Java, and we will soon integrate graphics via the SimpleGraphics library. Currently, the game runs in a console environment, but it will evolve with additional features like enhanced combat, inventory management, and sound.

## Current Features 🛠️

- ***Procedural Dungeon Generation:*** The dungeon layout is generated using a Depth-First Search (DFS) algorithm. This ensures the dungeon layout is unique every time.
- ***Basic Player and Enemy Movement:*** The player and enemies move on the dungeon grid. Player movement is controlled by keyboard input (```w```, ```a```, ```s```, ```d```), and enemies move randomly after each player turn.
- ***Console-Based Interface:*** The game is currently played through the console, with the dungeon, player, and enemies represented using ASCII characters.
- ***High Score System:*** Player progress, such as levels completed and enemies defeated, is tracked and stored even after the game is closed.
- ***Combat System:*** The player can attack enemies if they occupy the same tile. Once attacked, enemies are removed from the game.

## Features In Progress and Upcoming 🚧

- ***Graphics Integration:*** We'll be integrating SimpleGraphics to display the game visually beyond the console. Players will soon see the dungeon, enemies, and characters in a more interactive and aesthetic environment;
- ***Improved Combat System:*** The current system instantly removes enemies upon contact. We are adding health points (HP) and attack mechanics, so both players and enemies can take damage over time;
- ***Inventory System:*** A system where players can collect, manage, and use items like potions, weapons, and armor;
- ***High Scores & Persistence:*** We will implement a high-score system that tracks progress across multiple playthroughs, using file-based storage to retain data after closing the game;
- ***Sound Effects and Music:*** Background music and sound effects for key events like movement, attacks, and winning will soon be part of the experience;
- ***Cheat Codes:*** Fun cheat codes like invincibility or speed boosts will be added to provide a playful twist;
- ***Enhanced Enemy AI:*** Enemies will soon have smarter movement, possibly even tracking or targeting the player instead of moving randomly.

## Game Mechanics 🎮

- ***Player Movement:*** The player moves through the dungeon using ```w```, ```a```, ```s```, ```d``` for directional control;
- ***Enemies:*** Randomly spawned enemies populate the dungeon, and more challenging enemies will appear as the player progresses;
- ***Procedural Generation:*** Each dungeon is procedurally generated upon game start, ensuring a fresh experience every time;
- ***Victory Condition:*** The game is won when the player finds the exit, marked by an ```X``` on the map.

## Technologies Used 🔧

- ***Java:*** Core language for building the game logic;
- ***SimpleGraphics Library (planned):*** Will be used for rendering the game graphically.
- ***Java I/O (planed):*** Used to handle high score persistence.;
- **Design Patterns (planned):** Strategy and Factory patterns will be incorporated to make the code more modular and maintainable.

## Possible Future Enhancments 🚀

- ***Multiplayer Mode:*** Adding support for two players to explore the dungeon together;
- ***More Complex Enemy AI:*** Enemies with unique attack patterns and behavior;
- ***Power-ups:*** Adding more variety to the items players can collect, such as temporary invincibility or speed boosts;
- ***Additional Game Modes:*** Introducing new challenges like time limits or endless dungeon modes for high replayability.

## How to Run 📝

1. Clone the repository;
2. Compile and run the Main.java class from the command line or your preferred IDE;
3. Move your player using ```W``` (UP), ```A``` (LEFT), ```S``` (DOWN) and ```D``` (RIGHT);
4. Find the exist (```X```) and avoid or defeat enemies (```E```).

Stay tunned for the integration of graphics that will make this game even more exciting!
