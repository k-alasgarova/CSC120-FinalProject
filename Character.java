/**
 * Character.java
 * Abstract base class for all characters in the game.
 * Passenger and CrewMember both extend this class.
 */

public abstract class Character {

    private String name;
    private String seat;
    private boolean hasSpoken;
    private int dialogueState;

    public Character(String name, String seat){
        this.name = name;
        this.seat = seat;
        this.hasSpoken = false;
        this.dialogueState = 0;
    }

    public String getName()  {return name;}
    public String getSeat()  {return seat;}
    public boolean hasSpoken()  {return hasSpoken;}
    public int getDialogueState()  {return dialogueState;}

    public void setHasSpoken(boolean b)  {this.hasSpoken = b;}
    public void advanceDialogue()  {this.dialogueState++;}
    
    // Called when player gives this character a usable item
    public void receiveItem(UsableItem item) {
        System.out.println(" " + name + " accepts the " + item.getName() + ".");
    }

    // Every character must define their own talk() and react()
    public abstract void talk(Player player);
    public abstract void react();

    public String toString() {
        return name + " (Seat " + seat + ")";
    }

}
