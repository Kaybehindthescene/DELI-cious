package com.pluralsight.models;

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

    public static Drink parsed(String input){
        String s = input.trim().toUpperCase();
        return Drink.valueOf(s);
    }
}
