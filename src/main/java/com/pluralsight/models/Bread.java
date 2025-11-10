package com.pluralsight.models;

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

    public static Bread parsed(String input){
        return Bread.valueOf(input.trim().toUpperCase());
    }
}
