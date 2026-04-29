/**
 * BusinessClass.java
 * This is a Business Class cabin. Quieter, more expensive,... more suspicious?
 */

public class BusinessClass extends Location{

    public BusinessClass() {
        super("Business Class", 
            "Rows 1-8; has wider seats, real glasses, and rich kind of silence\n" + 
            "that silence costs $4,000. What a waste.");
    }

    @Override
    public void enter() {
        System.out.println("\n=============================================");
        System.out.println("  BUSINESS CLASS");
        System.out.println("\n=============================================");
        System.out.println("You step through the dividing curtain.");
        System.out.println("Even the air smells and feels different here. Better. Well, money has a special fragnance.");
        System.out.println("Three passengers look up at you; none of them smile. You almost scowl but then remember the role, and, oh well, have to smile, I guess.");
        look();
    }
    
}
