package com.ebay.itemeligibility.model;

/**
 * Input request class item eligibility check
 */
public class Item {

    private String title;
    private int category;
    private String sellerName;
    private double price;


    public String getTitle() {
        return title;
    }

    public Item setTitle(String title) {
        this.title = title;
        return this;
    }

    public int getCategory() {
        return category;
    }

    public Item setCategory(int category) {
        this.category = category;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public Item setPrice(double price) {
        this.price = price;
        return this;
    }

    public String getSellerName() {
        return sellerName;
    }

    public Item setSellerName(String sellerName) {
        this.sellerName = sellerName;
        return this;
    }

}
