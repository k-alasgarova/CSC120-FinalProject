/**
 * UsableItem.java
 * An item can be served or given to a character.
 */

public class UsableItem extends Item {
    
    private String effect;

    public UsableItem(String name, String description, String effect) {
        super(name, description, false);
        this.effect = effect;
    }

    public String getEffect() {
        return effect;
    }

    public void use(Character character) {
        System.out.println("You give the " + getName() + " to " + character.getName() + ".");
        System.out.println(" " + effect);
        character.receiveItem(this);
    }
}
