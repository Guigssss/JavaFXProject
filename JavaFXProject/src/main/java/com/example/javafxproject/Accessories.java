package com.example.javafxproject;

public class Accessories extends Product{

    public Accessories(String name, double price, int nbItems){
        super(name, price, nbItems);
    }
    @Override
    public String toString() {
        return super.getName()+" - Price : "+super.getPrice()+" € Qty : "+super.getNbItems();
    }

    @Override
    public void applyDiscount() {
        super.setPrice(Math.round(super.getPrice()*0.5*100.0)/100.0);
    }
}