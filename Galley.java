import java.util.ArrayList;

/**
 * Galley.java
 * Galley kitchen is the player's base of operations. 
 * Diane, supplies, accumulated evidence will be located here.
 */


public class Galley extends Location{

    private ArrayList<UsableItem> supplies;

    public Galley() {
        super("The Galley", "A tiny kitchen at the front of the Economy.\n" +
            "Diane's territory. Trays, carts, foil-covered meals are here.\n" + 
            "Evidence is also quietly accumulating on the counter.");
        this.supplies = new ArrayList<UsableItem>();
    }

    public void addSupply(UsableItem item) {
        supplies.add(item);
        addItem(item); // also shows up in look()
    }

    public ArrayList<UsableItem> getSupplies() {
        return supplies;
    }

    public UsableItem findSupply(String name) {
        for (UsableItem s : supplies) {
            if (s.getName().toLowerCase().contains(name.toLowerCase())) {
                return s;
            }
        }
        return null;
    }

    @Override
    public void enter() {
        System.out.println("\n=============================================");
        System.out.println("  THE GALLEY");
        System.out.println("\n=============================================");
        System.out.println("You duck into the galley. It's fluorescent and cramped.");
        System.out.println("Diane is here, trying to reorganize the evidence pile.");
        System.out.println("She doesn't bother to look up. \"Did you find anything dear?\"");
        look();
    }
}
