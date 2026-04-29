/**
 * Passenger.java
 * It is a regular passenger on the flight.
 * If needsService = true then the player has to serve them something before they'll talk.
 */

public class Passenger extends Character {

    private String quirk;
    private boolean needsService;

    public Passenger(String name, String seat, String quirk, boolean needsService) {
        super(name, seat);
        this.quirk  = quirk;
        this.needsService = needsService;
    }

    public String getQuirk()  { return quirk; }
    public boolean needsService()  { return needsService; }
    public void setNeedsService(boolean b) { this.needsService = b; }

    @Override
    public void talk(Player player) {
        if (needsService) {
            System.out.println(getName() + " ignores you. ");
            System.out.println("  Maybe if you helped them first, then they might talk. Common thing.");
            return;
        }
        System.out.println(getName() + " looks up at you.");
        System.out.println("  \"" + quirk + "\"");
        setHasSpoken(true);
    }

    @Override
    public void react() {
        System.out.println(getName() + " looks deeply offended. Oh, get a grip! Everyone is so sensitive these days!");
        System.out.println("  \"I beg your pardon?! I want to speak to someone in charge here!\"");
        
    }
    
}
