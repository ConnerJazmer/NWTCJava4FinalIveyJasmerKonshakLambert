package CaveExplorer;

import CaveExplorer.globals.Direction;
import CaveExplorer.globals.Times;
import CaveExplorer.items.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Game class stores and manipulates all information about the game
 */
public class Game {

    //1.1 proper use of visibility modifier
    private ArrayList<Room> map;
    private Player player;

    /**
     * Game constructor instantiates all the game objects
     */
    public Game() {

        Parser.initializeCommandList(this);

        //Create map
        map = new ArrayList<Room>();


        //Create Rooms
        Room caveEntrance = new Room("A bleak cave","Cave Entrance");
        Room caveRoom2 = new Room("dark cave","Cave room 2");
        Room caveRoom3 = new Room("damp cave","Cave room 3");
        Room caveRoom4 = new Room("cave cave", "Cave room 4");
        Room caveRoom5 = new Room("cave description", "Cave room 5");
        Room caveExit = new Room("To exit the cave", "Cave Exit");

        //Create Items
        Dynamite dynamite = new Dynamite("dynamite","Useful for blowing stuff up", true, caveExit);
        Pickaxe pickaxe = new Pickaxe("pickaxe","Useful for mining", false);
        Flashlight flashlight = new Flashlight("flashlight", "Really lights up the room", false, false, false);
        Battery battery = new Battery("battery", "Shockingly good to have.", true);
        Flint flint = new Flint("flint", "Useful to start things on fire", true);

        //Create Room Inventories
        Inventory caveEntranceInventory = new Inventory();
        Inventory caveRoom2Inventory = new Inventory();
        Inventory caveRoom3Inventory = new Inventory();
        Inventory caveRoom4Inventory = new Inventory();
        Inventory caveRoom5Inventory = new Inventory();

        caveEntranceInventory.add(pickaxe);
        caveRoom5Inventory.add(dynamite);

        //Map out rooms and initialize inventories
        caveEntrance.initializeRoom(null,null,caveRoom2,caveExit,caveEntranceInventory);
        caveRoom2.initializeRoom(caveRoom3,null,null,caveEntrance,caveRoom2Inventory);
        caveRoom3.initializeRoom(caveRoom5,caveRoom2,caveRoom4,null,caveRoom3Inventory);
        caveRoom4.initializeRoom(null,null,null,caveRoom3,caveRoom4Inventory);
        caveRoom5.initializeRoom(null,caveRoom3,null,null,caveRoom5Inventory);

        map.add(caveEntrance);
        map.add(caveRoom2);
        map.add(caveRoom3);
        map.add(caveRoom4);
        map.add(caveRoom5);
        map.add(caveExit);
        caveExit.setLocked(true);
        caveExit.setLockedStatus("Exit is blocked by rocks.");

        //Create Player
        Inventory playerInventory = new Inventory();
        playerInventory.add(flashlight);
        playerInventory.add(battery);
        playerInventory.add(flint);
        playerInventory.add(dynamite);
        player = new Player("Adventurer1","A bold cave diver.", caveEntrance, playerInventory);

    }

    /**
     * Displays the game's introduction based on time and locale
     *
     * @param time
     * @param locale
     */

    public void showIntro (LocalTime time, Locale locale) {
        String introMessage = "";
        ResourceBundle rb = ResourceBundle.getBundle("CaveExplorer", locale);

        if (time.isAfter(Times.earlyMorningBeg) && time.isBefore(Times.earlyMorningEnd)) {
            introMessage = rb.getString("intro_early_morning");
        } else if (time.isAfter(Times.morningBeg) && time.isBefore(Times.morningEnd)) {
            introMessage = rb.getString("intro_morning");
        } else if (time.isAfter(Times.noonBeg) && time.isBefore(Times.noonEnd)) {
            introMessage = rb.getString("intro_noon");
        } else if (time.isAfter(Times.afternoonBeg) && time.isBefore(Times.afternoonEnd)) {
            introMessage = rb.getString("intro_afternoon");
        } else if (time.isAfter(Times.eveningBeg) && time.isBefore(Times.eveningEnd)) {
            introMessage = rb.getString("intro_evening");
        }

        System.out.println(introMessage);
    }

    public String MovePlayer(Direction d) {
        return player.move(d);
    }
    public String ShowPlayerInventory() {return player.printInventory();}
    public String playerLook() {return player.look();}

    public String playerLookAt(String gameObject) {return player.lookAt(gameObject);}
    public String playerTakeItem(String item) {return player.takeItem(item);}

    public String playerUseItem(String item) {return player.useItem(item);}

    public String playerDropItem(String item) {return player.dropItem(item);}

    public String playerUseItemOn(String itemToUse, String itemToUseOn) {return player.useItemOn(itemToUse,itemToUseOn);}

    public Inventory getPlayerInventory() {return player.getInventory();}

    public Inventory getCurrentRoomInventory () {return player.getCurrentRoom().getInventory();}

    public Room getPlayerRoom (){return player.getCurrentRoom();}

}
