package com.pluralsight.models;

public enum Size {
    FOUR(4, 5.50),
    EIGHT(8, 7.00),
    TWELVE(12, 8.50);

    private final int inches;
    private final double basePrice;

    Size(int inches, double basePrice) {
        this.inches = inches;
        this.basePrice = basePrice;
    }

    public int getInches() {
        return inches;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public String getLabel(){
        return inches +"\"";
    }

    public static Size parsed(String input){
        String s = input.trim().toLowerCase();
        switch (s){
            case "4": case "4\"": case "four": case "small":return FOUR;
            case "8": case "8\"": case "eight": case "medium": return EIGHT;
            case "12": case "12\"": case "twelve": case "footlong": case "large": return TWELVE;
            default: throw new IllegalArgumentException("Invalid size " + input);

        }
    }
    public static Size ofinches(int inches){
        for (Size sizeOption : values()) if (sizeOption.inches == inches) return sizeOption;
        throw new IllegalArgumentException("Unsupported inches: " + inches);
    }

    @Override
    public String toString(){
        return getLabel();
    }


}
