package com.example.javafxproject;

public class Shoes extends Product {
    private int shoeSize;
    public Shoes(String name, double price, int nbItems, int shoeSize) throws IllegalArgumentException {
        super(name, price, nbItems);
        if(shoeSize > 34 && shoeSize < 54 ){
            this.shoeSize = shoeSize;
        }
        else{
            throw new IllegalArgumentException("Wrong Shoe Size !");
        }
    }
    public int getShoeSize() {
        return shoeSize;
    }
    public void setShoeSize (int shoeSize)throws IllegalArgumentException {
        if(shoeSize > 34 && shoeSize < 54 ){
            this.shoeSize = shoeSize;
        }
        else{
            throw new IllegalArgumentException("Wrong Shoe Size !");
        }
    }
    @Override
    public String toString() {
        return "Shoes{" +"name='" + super.getName() + '\'' +
                ", price=" + super.getPrice() +
                ", nbItems=" + super.getNbItems() +
                ", size=" + this.shoeSize +
                '}';
    }

    @Override
    public void applyDiscount() {
        super.setPrice(Math.round(super.getPrice()*0.8*100.0)/100.0);
    }
}
