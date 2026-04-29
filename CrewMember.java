/**
 * CrewMember.java
 * Diane here is the head flught attendant and our reluctant partner.
 * Each time the player talks to Diane, they receive a revealed next briefing.
 */

public class CrewMember extends Character {

    private String role;
    private String[] briefings;
    private int briefingIndex;

    public CrewMember(String name, String seat, String role, String[] briefings) {
        super(name, seat);
        this.role = role;
        this.briefings = briefings;
        this.briefingIndex = 0;
    }

    public String getRole() {
        return role;
    }

    @Override
    public void talk(Player player) {
        System.out.println(getName() + " lowers her voice:");
        //Branch: still has new info
        if (briefingIndex < briefings.length) {
            System.out.println(" \"" + briefings[briefingIndex] + "\"");
            briefingIndex++;
        
        //Branch: out of information
        } else {
            System.out.println(" \"That's all I have. Rest is up to you.\"");
            System.out.println(" \"Also, 23C is live-streaming again. Deal with it.\"");
        }
        setHasSpoken(true);
    }

    @Override
    public void react() {
        System.out.println(getName() + " says nothing for a moment.");
        System.out.println(" Then: \"I hope you're right. And I hope so for both our sakes.\"");
    }
    
}
