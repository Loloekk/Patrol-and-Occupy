# Patrol and Occupy

**Patrol and Occupy** is a dynamic local multiplayer tank combat game designed for 2, 3, or 4 players. Navigate a tactical battlefield, destroy obstacles, deploy explosives, and capture strategic bases to dominate your opponents.

## 🎮 Game Overview

In **Patrol and Occupy**, players command tanks on a map filled with spawn points, destructible and indestructible obstacles, and strategic bases.

### Objective
The goal is to **occupy as many bases as possible** before the round timer expires. To capture a base, simply drive your tank onto its territory. Be alert—bases can be recaptured by opponents at any time!

### Combat & Mechanics
* **Engagement:** Fire projectiles to disable enemy tanks. If hit, a tank is destroyed, teleported to a spawn point, and sidelined for a few seconds.
* **Tactical Explosives:** Deploy dynamites across the map. Shooting a dynamite triggers a massive explosion that destroys all vehicles and brick walls in its vicinity.
* **Ammunition:** Tanks are equipped with a magazine that automatically regenerates over time.
* **Customization:** Most gameplay parameters can be adjusted in the settings menu.

### Terrain & Obstacles
| Obstacle | Movement | Projectiles | Interaction |
| :--- | :---: | :---: | :--- |
| **Rocks** | Blocked | Blocked | Indestructible |
| **Trees** | Blocked | Blocked | Can be destroyed by driving over them |
| **Anti-tank Spikes** | Blocked | **Pass Through** | Indestructible |
| **Brick Walls** | Blocked | Blocked | **Destroyed by projectiles & explosions** |

---

## ⌨️ Controls

### 2-Player Mode
* **Player 1:** * `W`, `A`, `S`, `D`: Movement
  * `SPACE`: Shoot
  * `E`: Deploy Dynamite
* **Player 2:** * `Arrow Keys`: Movement
  * `Right CTRL`: Shoot
  * `Right SHIFT`: Deploy Dynamite

### 3 & 4-Player Mode (Simplified)
* **Player 1:** `W`
* **Player 2:** `Up Arrow`
* **Player 3:** `T`
* **Player 4:** `O`

---

## 🛠 Tech Stack

* **Language:** Java
* **Framework:** [libGDX](https://libgdx.com/)
* **Physics Engine:** [Box2D](https://box2d.org/)

---

## 🚀 How to Run

### Prerequisites
* Java Development Kit (JDK) 17 or higher.
* The project uses the Gradle build system.

### Running from Terminal
To launch the game, run the following command in the root directory:

./gradlew desktop:run

---

## 🎓 About the Project

This project was developed as part of the **Object-Oriented Programming** course within the **Theoretical Computer Science** program at the **Jagiellonian University** in Kraków.

### 👥 Authors

* **Karol Bielaszka**
* **Mateusz Gabzdyl**
* **Jacek Markiewicz**
