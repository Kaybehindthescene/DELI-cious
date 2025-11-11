package com.pluralsight.models;

public class RegularItem implements OrderItem{
    private final String label;
    private final double unitPrice;
    private final int quantity;

    public RegularItem(String label, double unitPrice, int quantity) {
        if (label == null || label.isBlank()){
            throw new IllegalArgumentException("label");
        }
        if (unitPrice < 0){
            throw new IllegalArgumentException("unitPrice");
        }
        if (quantity < 0){
            throw new IllegalArgumentException("quantity");
        }
        this.label = label;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public double getUnitPrice() {
        return unitPrice;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }
}
