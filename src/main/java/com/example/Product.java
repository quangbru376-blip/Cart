package com.example;

import java.io.Serializable;
import java.text.NumberFormat;

public class Product implements Serializable {
    private String productCode;
    private String name;
    private double price;

    public Product() {
        this.productCode = "";
        this.name = "";
        this.price = 0.0;
    }

    public Product(String productCode, String name, double price) {
        this.productCode = productCode;
        this.name = name;
        this.price = price;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    // Alias cho JSP EL: ${product.code}
    public String getCode() {
        return productCode;
    }

    public void setCode(String code) {
        this.productCode = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Alias cho JSP EL: ${product.description}
    public String getDescription() {
        return name;
    }

    public void setDescription(String description) {
        this.name = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPriceCurrencyFormat() {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(price);
    }
}
