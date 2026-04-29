public class Economy extends Location {
    
    public Economy() {
        super("Economy Class", 
        "Rows 15-35. Smells like pretzels and quiet desperation.\n" + 
        "Every seat is taken. A child is staring at you. You stare back. The child turns away. Just as I thought!");

    }

    @Override
    public void enter() {
        System.out.println("\n=============================================");
        System.out.println("  ECONOMY CLASS");
        System.out.println("\n=============================================");
        System.out.println("You push through the curtain.");
        System.out.println("Thirty pairs of eyes hopefully and hungrily turn towards you ");
        System.out.println("Someone in the center immediately presses the call button.");
        System.out.println("Well, Lyudmila, welcome to your new life. Been through worse. We'll show them who's in charge. \"Here is your cold water, dear.\"");
        look();
    }
    
}
