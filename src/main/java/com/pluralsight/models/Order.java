package com.pluralsight.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {

    private static final double taxRate = 0.08875;

    private final List<Sandwich> sandwiches = new ArrayList<>();

    public void addSandwich(Sandwich sandwich){
        if (sandwich == null)throw new IllegalArgumentException("sandwich is null");
        sandwiches.add(sandwich);
    }

    public boolean removeSandwich(int index){
        if (index < 0 || index >= sandwiches.size()) return false;
        sandwiches.remove(index);
        return true;
    }

    public void clear(){
        sandwiches.clear();
    }

    public List<Sandwich> getSandwiches(){
        return Collections.unmodifiableList(sandwiches);
    }

    public boolean isEmpty(){
        return sandwiches.isEmpty();
    }

    public double getSubtotal(){
        double sum = 0.0;
        for (Sandwich s : sandwiches) sum += s.getPrice();
        return sum;
    }

    public double getTax(){
        return getSubtotal() * taxRate;
    }

    public double getTotal(){
        return getSubtotal() + getTax();
    }

}
