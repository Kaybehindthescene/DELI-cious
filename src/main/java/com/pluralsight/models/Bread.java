package com.pluralsight.models;
// Represents the different bread options available for a sandwich
//Each bread type has a display name and a price modifier that
// increases or decreases the base sandwich price.
public enum Bread {
    WHITE("White", 0.00),
    WHEAT("Wheat", 0.25),
    RYE("Rye", 0.35),
    SOURDOUGH("Sourdough",0.50),
    WRAP("Wrap", 0.40);

    private final String displayName;
    private final double priceModifier;

    Bread(String displayName, double priceModifier) {
        this.displayName = displayName;
        this.priceModifier = priceModifier;
    }

    public String getDisplayName() {
        return displayName;
    }

    public double getPriceModifier() {
        return priceModifier;
    }
    // Converts user input into a Bread enum
    public static Bread parsed(String input){
        return Bread.valueOf(input.trim().toUpperCase());
    }

    @Override
    public String toString(){
        return displayName;
    }
}
