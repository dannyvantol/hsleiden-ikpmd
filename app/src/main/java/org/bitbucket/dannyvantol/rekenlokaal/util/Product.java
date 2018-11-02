package org.bitbucket.dannyvantol.rekenlokaal.util;

import java.io.Serializable;

public class Product implements Serializable {
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
