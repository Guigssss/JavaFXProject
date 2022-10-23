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
        return "Clothes{" +"name='" + super.getName() + '\'' +
                ", price=" + super.getPrice() +
                ", nbItems=" + super.getNbItems() +
                ", size=" + this.size +
                '}';
    }

    @Override
    public void applyDiscount() {
        super.setPrice(Math.round(super.getPrice()*0.7*100.0)/100.0);
    }
}
