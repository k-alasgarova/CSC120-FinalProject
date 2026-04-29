/**
 * Suspect.java
 * Suspect is a passenger suspected of murder.
 * Each has 3 stages dialogu - where the player keeps talking to them (can even serve them first) to unlock more revealing responses.
 */

public class Suspect extends Passenger {

    private boolean isGuilty;
    private String motive;
    private String alibi;

    //The 3 stages of dialogue for the suspect
    private String[] dialogueLines;

    public Suspect(String name, String seat, String quirk, boolean needsService, boolean isGuilty, String motive, String alibi, String[] dialogueLines){
        super(name, seat, quirk, needsService);
        this.isGuilty = isGuilty;
        this.motive = motive;
        this.alibi = alibi;
        this.dialogueLines = dialogueLines;
    }

    public boolean isGuilty(){
        return isGuilty;
    }
    public String getMotive(){
        return motive;
    }
    public String getAlibi(){
        return alibi;
    }

    @Override
    public void talk(Player player) {
        // Branch 1: they won't talk until served
        if (needsService()) {
            System.out.println(getName() + " dismisses you with a wave.");
            System.out.println(" \"I don't need anything from you right now.\"");
            return;
        }
        int state = getDialogueState();

        //Branch 2: the first conversation
        if (state == 0) {
            System.out.println(getName() + " speaks carefully.");
            System.out.println(" \"" + dialogueLines[0] + "\"");
        //Branch 3: second conversation where they reveal something
        } else if (state==1) {
            System.out.println(getName() + " hesitates.");
            System.out.println(" \"" + dialogueLines[1] + "\"");   
            // Give player a clue
            ClueItem clue = new ClueItem(getName() + "'s slip", dialogueLines[1], getName());
            player.addClue(clue);
            System.out.println("  >> Clue added:" + clue.getName());

        // Branch 4: third conversation: shuts down
        } else if (state == 2) {
            System.out.println(getName() + " looks out the window.");
            System.out.println(" \"" + dialogueLines[2] + "\"");
        //Branch 5: done talking
        } else {
            System.out.println(getName() + " has nothing more to say.");
            System.out.println(" \"I've told you everything I know. Probably (added softly).\"");
        }

        advanceDialogue();
        setHasSpoken(true);
    }

    @Override
    public void react() {
        //Branch: guilty vs. innocent reaction
        if (isGuilty) {
            System.out.println(getName() + " goes completely still.");
            System.out.println("  A long pause. ");
            System.out.println(" \"...How did you know?\"");
        } else {
            System.out.println(getName() + " shoots to their feet.");
            System.out.println(" \"ABSOLUTELY NOT. I want a lawyer!");
            System.out.println(" Actually, no. I want TWO lawyers! This is outrageous! The nerve!\"");
        }
    }
}
