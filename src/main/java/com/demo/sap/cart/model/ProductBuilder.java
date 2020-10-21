package com.demo.sap.cart.model;

public class ProductBuilder {
    private String id;
    private String name;
    private String details;
    private double price;

    public ProductBuilder id(String id) {
        this.id = id;
        return this;
    }

    public ProductBuilder name(String name) {
        this.name = name;
        return this;
    }

    public ProductBuilder details(String details) {
        this.details = details;
        return this;
    }

    public ProductBuilder price(double price) {
        this.price = price;
        return this;
    }

    public Product createProduct() {
        return new Product(id, name, details, price);
    }
}