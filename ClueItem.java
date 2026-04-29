/**
 * ClueItem.java
 * A clue item points towards a suspect.
 */

public class ClueItem extends Item {
   
    private String pointsTo; // name of the suspect

    public ClueItem(String name, String description, String pointsTo) {
        super(name, description, true);
        this.pointsTo = pointsTo;
    }

    public String getPointsTo() {
        return pointsTo;
    }

    @Override
    public void examine() {
        super.examine();
        System.out.println(" Your instincts say this connects to: " + pointsTo);
    }
}
