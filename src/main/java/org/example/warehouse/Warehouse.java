package org.example.warehouse;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Warehouse {

    private static Warehouse instance;
    private String name;
    private List<ProductRecord> products;

    // Privat konstruktor för att hindra publika instanser
    private Warehouse(String name) {
        this.name = name; // Sätt lagrets namn
        this.products = new ArrayList<>(); // Initiera produktlistan
        // Lägg till en standardprodukt om lagret skapas utan produkter
        addProduct(UUID.randomUUID(), "Default Product", Category.of("Default Category"), BigDecimal.valueOf(0.00));
    }

    // Statisk metod för att skapa eller returnera en instans av Warehouse med ett namn
    public static Warehouse getInstance(String name) {
        if (instance == null) {
            instance = new Warehouse(name);  // Skapa instansen med namnet
        }
        return instance;
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    public List<ProductRecord> getProducts() {
        return new ArrayList<>(products);
    }

    public void addProduct(UUID id, String name, Category category, BigDecimal price) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Product name can't be null or empty.");
        }
        if (category == null) {
            throw new IllegalArgumentException("Category can't be null."); // Kontrollera för null kategori
        }
        // Om priset är null, sätt det till BigDecimal.ZERO
        if (price == null) {
            price = BigDecimal.ZERO;
        }
        if (id == null) {
            id = UUID.randomUUID();
        }

        ProductRecord productRecord = new ProductRecord(id, name, category, price);
        products.add(productRecord); // Lägg till produkten i listan
    }

    // Getter för att hämta namnet på lagret
    public String getName() {
        return this.name;
    }
}
