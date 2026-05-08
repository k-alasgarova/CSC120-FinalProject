import java.util.ArrayList;
import java.util.Scanner;
import javax.imageio.stream.FileImageOutputStream;

/**
 * Game.java
 * The main game controller that sets up the world, characters, items, runs the game loop.
 * the branching pathways are handled through processCommand() + helpers
 * 
 * The Killer is: Mr.Byork (Seat 3A)
 */
public class Game {
    private Player  player;
    private Plane plane;
    private Scanner scanner;
    private int turnsLeft;
    private boolean gameOver;

    //Characters
    private CrewMember diane;
    private Passenger lyudmila;
    private Passenger harold;
    private Passenger osei;
    private Passenger voss;
    private Suspect byork;
    private Suspect diplomat;
    private Suspect agnes;
    private Suspect bryson;

    private ArrayList<Suspect> suspects;

    public Game() {
        this.scanner = new Scanner(System.in);
        this.turnsLeft = 25;
        this.gameOver = false;
        this.suspects = new ArrayList<Suspect>();
    }

    // START-----------------------------------------------------------

    public void start() {
        printTitle();
        setupWorld();

        System.out.print("Enter your name: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) name = "Lyudmila";
        player = new Player(name);
        System.out.print("\nWelcome, " + name + ".");
        System.out.print("Diane is waiting for you in the galley. ");
        System.out.print("Type 'help' anytime to see all commands. ");

        player.moveTo(plane.getGalley());
        gameLoop();

    }

// WORLD SETUP ---------------------------------------------------------------
    private void setupWorld() {
        plane = new Plane("404");

        //Diane
        String[] dianeTips = {"The victim had no ID. No wallet. Someone cleaned them out completely before we found them.",
    "Mr. Kline in 2A booked his ticket 3 hours before departure. Paid cash. No return flight.",
    "The Diplomat in 4B asked me TWICE if we could reroute the plane. I said no both times.",
    "Agnes in 31D was asking about the lavatory locations before we even left Boston.",
    "Bryson's ring light case weighs 14 kilograms. A ring light weighs about 2 kilograms.",
    "Someone accessed the cargo hold manifest mid-flight The access log was wiped afterward."};
        diane = new CrewMember("Diane", "Galley", "Head Flight Attendant", dianeTips);
        plane.getGalley().addCharacter(diane);

        //The KILLER - Mr. Byork
        byork = new Suspect("Mr. Byork", "3A", "6'4\". Hasn't blinked once since Boston. Orders only warm water.",
        true, true, "The victim carried documents exposing Byork's identity theft ring.", "Claims he was asleep. Nobody can confirm this.", 
        new String[]  {
            "I was in my seat the entire flight. I suggest you speak to someone else.", "Fine. I was in the aisle once. During turbulence. I needed to stretch. That's all.", "I have nothing more to say without a lawyer present. Good day."
        }
        );
        plane.getBusinessClass().addCharacter(byork);
        suspects.add(byork);

        // The DIPLOMAT (red herring) 
        diplomat = new Suspect("The Diplomat", "4B", "Will not name their country. Brought their own cutlery.", true, false, "Was meeting the victim in Zurich - for a legitimate handoff, not murder.",
            "Was on an encrypted call with their embassy during the murder window.",
            new String[] {
                "I am not at liberty to discuss my business on this flight", "I knew the person in the lavatory. We had...an arrangement. In Zurich. Business strictly.",
                "I have told you everything I am permitted to tell you. Please do not ask again."
    
            }
        );
        plane.getBusinessClass().addCharacter(diplomat);
        suspects.add(diplomat);

        //AGNES - red herring
        agnes = new Suspect("Agnes", "31D", "Matching travel vest. Retired.. Knows too much about Swiss banking.", false, false, "Was meeting the victim in Zurich - for a legitimate handoff, not murder.",
            "Was hired by an insurance firm to observe the victim. Not to harm them.\", \"Was with Harold the entire time. Harold will confirm this enthusiastically.",
            new String[] {
                "Oh we're just on holiday! Harold's idea. We do love Zurich this time of year.", "I suppose I should tell you... I was asked to keep an eye on someone on this flight. Just watch. Nothing more.", 
                "Whoever killed that poor person it wasn't us. Harold hasn't left my side once."
            }
        );
        plane.getEconomy().addCharacter(agnes);
        suspects.add(agnes);
        
        
        // BRYSON - red herring
        bryson = new Suspect("Bryson", "22C", "Influencer that is life-streaming the entire flight.Ring light case is suspicious - it's heavy!", false, false, "Was meeting the victim in Zurich - for a legitimate handoff, not murder.",
            "Was hired by an insurance firm to observe the victim. Not to harm them.\", \"Was with Harold the entire time. Harold will confirm this enthusiastically.",
            new String[] { 
                "Oh we're just on holiday! Harold's idea. We do love Zurich this time of year.", "I suppose I should tell you... I was asked to keep an eye on someone on this flight. Just watch. Nothing more.", 
                "Whoever killed that poor person it wasn't us. Harold hasn't left my side once."
            }
        );
        plane.getEconomy().addCharacter(bryson);
        suspects.add(bryson);

        //LYUDMILA - US
        lyudmila = new Suspect("Lyudmila", "14B", "Will not name their country. Brought their own cutlery.", true, false, "Was meeting the victim in Zurich - for a legitimate handoff, not murder.",
            "Was on an encrypted call with their embassy during the murder window.",
            new String[] {
                "I am not at liberty to discuss my business on this flight", "I knew the person in the lavatory. We had...an arrangement. In Zurich. Business strictly.",
                "I have told you everything I am permitted to tell you. Please do not ask again."
    
            }
        );
        plane.getEconomy().addCharacter(lyudmila);

        //DR.OSEI 
        osei = new Passenger("Dr. Osei", "19B", "Sleep researcher. Has been asleep (or pretended to be) since boarding. Strangely, keeps waking up in different seats.", false);
        plane.getEconomy().addCharacter(osei);

        //HAROLD
        harold = new Passenger("Harold", "31E", "Retured. Podkabluchnik. Matching travel vest + agrees with EVERYTHING Agnes says. Enthusiastic fellow.", false);
        plane.getEconomy().addCharacter(harold);

        
        
        //MADAME VOSS
        voss = new Passenger("Madame Voss", "14A", "Self-described oracle. Sharlatan, of course. Sofar, actually predicted three things correctly. Wants pretzels.", true);
        plane.getEconomy().addCharacter(voss);

        //ITEMS-----------------------------------------------
        // Galley supplies 
        plane.getGalley().addSupply(new UsableItem("pretzel bag", "Standard airline pretzels", "They take the pretzels. Madame Voss looks satisfied."));
        plane.getGalley().addSupply(new UsableItem("blanket", "A thin airline blanket", "They wrap themselves without a single polite thank you."));
        
        
        
        plane.getGalley().addSupply(new UsableItem("warm water","A cup of warm water. Room temperature. No ice.", "They accept the water without looking up. Small progress."));
        plane.getGalley().addSupply(new UsableItem("meal tray", "Foil-covered. Pasta only - the chicken ran out over Nova Scotia.", "They take the pasta. Bedrudgingly. The nerve."));

        //Clue items hidden on the plane
        plane.getGalley().addItem(new ClueItem("torn boarding pass", "Half a boarding pass, torn clean. The name is missing. The seat number reads: 3A.", "Mr. Byork"));
        plane.getLavatory().addItem(new ClueItem("monogrammed cufflink", "Silver cufflink. Initials engraved: R.K. Found the body. Very expensive.", "Mr. Byork"));
        plane.getEconomy().addItem(new ClueItem("folded note", "Found tucked under seat 14A. Reads: 'He is in 3A. Do not let him reach Zurich. Alive.' Unsigned.", "Mr. Byork"));
        plane.getBusinessClass().addItem(new ClueItem("empty briefcase", "Found in the overhead bin above seat 3A. Locks are broken open. Whatever was inside is gone", "Mr. Byork"));

        // GAME LOOP----------------------------------------------
    }
    
    private void gameLoop() {
        while (!gameOver) {
            if (turnsLeft <= 0) {
                endGame(false, null);
                return;
            }
            System.out.println("\n[Turns left: " + turnsLeft + " | Clues: " + player.getClues().size() + "]");
            System.out.print("> ");
            if (!scanner.hasNextLine()) break;
            String input = scanner.nextLine().trim().toLowerCase();
            if (!input.isEmpty()) {
                processCommand(input);
            }
        }
    }

    // COMMAND PARSER----------------------------------------

    private void processCommand(String input) {
        //movement
        if (input.startsWith("go ")) {
            handleMove(input.substring(3).trim());
        //look
        } else if (input.equals("look")) {
            player.getCurrentLocation().look();
        //map
        } else if (input.equals("map")) {
            plane.printMap();
        //talk
        } else if (input.startsWith("talk ")) {
            handleTalk(input.substring(5).trim());
        //take
        } else if (input.startsWith("take ")) {
            handleTake(input.substring(5).trim());
        //examine
        } else if (input.startsWith("examine ")) {
            handleExamine(input.substring(8).trim());
        //serve
        } else if (input.startsWith("serve ")) {
            handleServe(input.substring(6).trim());
        //search (lavatory only)
        } else if (input.equals("search")) {
            handleSearch();
        //knock (cockpit only)
        } else if (input.equals("knock")) {
            if (player.getCurrentLocation() instanceof CockpitDoor) {
                ((CockpitDoor) player.getCurrentLocation()).knock();
                turnsLeft--;
            } else {
                System.out.println("There's nothing to knock on here.");
            }

        //CLUES
        } else if (input.equals("clues")) {
            player.showClues();
        //INVENTORY
        }else if (input.equals("inventory") || input.equals("inv")) {
            player.showInventory();
        //ACCUSE
        } else if (input.startsWith("accuse ")) {
            handleAccuse(input.substring(7).trim());
        //HELP
        } else if (input.equals("help")) {
            printHelp();
        //QUIT
        } else if (input.equals("quit")) {
            System.out.println("You hang up the apron and sit down.");
            System.out.println("The killer lands in Zurich. Reginals gets the chocolate.");
            gameOver = true;
        } else {
            System.out.println("You stand there a moment. (Unknown commands --- type 'help'");
        }
        
    }

    // MOVE HANDLER--------------------------------------------------------------
    private void handleMove(String dest) {
        Location target = null;

        if (dest.contains("economy")) target = plane.getEconomy();
        else if (dest.contains("business")) target = plane.getBusinessClass();
        else if (dest.contains("galley")) target = plane.getGalley();
        else if (dest.contains("lavatory") || dest.contains("bathroom") || dest.contains("toilet"))  target = plane.getLavatory();
        else if (dest.contains("cockpit") || dest.contains("door")) target = plane.getCockpitDoor();

        if (target == null) {
            System.out.println("Go where? Options: economy, business, galley, lavatory, cockpit");
        } else if (target == player.getCurrentLocation()) {
            System.out.println("You are already here. Open your eyes.");
        } else {
            player.moveTo(target);
            turnsLeft--;
        }
    }

    // TALK HANDLER ---------special for Lyudmila and Voss
    private void handleTalk(String name) {
        //Lyudmila has her own branching dialogue
        if (name.contains("lyudmila") || name.contains("bozhen") || name.contains("mother")) {
            if (!playerIsIn(plane.getEconomy())) {
                System.out.println("Lyudmila isn't here.");
                return;
            }  
            handleLyudmila();
            turnsLeft--;
            return;         
        }
        //Madame Voss has a multi-branch response
        if (name.contains("voss") || name.contains("madame")) {
            if (!playerIsIn(plane.getEconomy())) {
                System.out.println("Madame Voss isn't here.");
                return;
            }
            handleVoss();
            turnsLeft--;
            return;
        }

        //Search characters in current location
        for (Character c : player.getCurrentLocation().getCharacters()) {
            if (c.getName().toLowerCase().contains(name)) {
                c.talk(player);
                turnsLeft--;
                return;
            }
        }
        System.out.println("There's no one here by that name.");
    }

    //Lyudmila branching dialogue in 3 stages
    private void handleLyudmila() {
        int state = lyudmila.getDialogueState();

        if (state == 0) {
            //Stage1: she gives you crucial clue
            System.out.println("\nLyudmila looks you up and down.");
            System.out.println("\"You look terrible. When did you last eat?\"");
            System.out.println("She doesn't wait for an answer.");
            System.out.println("\"That man in front. 3A one. He was in the aisle.");
            System.out.println("during the turbulence. I know because he stepped on my foot and did not apologize");
            System.out.println("In my country, this says everything about a person.\"");
            System.out.println("She hands you the cabbage roll that she takes from her packet bag.");
            System.out.println("\"Eat. You can't solve murder when your stomach is getting murdered.\"");
            ClueItem clue = new ClueItem("Lyudmila's testimony", "Byork was in the aisle during turbulence - the exact window of murder.", "Mr. Byork");
            player.addClue(clue);
            System.out.println(">> Clue added: " + clue.getName());

        } else if (state == 1) {
            //Stage 2 talking about her daughter
            System.out.println("\n\"You want to know something?\" Lyudmila says,");
            System.out.println("as if you asked. \"My daughter. Katya.");
            System.out.println("She has a degree in COMPUTER SCIENCE!");
            System.out.println("Now she charges Swiss people 200 francs to teach them to BREATHE.\"");
            System.out.println("She holds up her phone. On the wrbsite, there is a gong. A gong?");
            System.out.println("\" I printed it. As evidence. And reminder of my failure. Here.\"");
            System.out.println("She produces a thick folded document from her bag, meticulously stiched.");
            System.out.println("And, yes, it is a printed website - all 14 pages.");
        } else if (state == 2) {
            //Stage 3 - giving her final advice
            System.out.println("\n\"You still don't have him?\" Lyudmila signs.");
            System.out.println("\"The man in 3A. Bad posture. Untasteful cold eyes.");
            System.out.println("In 35 years of my honorable teaching I can SNIFF trouble from that posture.");
            System.out.println("We had a boy just like him in Form 5C, 1988.");
            System.out.println("He though he was very clever, too.\"");
            System.out.println("She taps her temple. \"Trust this. Never let me down. The badge is extra here.\"");
        } else {
            // Stage 4+ done
            System.out.println("\n\"Go,\"says Lyudmila.\"Solve the murder.");
            System.out.println("Then come back. I have more food.\"");
            System.out.println("She turns to the window with a great finality.");
        }
        lyudmila.advanceDialogue();
        lyudmila.setHasSpoken(true);
    }

    // MADAME VOSS - branching on whether she has pretzels
    private void handleVoss() {
        if (voss.needsService()) {
            System.out.println("\nMadame Voss looks at you with ancient, knowing eyes.");
            System.out.println("\"You want answers, I see. I have them.\"");
            System.out.println("She folds her hands diligently on her lap.");
            System.out.println("\"Pretzels upfront, please, dear.\"");
            return;
        }
        int state = voss.getDialogueState();

        if (state == 0) {
            System.out.println("\nMadame Voss eats a pretzel slowly.");
            System.out.println("\"The one you are looking for,\"she says,");
            System.out.println("\"has cold hands. I have felt that deadly cold when shaking them at boarding.\"");
            System.out.println("\"He smiled like a man who has won. Smug. Men...\"");
            System.out.println("He has not won yet. But like all men, he thinks he has.\"");
            ClueItem clue = new ClueItem("Voss's reading", "Someone with cold hands boarded confidently and shook her hand. 3A area.", "Mr. Byork");
            player.addClue(clue);
            System.out.println(">> Clue added: " + clue.getName());
        } else if (state == 1) {
            System.out.println("\nMadame Voss finishes another pretzel.");
            System.out.println("\"I see you are getting closer.\"");
            System.out.println("\"The documents.That is what it is all about. The papers.");
            System.out.println("Someone on the plane needed them gone!");
            System.out.println("\"The killer is not afraid of you. Not yet.\"");
            
        } else {
            System.out.println("\nMadame Voss shakes her head.");
            System.out.println("\"I have said what I can say.");
            System.out.println("The rest you already know. You just don't know you know it.\"");
            System.out.println("She dramatically closes her eyes as if you tired her.");
            System.out.println("Well, conversation is over, then.");
        }
        voss.advanceDialogue();
        voss.setHasSpoken(true);
    }
    //TAKE HANDLER -----------------------------
    private void handleTake(String itemName) {
        for  (Item item : player.getCurrentLocation().getItems()) {
            if (item.getName().toLowerCase().contains(itemName)) {
                player.take(item);
                if (item instanceof ClueItem) {
                    player.addClue((ClueItem) item);
                    System.out.println(">> Clue added: " + item.getName());
                }
                turnsLeft--;
                return;
            }
        }
        System.out.println("You don't see that here.");
    }

    //EXAMINE HANDLER ----------------------------
    private void handleExamine(String itemName) {
        //Check first inventory
        for  (Item item : player.getInventory()) {
            if (item.getName().toLowerCase().contains(itemName)) {
                item.examine();
                return;
            }
        }
        //Then current location
        for  (Item item : player.getCurrentLocation().getItems()) {
            if (item.getName().toLowerCase().contains(itemName)) {
                item.examine();
                return;
            }
        }
        System.out.println("You don't see that anywhere.");
    }

    //SERVE HANDLER------------------
    //for servine item to character
    private void handleServe(String input) {
        String[] parts = input.split(" ", 2);
        if (parts.length < 2) {
            System.out.println("Serve who with what?(e.g. serve byork water)");
            return;
        }
        String charName = parts[0];
        String itemName = parts[1];

        //Find character in current location
        Character target = null;
        for  (Character c : player.getCurrentLocation().getCharacters()) {
            if (c.getName().toLowerCase().contains(charName)) {
                target = c;
                break;
            }
        }
        //Check Lyudmila and Voss by nickname
        if (target == null && (charName.contains("lyudmila") || charName.contains("mother"))){
            target = lyudmila;
        }
        if (target == null && (charName.contains("voss") || charName.contains("madame"))){
            target = voss;
        }
        if (target == null) {
            System.out.println("Can't find that person here.");
            return;
        }

        //Find item - check first if supplied in galley
        UsableItem toServe = null;
        if (player.getCurrentLocation() instanceof Galley) {
            toServe = ((Galley) player.getCurrentLocation()).findSupply(itemName);
        }
        //Also check player inventory
        if (toServe == null){
            for (Item item: player.getInventory()) {
                if (item instanceof UsableItem && item.getName().toLowerCase().contains(itemName)) {
                    toServe = (UsableItem) item;
                    break;
                }
            }
        }
        if (toServe == null) {
            System.out.println("You don't have that. Check the galley for supplies.");
            return;
        }
        //Deliver the item
        toServe.use(target);
        //Unlock dialogue
        if (target instanceof Passenger) {
            ((Passenger) target).setNeedsService(false);
            System.out.println(target.getName() + " is now willing to talk.");
        }
        turnsLeft--;

    }

    //SERACH HANDLER ------------LAVATORY CRIME SCENE
    private void handleSearch() {
        Location loc = player.getCurrentLocation();
        if (!(loc instanceof Lavatory)) {
            System.out.println("There's nothing to search here.");
            return;
        }
        Lavatory lav = (Lavatory) loc;
        if (!lav.isCrimeScene()) {
            System.out.println("There's nothing unusual here.");
            return;
        }
        if (!lav.hasBeenSearched()) {
            System.out.println("You have already searched here. There's nothing else to find.");
            return;
        }
        //search reveals cufflink
        System.out.println("Yo take a deep breath. Go back in. It's as bad as you remember. After careful search, your hand closes around something small.");
        //find cufflink
        ArrayList<Item> itemsCopy = new ArrayList<Item>(lav.getItems());
        for (Item item: itemsCopy) {
            if (item.getName().contains("cufflink")) {
                player.take(item);
                player.addClue((ClueItem) item);
                System.out.println(">> Clue added: " + item.getName());
            }
        }
        lav.setSearched(true);
        turnsLeft--;
    }
    //ACCUSE HANDLER----------
    private void handleAccuse(String name) {
        ////Need at least 3 clues before accusing
        if (player.getClues().size() <3) {
            System.out.println("You don't have enough evidence yet. Diane gives you a look - 'Don't embarass us both.'");
            System.out.println("(You need at least 3 clues before accusing anyone.)");
            return;
        }
        Suspect target = null; 
        for (Suspect s: suspects) {
            if (s.getName().toLowerCase().contains(name)) {
                target=s; 
                break;
            }
        }
        if (target == null) {
            System.out.println("Accuse who? Use their name. (e.g. accuse byork)");
            return;
        }
        player.accuse(target); 
        endGame(target.isGuilty(), target);
    }

    //END GAME ------------------------------- 3 endings
    private void endGame(boolean won, Suspect accused) {
        System.out.println("\n===================================");
        if (won) {
            System.out.println(" YOU GOT HIM."); //ending 1- correct accusation
            System.out.println("Mr. Byork goes very still. A long silence."); 
            System.out.println("\"...How did you know?\"");
            System.out.println("The storm clears. The plane begins its descent.");
            System.out.println("Zurich police are waiting on the tarmac. Byork is escorted off the plane in handcuffs.");
            System.out.println("He still does not blink.");
            System.out.println("Diane shakes your hand exactly once.");
            System.out.println("Says nothing. That is her version of astanding ovation.");
            System.out.println("\"Well, if I can solve murder, I can make my daughter see reason.\"");
            System.out.println("YOU WIN");
        } else if (accused != null) {
            System.out.println(" WRONG.");
            System.out.println("\n===================================");
            System.out.println(accused.getName() +" is furious.");
            System.out.println("From seat 3A, Mr. Byork watches. He does not blink. He does not smile. The plane lands. He walks off. ");
            System.out.println("Well, I will still make my daughter see reason");
            System.out.println("Game over!");
        } else {
            //END 3-no turns
            System.out.println(" TIME'S UP.");
            System.out.println("Ladies and gentlemen, we are beginning our descent into Zurich...");
            System.out.println("Mr. Byork closes his briefcase. Stretches. Looks at you. Blinks for the first time. Smiles. Then walks off the plane.");
            System.out.println("Game over!");
        }
        gameOver = true;
    }

    //HELPERS----------
    private boolean playerIsIn(Location loc) {
        return player.getCurrentLocation() == loc;
    }
    //TITLE SCREEN----------
    private void printTitle() {
        System.out.println(" F AS T E N YOUR SEATBELT S");
        System.out.println(" Flight 404. Boston to Zurich. 8 hours.");
        System.out.println(" You just wanted to use the bathroom There was a line. And loud storm.");
        System.out.println(" You went in. And...saw the body. Your passport fell out. A crew member Diane read the name.");
        System.out.println(" Your name is similar to the famous detective's one. You were told to handle the case before the plane lands.");
        System.out.println(" You are handed the uniform of flight attendant. Let's go undercover.");
    }
    //HELP MENU--------
    private void printHelp() {
        System.out.println("go [place) Move: economy, business, galley, lavatory, cockpit");
        System.out.println("look to loook around current location");
        System.out.println("talk [name] talk to someone");
        System.out.println("serve [name] serve voss pretzel)");
        System.out.println("take [item] Pick up an item");
        System.out.println("examine [item] Examine an item closely");
        System.out.println("search - to search crime scene");
        System.out.println("knock (at cockpit only)"); 
        System.out.println("clues (review all clues)"); 
        System.out.println("inventory / inv Check your inventory");
        System.out.println("accuse (need 3+ clues)");

    }



    








}
