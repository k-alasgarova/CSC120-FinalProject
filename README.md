# CSC120-FinalProject

## Deliverables:
 - Your final codebase
 - Your revised annotated architecture diagram
 - Design justification (including a brief discussion of at least one alternative you considered)
 - A map of your game's layout (if applicable)
 - `cheatsheet.md`
 - Completed `rubric.md`
  
## Additional Reflection Questions
 - What was your **overall approach** to tackling this project?
 - What **new thing(s)** did you learn / figure out in completing this project?
 - Is there anything that you wish you had **implemented differently**?
 - If you had **unlimited time**, what additional features would you implement?
 - What was the most helpful **piece of feedback** you received while working on your project? Who gave it to you?
 - If you could go back in time and give your past self some **advice** about this project, what hints would you give?
 - _If you worked with a team:_ please comment on how your **team dynamics** influenced your experience working on this project.

# FASTEN YOUR SEATBELTS
This is a text-based mystery game set on Flight 404, Boston to Zurich. The playerdiscovers a body in the lavatory of the airplane and is mistakenly identified as a famous detective by the crew (the name is accidentally too similar!). Now stuck as a detective, the player must go undercover as a flight attendant - serve meals, collect clues, interrogate passengers, and identify/accuse the killer before the plane lands - all in 25 turns. 

# WORLD MAP
The Plane
[COCKPIT DOOR]
        |
    [BUSINESS CLASS] ---- [GALLEY]
         |                    |
    [ECONOMY CLASS]       [LAVATORIES]

# FILES IN THIS PROJECT
- Main.java - starts the game
- Game.java - main controller
- Player.java - the player
- Plane.java - the world (with all the locations)
- Location.java - abstract base class for all locations
- Economy.java
- BusinessClass.java
- Galley.java
- Lavatory.java
- CockpitDoor.java
- Character.java
- Passenger.java
- Suspect.java
- CrewMember.java
- Item.java
- ClueItem.java
- UsableItem.java
