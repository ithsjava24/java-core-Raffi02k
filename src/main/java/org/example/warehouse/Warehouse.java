package org.example.warehouse;


import java.math.BigDecimal;
import java.util.UUID;

public class Warehouse {

    public static Warehouse getInstance() {
        return null;
    }

    public ProductRecord addProduct(UUID uuid, String products, Category meat, BigDecimal bigDecimal) {
        return null;
    }

    public boolean getProductsBy(Category meat) {
        return false;
    }

    public void updateProductPrice(UUID uuid, BigDecimal bigDecimal) {

    }

    public Object getProducts() {
        return null;
    }

    public boolean isEmpty() {
        return false;
    }

    public boolean getProductsGroupedByCategories() {
        return false;
    }

    public boolean getChangedProducts() {
        return false;
    }
}

