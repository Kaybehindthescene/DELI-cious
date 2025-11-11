package com.pluralsight.models;

public enum Topping {

    LETTUCE(ToppingType.REGULAR),
    TOMATO(ToppingType.REGULAR),
    ONION(ToppingType.REGULAR),
    PICKLES(ToppingType.REGULAR),
    OLIVES(ToppingType.REGULAR),
    PEPPERS(ToppingType.REGULAR),
    MUSHROOMS(ToppingType.REGULAR),
    JALAPENOS(ToppingType.REGULAR),

    MAYO(ToppingType.SAUCE),
    MUSTARD(ToppingType.SAUCE),
    RANCH(ToppingType.SAUCE),
    THOUSAND_ISLAND(ToppingType.SAUCE),
    VINAIGRETTE(ToppingType.SAUCE),

    HAM(ToppingType.MEAT),
    SALAMI(ToppingType.MEAT),
    ROAST_BEEF(ToppingType.MEAT),
    CHICKEN(ToppingType.MEAT),
    BACON(ToppingType.MEAT),

    AMERICAN(ToppingType.CHEESE),
    PROVOLONE(ToppingType.CHEESE),
    CHEDDAR(ToppingType.CHEESE),
    SWISS(ToppingType.CHEESE),

    EXTRA_MEAT(ToppingType.EXTRA_MEAT),
    EXTRA_CHEESE(ToppingType.EXTRA_CHEESE);

    private final ToppingType type;

    Topping(ToppingType type) {
        this.type = type;
    }

    public ToppingType getType() {
        return type;
    }

    public double getPrice(Size size){
        switch (type){
            case MEAT:
                if (size == Size.FOUR){
                    return 1.00;
                }
                if (size == Size.EIGHT){
                    return 2.00;
                }
                if (size == Size.TWELVE){
                    return 3.00;
                }
                break;
            case EXTRA_MEAT:
                if (size == Size.FOUR){
                    return 0.50;
                }
                if (size == Size.EIGHT){
                    return 1.00;
                }
                if (size == Size.TWELVE){
                    return 1.50;
                }
                break;
            case CHEESE:
                if (size == Size.FOUR){
                    return 0.75;
                }
                if (size == Size.EIGHT){
                    return 1.50;
                }
                if (size == Size.TWELVE){
                    return 2.25;
                }
                break;
            case EXTRA_CHEESE:
                if (size == Size.FOUR){
                    return 0.30;
                }
                if (size == Size.EIGHT){
                    return 0.60;
                }
                if (size == Size.TWELVE){
                    return 0.90;
                }
                break;
            case REGULAR:
            case SAUCE:
                return 0.00;
    }
    return 0.00;
}}
