import java.util.ArrayList;

/**
 * Player.java
 * The player that moves through the game - traks location, inventory, clues, and accusations.
 */


public class Player {

    private String name;
    private Location currentLocation;
    private ArrayList<Item> inventory;
    private ArrayList<ClueItem> clues;
    private int accusationsMade;

    public Player(String name) {
        this.name = name;
        this.inventory = new ArrayList<Item>();
        this.clues = new ArrayList<ClueItem>();
        this.accusationsMade = 0;
    }

    public String getName() {
        return name;
    }
    public Location getCurrentLocation() {
        return currentLocation;
    }
    public ArrayList<Item> getInventory() {
        return inventory;
    }
    public ArrayList<ClueItem> getClues() {
        return clues;
    }
    public int getAccusationsMade() {
        return accusationsMade;
    }

    public void moveTo(Location location) {
        this.currentLocation = location;
        location.enter();
    }

    public void take(Item item) {
        inventory.add(item);
        currentLocation.removeItem(item);
        System.out.println("You pick up: " + item.getName());
    }

    public void addClue(ClueItem clue) {
        //Dpn't add duplicate clues
        for (ClueItem c : clues){
            if (c.getName().equals(clue.getName())) return;
        }
        clues.add(clue);
    }

    public void showInventory() {
        if (inventory.isEmpty() && clues.isEmpty()){
            System.out.println("You have nothing. Just the apron. And regret.");
            return;
        }
        System.out.println("\n--- INVENTORY ---");
        for (Item item : inventory) {
            System.out.println("  - " + item.getName());
        }
        if (!clues.isEmpty()) {
            System.out.println("--- CLUES ---");
            for (ClueItem c : clues) {
                System.out.println(" - " + c.getName() + ": " + c.getDescription());
            }
        }
    }

    public void showClues() {
        if (clues.isEmpty()) {
            System.out.println("No clues yet. Go talk to people. Serve some pretzels or something.");
            return;
        }
        System.out.println("\n--- YOUR CLUES (" + clues.size() + ") ---");
        for (ClueItem c : clues) {
            System.out.println(" * " + c.getName());
            System.out.println("  " + c.getDescription());
            System.out.println("  >>Points to:" + c.getPointsTo());
        }
    }
    
    public void accuse(Suspect suspect) {
        accusationsMade++;
        System.out.println("\nYou point directly at " + suspect.getName() + ".");
        System.out.println("The cabin goes completely silent.");
        suspect.react();
    }

    public String toString() {
        return name + " | Location: " + (currentLocation != null ? currentLocation.getName() : "unknown") + " | Clues: " + clues.size();
    }
}
