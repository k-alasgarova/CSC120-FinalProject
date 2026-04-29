/**
 * CockpitDoor.java
 * Cockpit door to knock on and maybe someone answers.
 */

public class CockpitDoor extends Location {

    private int knockCount;

    public CockpitDoor() {
        super("Cockpit Door","A heavy reinforced door at the front of the plane. \n" + 
        "Sign reads: DO NOT DISTURB UNLESS ON FIRE. \n" + 
        "Murder apparently does not qualify.");
        this.knockCount = 0;
    }
    
    public void knock() {
        knockCount++;
        // Branch based on how many times player has knoecked 
        if (knockCount == 1) {
            System.out.println("You knock firmly. Silence.");
            System.out.println("Then a muffled voice: \"WE KNOW. HANDLE IT.\"");
        } else if (knockCount == 2) {
            System.out.println("You knock again, harder.");
            System.out.println("\"WE SAID HANDLE IT. WHY ARE YOU STILL KNOCKING.\"");
        } else if (knockCount == 3) {
            System.out.println("A long pause. A note slides under the door.");
            System.out.println("'Storm clears in 3 hrs. You have until then. Good luck.'");
            System.out.println("That is all you are getting from the cockpit.");
        } else {
            System.out.println("You knock again.");
            System.out.println("Nothing. Not even the voice anymore.");
            System.out.println("They have decided you are not worth responding to.");
        }
    }

    @Override
    public void enter() {
        System.out.println("\n=============================================");
        System.out.println("  COCKPIT DOOR");
        System.out.println("\n=============================================");
        System.out.println("You stand at the very front of the plane.");
        System.out.println("The door is sealed shut.  (type: knock)");
        look();
    }
}
