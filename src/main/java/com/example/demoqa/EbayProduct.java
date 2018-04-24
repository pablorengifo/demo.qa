package com.example.demoqa;

public class EbayProduct {

    private String name;
    private Double price;

    public EbayProduct(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }
}
