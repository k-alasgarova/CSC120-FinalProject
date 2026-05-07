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
- Economy.java - economy class cabin
- BusinessClass.java - business class cabin
- Galley.java - galley kitchen - supplies and evidence 
- Lavatory.java - the lavatories a.k.a. crime scene
- CockpitDoor.java - the cockpit door
- Character.java - abstract base class for all characters
- Passenger.java - regular passengers
- Suspect.java - suspects, extends passenger and has dislogue stages
- CrewMember.java - Diane, extends character and gives briefings
- Item.java - base class for all items
- ClueItem.java - clue items, extends Item and points to a suspect
- UsableItem.java - usable items, extends Itsm,can be served

# DESIGN JUSTIFICATION
The core design was to make Location, Character, and Item as abstract base classes with subclasses for specific types. This was done for structure - locations have the same basic behaviors like having items, or doing actions like look, go, but they each need to print different descriptions when the player goes there. Inheritance was used therefore. Same works with characters - each can talk and react, but Suspect has different lines/ attitudes from CrewMember, so making Character class have subclasses lets each of them have its own implementation while having the same structure. 
The Plane class is a container for all 5 locations; it was done so the Game class only holds one Plane object and asks it for whatever location needed, which makes it clearer and there is an opportunity to add more locations by simply changing Plane. 
ALTERNATIVE CONSIDERED
The obvious alternstive was to skip inheritance and have one Character class with a type field like Sting type = "suspect" or String type = "crew" and the use if-else to handle each type differently. However, it can get messy when there are many characters (and i would like to add more), so having classes clear and structures and separate works for organization and further development. 
Another alternative was to store all game logic in one file - 
