package com.pluralsight.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sandwich {
    private final Size size;
    private final Bread bread;
    private final boolean toasted;

    public Sandwich(Size size, Bread bread, boolean toasted) {
        this.size = size;
        this.bread = bread;
        this.toasted = toasted;
    }
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
    public void addTopping(Topping topping){
        if (topping == null){
            throw new IllegalArgumentException("topping is null");
        }
        toppings.add(topping);
    }
    public void addToppings(Topping... more){
       if (more == null){
           return;
       }
       for (Topping t : more) if (t != null) toppings.add(t);
    }
    public List<Topping>getToppings(){
        return Collections.unmodifiableList(toppings);
    }

    public double getPrice(){
        double total = size.getBasePrice() + bread.getPriceModifier();
        for (Topping t : toppings){
            total += t.getPrice(size);
        }
        return total;
    }

    public String getLabel(){
        String base = size.getLabel() + " " + bread.getDisplayName() + (toasted ? " (toasted)" : "");
            return base;
    }

    @Override
    public String toString(){
        return getLabel();
    }

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
