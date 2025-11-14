package com.pluralsight.models;
// Represents the different categories of toppings that can be added to a sandwich.
// Each category helps determine:
//   - how toppings are grouped in the menu,
//   - how prices are calculated,
//   - and which toppings can be chosen at one time.
public enum ToppingType {

    REGULAR, SAUCE, MEAT, CHEESE, EXTRA_MEAT, EXTRA_CHEESE
}
