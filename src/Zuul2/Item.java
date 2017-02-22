/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Zuul2;

/**
 *
 * @author rocio
 */
public class Item
{

    private String itemName;
    private int itemWeight;

    public String getItemSpec(String name, int weight)
    {
        itemName = name;
        itemWeight = weight;
        
        return itemName;
    }

    public int getWeight()
    {
        return itemWeight;
    }
}
