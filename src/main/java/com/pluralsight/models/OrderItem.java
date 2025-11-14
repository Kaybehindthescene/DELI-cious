package com.pluralsight.models;
// Represents an item that can appear on a customer's order.
// This interface allows different kinds of items (Sandwiches, Drinks, Chips,
// Desserts, etc.) to be handled the same way when calculating totals
// or printing the receipt.
public interface OrderItem {
    String getLabel();
    double getUnitPrice();
    int getQuantity();


    default double getOrderTotal(){
        return getUnitPrice() * getQuantity();
    }
}
