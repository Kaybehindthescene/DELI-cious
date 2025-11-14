package com.pluralsight.models;

public class BltSandwich extends Sandwich{

    public BltSandwich(){
        super(Size.EIGHT,Bread.WHITE,true);
        addTopping(
                Topping.BACON,
                Topping.CHEDDAR,
                Topping.LETTUCE,
                Topping.TOMATO,
                Topping.MAYO
        );
    }
    @Override
    public String getLabel(){
        return "BLT (" + super.getLabel() + ")";
    }
}
