package com.pluralsight.models;

public enum DrinkSize {
    SMALL("Small", 2.00),
    MEDIUM("Medium", 2.50),
    LARGE("Large", 3.00);

    private final String label;
    private final double price;

    DrinkSize(String label, double price) {
        this.label = label;
        this.price = price;
    }

    public String getLabel() {
        return label;
    }

    public double getPrice() {
        return price;
    }
    
    public static DrinkSize parsed(String input){
        String s = input.trim().toLowerCase();
        switch (s){
            case "s": case "sm": case "small": return SMALL;
            case "m": case "med": case "medium": return MEDIUM;
            case "l": case "lg": case "large": return LARGE;
            default: throw new IllegalArgumentException("Unknown drink size: " + input + "Please try again");
        }
    }
}
