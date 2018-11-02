package org.bitbucket.dannyvantol.rekenlokaal.util;

public class Product {
    private String product;
    private int value;

    public Product(String product, int value) {
        this.product = product;
        this.value = value;
    }

    public String getProduct() {
        return product;
    }

    public int getValue() {
        return value;
    }
}
