package com.pluralsight.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
// Represents a single sandwich, including its size, bread type, toasted
// preference, and all toppings chosen by the user
public class Sandwich {
    private final Size size;
    private final Bread bread;
    private final boolean toasted;
    // Core sandwich properties
    public Sandwich(Size size, Bread bread, boolean toasted) {
        this.size = size;
        this.bread = bread;
        this.toasted = toasted;
    }
    //List storing all toppings added to this sandwich
    private final List<Topping> toppings = new ArrayList<>();

    public Size getSize() {
        return size;
    }

    public Bread getBread() {
        return bread;
    }

    public boolean isToasted() {
        return toasted;
    }
    // Adds a single topping to the sandwich
    public void addTopping(Topping topping){
        if (topping == null){
            throw new IllegalArgumentException("topping is null");
        }
        toppings.add(topping);
    }
    // Adds multiple toppings at once
    public void addToppings(Topping... more){
       if (more == null){
           return;
       }
       for (Topping t : more) if (t != null) toppings.add(t);
    }
    // Returns an unmodifiable list so outside code cannot alter toppings directly
    public List<Topping>getToppings(){
        return Collections.unmodifiableList(toppings);
    }
    // Calculates the total price of the sandwich:
    // base size price + bread cost + topping costs
    public double getPrice(){
        double total = size.getBasePrice() + bread.getPriceModifier();
        for (Topping t : toppings){
            total += t.getPrice(size);
        }
        return total;
    }
    // Returns a formatted label
    public String getLabel(){
        String base = size.getLabel() + " " + bread.getDisplayName() + (toasted ? " (toasted)" : "");
            return base;
    }
    // When you print the Sandwich object directly
    // this label is displayed
    @Override
    public String toString(){
        return getLabel();
    }
    // Converts an ENUM_NAME string into a readable format like
    private static String toTitle(String enumName){
        String s = enumName.replace('_',' ').toLowerCase();
        String[] parts = s.split("\\s+");
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < parts.length; i ++){
            b.append(Character.toUpperCase(parts[i].charAt(0)))
                    .append(parts[i].substring(1));
            if (i < parts.length - 1) b.append(' ');
        }
        return b.toString();
    }
}
