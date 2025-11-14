package com.pluralsight.models;

public class BaconEggAndCheese extends Sandwich {

    public BaconEggAndCheese() {
        // Example: 8" White, toasted
        super(Size.EIGHT, Bread.WHITE, true);

        addToppings(
                Topping.BACON,
                Topping.EGG,          // if you have this topping
                Topping.AMERICAN     // cheese

        );
    }

    @Override
    public String getLabel() {
        return "Bacon Egg & Cheese (" + super.getLabel() + ")";
    }

}
