package com.pluralsight.models;

public enum Chips {
    CLASSIC("Chips - Classic", 1.49),
    BBQ("Chips - BBQ", 1.59),
    SOUR_CREAM("Chips - Sour Cream", 1.59);

    private final String label;
    private final double price;

    Chips(String label, double price) {
        this.label = label;
        this.price = price;
    }

    public String getLabel() {
        return label;
    }

    public double getPrice() {
        return price;
    }

    public static Chips parsed(String input){
        String s = input.trim().toUpperCase().replace(' ', '_');
        if (s.equals("BBQ")){
            s = "BBQ";
        }
        return Chips.valueOf(s);
    }
}
