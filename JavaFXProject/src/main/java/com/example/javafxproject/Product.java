package com.example.javafxproject;

public abstract class Product implements Discount{
    private int number;
    private String name;
    private double price;
    private double discountPrice;
    private int nbItems;
    static double income = 0;
    static int cpt = 0;
    public Product(String name, double price, int nbItems)throws IllegalArgumentException{
        this.name = name;
        setPrice(price);
        setNbItems(nbItems);
        number=++cpt;
    }
    public int getNumber() {
        return number;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public double getDiscountPrice(){
        return this.discountPrice;
    }
    public int getNbItems() {
        return nbItems;
    }
    public static double getIncome() {
        return income;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(double price)throws IllegalArgumentException {
        if(price>0){
            this.price = price;
        }
        else{
            throw new IllegalArgumentException("Negative price !");
        }
    }
    public void setDiscountPrice(double discountPrice){
        this.discountPrice = discountPrice;
    }
    public void setNbItems(int nbItems) {
        if(nbItems>=0){
            this.nbItems=nbItems;
        }
        else{
            throw new IllegalArgumentException("Negative quantity !");
        }
    }
    @Override
    public String toString() {
        return "Product{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", nbItems=" + nbItems +
                '}';
    }
    public void sell(int nbItems, boolean isDiscounted) throws IllegalArgumentException{
        if(this.nbItems-nbItems>=0){
            this.nbItems -= nbItems;
            if(isDiscounted){
                income+=nbItems*this.discountPrice;
            }
            else{
                income+=nbItems*this.price;
            }
        }
        else{
            int missing = nbItems-this.nbItems;
            throw new IllegalArgumentException("Product Unavailable : " + this.name + " missing quantity : " + missing);
        }
    }
    public void purchase(int nbItems){
        this.nbItems+=nbItems;
    }
}
