import java.util.ArrayList;
/**
 * Location.java
 * It's an abstract base class for all locations on the plane.
 * Each location has its own enter(), description, characters, and items
 */

public abstract class Location {
    
    private String name;
    private String description;
    private ArrayList<Character> characters;
    private ArrayList<Item> items;

    public Location(String name, String description) {
        this.name = name;
        this.description = description;
        this.characters = new ArrayList<Character>();
        this.items = new ArrayList<Item>();
    }

    public String getName() {return name;}
    public ArrayList<Character> getCharacters() {return characters;}
    public ArrayList<Item> getItems() { return items;}

    public void addCharacter(Character c) { characters.add(c);}
    public void addItem(Item i) {items.add(i);}
    public void removeItem(Item i) {items.remove(i);}

    //Each subclass defines its own arrival text
    public abstract void enter();

    //Shared look() lists everything here
    public void look() {
        System.out.println("\n----" + name + "---");
        System.out.println(description);

        if (characters.isEmpty()) {
            System.out.println("No one is here right now.");
        } else{
            System.out.println("People here: ");
            for (Character c : characters) {
                System.out.println("  - " + c.toString());
            }
        }
        if (!items.isEmpty()) {
            System.out.println("Items you can see: ");
            for (Item item : items) {
                System.out.println("  - " + item.getName());
            }
        }
    }

    public String toString() {
        return name;
    }
}
