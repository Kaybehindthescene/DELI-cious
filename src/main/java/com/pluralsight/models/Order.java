package com.pluralsight.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {

    private static final double taxRate = 0.08875;

    private final List<OrderItem> items = new ArrayList<>();


    public void addSandwich(Sandwich sandwich, int quantity) {
        items.add(new SandwichItem(sandwich, quantity));
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
        }
        items.add(item);
    }
    public boolean removeAt(int index) {
        if (index < 0 || index >= items.size()) return false;
        items.remove(index);
        return true;
    }


    public void clear(){
        items.clear();
    }

    public List<OrderItem> getItems(){
        return Collections.unmodifiableList(items);
    }

    public boolean isEmpty(){
        return items.isEmpty();
    }

    public double getSubtotal(){
        double sum = 0.0;
        for (OrderItem ri : items) sum += ri.getOrderTotal();
        return sum;
    }

    public double getTax(){
        return getSubtotal() * taxRate;
    }

    public double getTotal(){
        return getSubtotal() + getTax();
    }

    // inside Order.java
    public String getReceipt() {
        StringBuilder sb = new StringBuilder();

        sb.append("*************************************\n");
        sb.append("        DELI-cious Sandwich Co.\n");
        sb.append("      123 Java Street, IDE City\n");
        sb.append("          (555) 123-DELI\n");
        sb.append("-------------------------------------\n");

        java.time.LocalDateTime now = java.time.LocalDateTime.now();
        sb.append(String.format("Order ID: #%04d     Date: %s%n",
                (int) (Math.random() * 9999), now.toLocalDate()));
        sb.append("-------------------------------------\n");

        for (OrderItem item : items) {
            if (item instanceof SandwichItem si) {
                Sandwich s = si.getSandwich();

                // main sandwich line
                sb.append(String.format("%dx %s%n", si.getQuantity(), s.getLabel()));

                // toppings under the sandwich
                for (Topping t : s.getToppings()) {
                    sb.append(String.format("   + %s%n", pretty(t.name())));
                }

                // sandwich total aligned to the right
                sb.append(String.format("                           $%.2f%n", si.getOrderTotal()));
                sb.append("\n");
            } else {
                // Regular items (drinks, chips, etc.)
                sb.append(String.format("%dx %-25s $%.2f%n",
                        item.getQuantity(), item.getLabel(), item.getOrderTotal()));
            }
        }

        sb.append("-------------------------------------\n");
        sb.append(String.format("Subtotal                    $%.2f%n", getSubtotal()));
        sb.append(String.format("Tax (8.875%%)                $%.2f%n", getTax()));
        sb.append(String.format("Total                       $%.2f%n", getTotal()));
        sb.append("*************************************\n");
        sb.append("      Thanks for stopping by!\n");
        sb.append("*************************************\n");

        return sb.toString();
    }




    private String formatName(String raw) {
        String lower = raw.toLowerCase().replace('_', ' ');
        String[] parts = lower.split(" ");
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < parts.length; i++) {
            if (parts[i].isEmpty()) continue;
            result.append(Character.toUpperCase(parts[i].charAt(0)))
                    .append(parts[i].substring(1));
            if (i < parts.length - 1) result.append(" ");
        }
        return result.toString();

}
    // "ROAST_BEEF" -> "Roast Beef"
    private static String pretty(String enumName) {
        String[] parts = enumName.toLowerCase().replace('_', ' ').split("\\s+");
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < parts.length; i++) {
            if (parts[i].isEmpty()) continue;
            b.append(Character.toUpperCase(parts[i].charAt(0)))
                    .append(parts[i].substring(1));
            if (i < parts.length - 1) b.append(' ');
        }
        return b.toString();
    }

}
