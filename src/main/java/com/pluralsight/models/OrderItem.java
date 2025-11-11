package com.pluralsight.models;

public interface OrderItem {
    String getLabel();
    double getUnitPrice();
    int getQuantity();


    default double getOrderTotal(){
        return getUnitPrice() * getQuantity();
    }
}
