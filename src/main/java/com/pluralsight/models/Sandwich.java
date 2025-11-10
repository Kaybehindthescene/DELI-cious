package com.pluralsight.models;

public class Sandwich {
    private final Size size;
    private final Bread bread;
    private final boolean toasted;

    public Sandwich(Size size, Bread bread, boolean toasted) {
        this.size = size;
        this.bread = bread;
        this.toasted = toasted;
    }

    public Size getSize() {
        return size;
    }

    public Bread getBread() {
        return bread;
    }

    public boolean isToasted() {
        return toasted;
    }

    public double getPrice(){
        return size.getBasePrice() + bread.getPriceModifier();
    }

    public String getLabel(){
        return size.getLabel() + " " + bread.getDisplayName() + (toasted ? "toasted" : "");
    }

    @Override
    public String toString(){
        return getLabel();
    }
}
