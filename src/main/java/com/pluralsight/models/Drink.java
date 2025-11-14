package com.pluralsight.models;
// Represents the types of drinks available in the deli.
// Each drink has a display label and a base price.
public enum Drink {
    SODA("Soda", 1.79),
    WATER("Water", 1.29),
    JUICE("Juice", 2.29);

    private final String label;
    private final double price;

    Drink(String label, double price) {
        this.label = label;
        this.price = price;
    }

    public String getLabel(){
        return label;
    }
    public double getPrice(){
        return price;
    }
    // Parses user input into a Drink enum value.
    public static Drink parsed(String input){
        String s = input.trim().toUpperCase();
        return Drink.valueOf(s);
    }
}
