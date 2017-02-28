/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Zuul2;

import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author rocio
 */
public class RoomInventory
{

    private HashMap<String, Item> roomInventoryCollection;

    public RoomInventory()
    {
        roomInventoryCollection = new HashMap<>();
    }
/**
     * Return an Iterator over the items.
     * @return An Iterator.
     */
    public Iterator<Item> iterator()
    {
        return roomInventoryCollection.values().iterator();
    }
    
    /**
     * Remove the given item.
     * @param name The name of the item to be removed.
     */
    public Item remove(String name)
    {
        return roomInventoryCollection.remove(name);
    }
    
    /**
     * Put the given item in the list.
     * @param name The name of the item.
     * @param value The item.
     */
    public void put(String name, Item value)
    {
        roomInventoryCollection.put(name, value);
    }
    
    /**
     * Return the item with the given name
     * @param name The name of the item to return
     * @return The named item, or null if it is not in the list.
     */
    public Item get(String name)
    {
        return roomInventoryCollection.get(name);
    }
    
    /**
     * Return a string listing the descriptions of the
     * items in the list.
     */
    public String getLongDescription() 
    {
        String returnString = "";
        for(Iterator<Item> iter = roomInventoryCollection.values().iterator(); iter.hasNext(); )
            returnString += "  " + iter.next().getDescription();
        
        return returnString;     
    }
    
   
    /**
     * Return a string listing the names of the
     * items in the list.
     */
    /*
    public String getShortDescription() 
    {
        String returnString = "";
        for(Iterator<Item> iter = items.values().iterator(); iter.hasNext(); )
            returnString += " " + iter.next().getName();
        
        return returnString;     
    }
    */
    
    /**
     * Return the total weight of all items in the list.
     * @return The total weight
     */
    public double getTotalWeight()
    {
        double weight = 0;
        for(Iterator<Item> iter = roomInventoryCollection.values().iterator(); iter.hasNext(); ) {
            weight += iter.next().getWeight();
        }
        return weight;        
    }
    
    
    
}
