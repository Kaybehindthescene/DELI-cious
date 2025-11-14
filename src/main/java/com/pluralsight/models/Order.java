package com.pluralsight.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
// Represents a complete customer order containing sandwiches, drinks,
// chips, or any other items. Also calculates subtotal, tax, and total,
// and generates the final printed receipt.
public class Order {

    // NY-style tax rate used for all orders
    private static final double taxRate = 0.08875;

    // Stores all items added to the order
    private final List<OrderItem> items = new ArrayList<>();

    // Adds a sandwich wrapped in a SandwichItem
    public void addSandwich(Sandwich sandwich, int quantity) {
        items.add(new SandwichItem(sandwich, quantity));
    }

    // Adds a drink as a RegularItem using its size + drink label
    public void addDrink(Drink drink,DrinkSize size, int quantity){
        String label = size.getLabel() + " " + drink.getLabel();
        items.add(new RegularItem(label,size.getPrice(),quantity));
    }

    // Adds chips as a RegularItem
    public void addChips(Chips chips,int quantity){
        items.add(new RegularItem(chips.getLabel(),chips.getPrice(),quantity));
    }

    // Allows directly adding a custom OrderItem
    public void addItem(OrderItem item){
        if (item == null){
            throw new IllegalArgumentException("item");
        }
        items.add(item);
    }

    // Removes an item at a certain index; returns false if index is invalid
    public boolean removeAt(int index) {
        if (index < 0 || index >= items.size()) return false;
        items.remove(index);
        return true;
    }

    // Removes all items from the order
    public void clear(){
        items.clear();
    }
    // Returns an unmodifiable list so outside code cannot modify items directly
    public List<OrderItem> getItems(){
        return Collections.unmodifiableList(items);
    }
    // Quick check to see if the order has no items
    public boolean isEmpty(){
        return items.isEmpty();
    }
    // Adds up each item's total
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

    // Builds a formatted string receipt using item details and totals
    public String getReceipt() {
        StringBuilder sb = new StringBuilder();

        sb.append("*************************************\n");
        sb.append("        DELI-cious Sandwich Co.\n");
        sb.append("      123 Java Street, IDE City\n");
        sb.append("          (555) 123-DELI\n");
        sb.append("-------------------------------------\n");

        // Order number & date
        java.time.LocalDateTime now = java.time.LocalDateTime.now();
        sb.append(String.format("Order ID: #%04d     Date: %s%n",
                (int) (Math.random() * 9999), now.toLocalDate()));
        sb.append("-------------------------------------\n");
        //Print every item in the order
        for (OrderItem item : items) {
            if (item instanceof SandwichItem si) { //Special formatting for sandwiches
                Sandwich s = si.getSandwich();

                // main sandwich line
                sb.append(String.format("%dx %s%n", si.getQuantity(), s.getLabel()));

                // toppings under the sandwich
                for (Topping t : s.getToppings()) {
                    sb.append(String.format("   + %s%n", prettyName(t.name())));
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



    // Converts enum-looking text
    //previous method used in printing on the receipt before I changed
    //hiw the receipt looked
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
    // Another version used for printing toppings in the receipt
    private static String prettyName(String enumName) {
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
