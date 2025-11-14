package com.pluralsight.models;

public class PhillyCheeseSteak extends Sandwich {

    public PhillyCheeseSteak(){
        super(Size.EIGHT,Bread.WHITE,true);
        addTopping(
                Topping.ROAST_BEEF,
                Topping.AMERICAN,
                Topping.PEPPERS,
                Topping.MAYO
        );
    }
    @Override
    public String getLabel(){
        return "Philly CheeseSteak (" + super.getLabel() + ")";
    }
}
