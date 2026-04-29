/**
 * Item.java
 * Base class for all items in the game.
 */

public class Item {

    private String name;
    private String description;
    private boolean isClue;

    public Item(String name, String description, boolean isClue) {
        this.name = name;
        this.description = description;
        this.isClue = isClue;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isClue() {
        return isClue;
    }

    public void examine() {
        System.out.println("You examine the " + name + ".");
        System.out.println(" " + description);
        if (isClue) {
            System.out.println(" [This could be important.]");
        }    
    }

    public String toString() {
        return name;
    }
}