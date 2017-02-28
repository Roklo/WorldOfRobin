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

    private String name;

    private String description;
    private double weight;

    /**
     * Creates an instance of Item with description and weight given by
     * parameters
     *
     * @param description
     * @param weight the weight of the item in kg
     */
    public Item(String description, double weight)
    {
        this.description = description;
        this.weight = weight;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public double getWeight()
    {
        return weight;
    }

    public void setWeight(double weight)
    {
        this.weight = weight;
    }

    public String getAsString()
    {
        String asString = "\njk"
                + this.getDescription()
                + " Weight: "
                + this.getWeight();
        return asString;
    }

}
