package com.pluralsight.models;
// Represents a sandwich as an item inside an order.
// This class wraps a Sandwich object and adds quantity information,
// allowing the order system to treat sandwiches as "OrderItems"
// like drinks, chips, etc
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
    // Returns total cost (unit price × quantity).
    // Uses the default implementation provided by the OrderItem interface.
    @Override
    public double getOrderTotal() {
        return OrderItem.super.getOrderTotal();
    }
}
