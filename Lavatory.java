/**
 * Lavatory.java
 * The lavatory is the one that is a crime scene.
 */

public class Lavatory extends Location {
    
    private boolean isCrimeScene;
    private boolean hasBeenSearched;

    public Lavatory(boolean isCrimeScene) {
        super("The Lavatories", "A row of three lavatory doors. One is taped off\n" + "with seatbelt. That was your idea.");
        this.isCrimeScene = isCrimeScene;
        this.hasBeenSearched = false;
    }

    public boolean isCrimeScene() {
        return isCrimeScene;
    }
    public boolean hasBeenSearched() {
        return hasBeenSearched;
    }
    public void setSearched(boolean b) {
        this.hasBeenSearched = b;
    }

    @Override
    public void enter() {
        System.out.println("\n=============================================");
        System.out.println("  THE LAVATORIES");
        System.out.println("\n=============================================");
        if (isCrimeScene) {
            System.out.println("The seatbelt tape is still holding.");
            System.out.println("Door #3 is where you found the body.");
            if (!hasBeenSearched) {
                System.out.println("You haven't searched the scene yet.");
            } else {
                System.out.println("Common. You have already searched the room.");
            }
        }
        look();
    }
}
