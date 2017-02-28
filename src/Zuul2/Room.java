package Zuul2;

import java.util.HashMap;
import java.util.Set;
import java.util.Iterator;
import java.util.HashSet;



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

    private RoomInventory roomInventory;
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
    private int caveStat = 0;

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
        //this.item = null;
        roomInventory = new RoomInventory();
       
    }

    /*
    public Room(String description, Item item)
    {
        this(description);

        this.item = item;

    }
     */
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
            //TODO : The following line does not work as intended
            if ((description.contains("desert")) && (getDesertStat() == 3))
            {
                returnString += " " + exit;
            }
            returnString += " " + exit;
        }
        return returnString;
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
     * @return Returning the status if the cave area
     */
    public int getCaveStat()
    {
        return caveStat;
    }

    /**
     * Define the exits of this room. Every direction either leads to another
     * room or is null (no exit there).
     */
    public void setExits(String direction, Room neighbor)
    {
        exits.put(direction, neighbor);   
    }

    /**
     * Get the exit directions in the current room
     * @param direction
     * @return
     */
    public Room getExit(String direction)
    {
        return exits.get(direction);

        
    }

    public void addItem(Item item)
    {
        roomInventory.put(item.getDescription(), item);

    }

    /**
     * Returns the item if it is available, otherwise it returns null.
     * @param name The name of the item to be returned.
     * @return The named item, or null if it is not in the room.
     */
    public Item getItem(String name)
    {
        return roomInventory.get(name);
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
        String returnString = "You are " + description + "\n"
                + getExitString();

        if (!description.contains("desert"))
        {
            //TODO: this is the fix
            if (roomInventory.getLongDescription() != null)
            {
                returnString += "\nYou also see: "
                        + "You are " + description + ".\n" + getExitString() 
                        + "\nItems in the room: " 
                        + roomInventory.getLongDescription();
                        
                      //  this.getItem().getAsString();

            }
            else
            {
                returnString += "\nThe room has no items";
            }
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

        String returnString = " ";

        String scenery = " ";

        if (description.contains("entance"))
        {
            if (caveStat == 0)
            {
                scenery += "You can hear vicious roars from the cave."
                        + "\n" + getExitString();
            }

            if (caveStat == 1)
            {
                scenery += "";
            }

        }

        if (description.contains("desert"))
        {

            if (desertStat == 0)
            {
                scenery += "You can only see sand as far as the eye can see. "
                        + "\n" + getExitString();

                

            }
            if (desertStat == 1)
            {
                scenery += "You see a small hole in the sand."
                        + "\n" + getExitString();

                
            }
            if (desertStat == 2)
            {
                scenery += "You see a big hole in the sand."
                        + "\n" + getExitString();

                
            }
            if (desertStat == 3)
            {
                scenery += "You see a huge hole in the sand, "
                        + "and an cave entrance under the sand."
                        + "\n" + getExitString();;

                

            }

        }
        return "You are " + description + scenery + returnString;

    }
    
     /**
     * Removes and returns the item if it is available, otherwise it returns null.
     * @param name The item to be removed.
     * @return The item if removed, null otherwise.
     */
    public Item removeItem(String name)
    {
        return roomInventory.remove(name);
    }   
}
