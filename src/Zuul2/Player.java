/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Zuul2;

import java.util.HashMap;

/**
 *
 * @author rocio
 */
public class Player
{
private RoomInventory roomInventory;
    private Room currentRoom;
    //private HashMap<String, Integer> inventory = new HashMap<>();
    private String name;
    
    private double maxWeight;
    


    /**
     * Creats an instance of Player with the name Jak.
     *
     * @param name
     */
    public Player(String name)
    {
        
        this.name = name;
        this.maxWeight = 1;        
    }

   

  /**
     * Returns a string describing the items that the player carries.
     * @return A description of the items held.
     */
    public String getItemsString()
    {
        return "You are carrying: " + roomInventory.getLongDescription();
    }
    
    
    /**
     * Tries to pick up the item from the current room.
     * @param itemName The item to be picked up.
     * @return If successful this method will return the item that was picked up.
     */
    public Item pickUpItem(String itemName)
    {
        if(canPickItem(itemName)) {
            Item item = currentRoom.removeItem(itemName);
            roomInventory.put(itemName, item);            
            return item;
        } 
        else {
            return null;
        }
    }
    
    /**
     * Tries to drop an item into the current room.
     * @param itemName The item to be dropped.
     * 
     * @return If successful this method will return the item that was dropped.
     */
    public Item dropItem(String itemName)
    {
        Item item = roomInventory.remove(itemName);
        if(item != null) {
            currentRoom.addItem(item);            
        }
        return item;
    }
    
    /**
     * Checks if we can pick up the given item. This depends on whether the item 
     * actually is in the current room and if it is not too heavy.
     * @parem itemName The item to be picked up.
     * @return true if the item can be picked up, false otherwise.
     */
    private boolean canPickItem(String itemName)
    {
        boolean canPick = true;
        Item item = currentRoom.getItem(itemName);
        if(item == null) {
            canPick = false;
        }
        else {
            double totalWeight = roomInventory.getTotalWeight() + item.getWeight();
            if(totalWeight > maxWeight) {
                canPick = false;
            }
        }
        return canPick;         
    }

}
