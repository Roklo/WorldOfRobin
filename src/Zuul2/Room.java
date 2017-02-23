package Zuul2;

import java.util.HashMap;
import java.util.Set;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. "World of Zuul" is a
 * very simple, text based adventure game.
 *
 * A "Room" represents one location in the scenery of the game. It is connected
 * to other rooms via exits. The exits are labelled north, east, south, west.
 * For each direction, the room stores a reference to the neighboring room, or
 * null if there is no exit in that direction.
 *
 * @author Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
public class Room
{

    private String description;
    private HashMap<String, Room> exits;

    private Item item;

    /*
    private Room currentRoom;
    private String description;
    private Room northExit;
    private Room southExit;
    private Room eastExit;
    private Room westExit;
    private Room downExit;
    private Room upExit;
     */
    private int desertStat = 0;

    /**
     * Create a room described "description". Initially, it has no exits.
     * "description" is something like "a kitchen" or "an open court yard".
     *
     * @param description The room's description.
     */
    public Room(String description)
    {
        this.description = description;
        exits = new HashMap<>();
        this.item = null;
    }

    public Room(String description, Item item)
    {
        this(description);

        this.item = item;

    }

    /**
     * Return a description of the area's exits, for example, "Exits: north
     * west".
     *
     * @return A description of the available exits.
     */
    public String getExitString()
    {
        String returnString = "Paths: ";
        Set<String> keys = exits.keySet();
        for (String exit : keys)
        {
            returnString += " " + exit;
        }
        return returnString;

        /*
        String exitString = "Paths: ";
        boolean north = false;
        boolean east = false;
        boolean south = false;
        boolean west = false;

        if (northExit != null) {
            exitString += "north ";
            north = true;
        }
        if (eastExit != null) {
            exitString += "east ";
        }
        if (southExit != null) {
            exitString += "south ";
        }
        if (westExit != null) {

            exitString += "west ";
        }
        if (upExit != null) {
            exitString += "up";
        }
        if (downExit !=) {
            if (north == false && east == false
                    && south == false && west == false) {
                exitString += "There are noe exits!";
            }
        }

        return exitString;
         */
    }

    /**
     *
     * @return Returning the status of the dessert area
     */
    public int getDesertStat()
    {
        return desertStat;
    }

    /**
     * Define the exits of this room. Every direction either leads to another
     * room or is null (no exit there).
     *
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     * @param up The up exit
     * @param down The sown exit
     */
    public void setExits(String direction, Room neighbor)
    {
        exits.put(direction, neighbor);
        /*

        if (north != null) {
            exits.put("north", north);
            // northExit = north;
        }
        if (east != null) {
            exits.put("east", east);
            //eastExit = east;
        }
        if (south != null) {
            exits.put("south", south);
            //southExit = south;
        }
        if (west != null) {
            exits.put("west", west);
            //westExit = west;
        }
        if (up != null) {
            exits.put("up", up);
            //upExit = up;
        }
        if (down != null) {
            exits.put("down", down);
            //downExit = down;
        }
         */

    }

    /**
     *
     * @param direction
     * @return
     */
    public Room getExit(String direction)
    {
        return exits.get(direction);

        /*
        if (direction.equals("north")) {
            return northExit;
        }
        if (direction.equals("east")) {
            return eastExit;
        }
        if (direction.equals("south")) {
            return southExit;
        }
        if (direction.equals("west")) {
            return westExit;
        }
        return null;
         */
    }

    public void addItem(Item item)
    {
        this.item = item;

    }

    public Item getItem()
    {
        return this.item;
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {

        return description;
    }

    /**
     * Return a lomg description of this room, of the form: You are in the
     * kitchen. Exits: North west
     *
     * @return A description of the room, including exits.
     */
    public String getLongDescription()
    {
        String returnString = "Toy are" + description + ".\n"
                + getExitString();

        if (this.getItem() != null)
        {
            returnString += "\nThe room has an item; " 
                    + this.getItem().getAsString();
          
        }
        else
        {
            returnString += "\nThe room has no items"; 
        }
        return returnString;

    }

    public void addDesertStat()
    {
        desertStat = desertStat + 1;
    }

    public String somethingToEat()
    {

        return "You are not hungry";
    }

    public String getDetailedLongDescription()
    {
        String scenery = " ";
        if (description.contains("desert"))
        {

            if (desertStat == 0)
            {
                scenery += "You can only see sand as far as the eye can see.";
            }
            if (desertStat == 1)
            {
                scenery += "You see a small hole in the sand.";
            }
            if (desertStat == 2)
            {
                scenery += "You see a big hole in the sand.";
            }
            if (desertStat == 3)
            {
                scenery += "You see a huge hole in the sand, "
                        + "and an cave entrance.";

            }
        }
        return "You are " + description + scenery;

    }

}
