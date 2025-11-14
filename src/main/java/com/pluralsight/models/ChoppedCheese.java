package com.pluralsight.models;

public class ChoppedCheeseSandwich extends Sandwich {

    public ChoppedCheeseSandwich() {
        // 8" WHITE, toasted
        super(Size.EIGHT, Bread.WHITE, true);

        addToppings(
                Topping.GROUND_BEEF,  // ← updated
                Topping.AMERICAN,
                Topping.LETTUCE,
                Topping.TOMATO,
                Topping.ONION,
                Topping.MAYO
        );
    }

    @Override
    public String getLabel() {
        return "Chopped Cheese (" + super.getLabel() + ")";
    }
}