package com.example.javafxproject;

public class Accessories extends Product{

    public Accessories(String name, double price, int nbItems){
        super(name, price, nbItems);
    }
    @Override
    public String toString() {
        return "Accessories{" +"name='" + super.getName() + '\'' +
                ", price=" + super.getPrice() +
                ", nbItems=" + super.getNbItems() +
                '}';
    }

    @Override
    public void applyDiscount() {
        super.setPrice(Math.round(super.getPrice()*0.5*100.0)/100.0);
    }
}