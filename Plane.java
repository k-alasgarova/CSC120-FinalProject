import java.util.ArrayList;

/**
 * Plane.java
 * Plane contains all locations and the game world.
 * It is used by the Game to access every zone on the aircraft.
 */

public class Plane {

    private String flightNumber;
    private ArrayList<Location>locations;

    private Economy economy;
    private BusinessClass businessClass;
    private Galley galley;
    private Lavatory lavatory;
    private CockpitDoor cockpitDoor;

    public Plane(String flightNumber) {
        this.flightNumber = flightNumber;
        this.locations = new ArrayList<Location>();
        buildPlane();
    }

    private void buildPlane() {
        economy = new Economy();
        businessClass = new BusinessClass();
        galley = new Galley();
        lavatory = new Lavatory(true);
        cockpitDoor = new CockpitDoor();

        locations.add(galley);
        locations.add(economy);
        locations.add(businessClass);
        locations.add(lavatory);
        locations.add(cockpitDoor);
        
    }

    public Economy getEconomy() {
        return economy;
    }
    
    public BusinessClass getBusinessClass() {
        return businessClass;
    }
    
    public Galley getGalley() {
        return galley;
    }
    
    public Lavatory getLavatory() {
        return lavatory;
    }
    
    public CockpitDoor getCockpitDoor() {
        return cockpitDoor;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void printMap() {
        System.out.println("\n--- PLANE MAP: Flight " + flightNumber + " ---");
        System.out.println("                                         ");
        System.out.println("  [COCKPIT DOOR]                         ");
        System.out.println("        |                                ");
        System.out.println("  [BUSINESS CLASS]-----[GALLEY]          ");
        System.out.println("        |                   |            ");
        System.out.println("  [ECONOMY CLASS]      [LAVATORIES]       ");
        System.out.println("------------------------------------------");
        System.out.println(" GO ECONOMY | GO BUSINESS | GO GALLEY |GO LAVATORY | GO COCKPIT");
    }
    
}
