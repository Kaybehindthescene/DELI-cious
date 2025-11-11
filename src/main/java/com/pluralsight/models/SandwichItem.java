package com.pluralsight.models;

public class SandwichItem implements OrderItem{

    private final Sandwich sandwich;
    private final int quantity;

    public SandwichItem(Sandwich sandwich, int quantity) {
        if (sandwich == null){
            throw new IllegalArgumentException("The sandwich is null");
        }
        if (quantity <= 0){
            throw new IllegalArgumentException("You have to order at least 1 sandwich");
        }
        this.sandwich = sandwich;
        this.quantity = quantity;
    }

    public Sandwich getSandwich(){
        return sandwich;
    }

    @Override
    public String getLabel(){
        return sandwich.getLabel();
    }

    @Override
    public double getUnitPrice(){
        return sandwich.getPrice();
    }
    @Override
    public int getQuantity(){
        return quantity;
    }

    @Override
    public double getOrderTotal() {
        return OrderItem.super.getOrderTotal();
    }
}
