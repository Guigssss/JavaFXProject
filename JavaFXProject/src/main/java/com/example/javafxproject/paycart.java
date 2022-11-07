package com.example.javafxproject;

public class paycart {
    private Product p;
    private Integer cartquantity;
    private Boolean isDiscounted;
    public paycart(Product p,Integer cartquantity, Boolean isDiscounted){
        this.cartquantity=cartquantity;
        this.isDiscounted=isDiscounted;
        this.p=p;
    }

    public Product getP() {
        return p;
    }

    public void setP(Product p) {
        this.p = p;
    }

    public Integer getCartquantity() {
        return cartquantity;
    }

    public void setCartquantity(Integer cartquantity) {
        this.cartquantity = cartquantity;
    }

    public Boolean getDiscounted() {
        return isDiscounted;
    }

    public void setDiscounted(Boolean discounted) {
        isDiscounted = discounted;
    }
}
