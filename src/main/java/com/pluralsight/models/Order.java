package com.pluralsight.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {

    private static final double taxRate = 0.08875;

    private final List<Sandwich> items = new ArrayList<>();

    public void addSandwich(Sandwich sandwich, int quantity){
        items.add(new SandwichItem(sandwich,quantity));
    }
    public void addDrink(Drink drink,int quantity){
        items.add(new RegularItem(drink.getLabel(),drink.getPrice(),quantity));
    }
    public void addChips(Chips chips,int quantity){
        items.add(new RegularItem(chips.getLabel(),chips.getPrice(),quantity));
    }
    public void addItem(OrderItem item){
        if (item == null){
            throw new IllegalArgumentException("item");
            items.add(item);
        }
    }
    public boolean removeAt(int index) {
        if (index < 0 || index >= items.size()) return false;
        items.remove(index);
        return true;
    }


    public void clear(){
        items.clear();
    }

    public List<Sandwich> getItems(){
        return Collections.unmodifiableList(items);
    }

    public boolean isEmpty(){
        return items.isEmpty();
    }

    public double getSubtotal(){
        double sum = 0.0;
        for (RegularItem ri : items) sum += ri.getOrderTotal();
        return sum;
    }

    public double getTax(){
        return getSubtotal() * taxRate;
    }

    public double getTotal(){
        return getSubtotal() + getTax();
    }

}
