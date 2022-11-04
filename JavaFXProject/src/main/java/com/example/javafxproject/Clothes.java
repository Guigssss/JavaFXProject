package com.example.javafxproject;

public class Clothes extends Product {
    private int size;
    public Clothes(String name, double price, int nbItems, int size) throws IllegalArgumentException {
        super(name, price, nbItems);
        if(size > 34 && size < 54 ){
            this.size = size;
        }
        else{
            throw new IllegalArgumentException("Wrong Size");
        }
    }
    public int getSize() {
        return size;
    }
    public void setSize (int size)throws IllegalArgumentException {
        if(size > 34 && size < 54 ){
            this.size = size;
        }
        else{
            throw new IllegalArgumentException("Wrong Size");
        }
    }
    @Override
    public String toString() {
        return super.getName()+" - Price : "+super.getPrice()+" € Qty : "+super.getNbItems()+" Size : "+this.size;
    }

    @Override
    public void applyDiscount() {
        super.setPrice(Math.round(super.getPrice()*0.7*100.0)/100.0);
    }
}
