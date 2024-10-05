package org.example.warehouse;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

public class ProductRecord {
    private UUID id;
    private String name;
    private Category category;
    private BigDecimal price;

    public ProductRecord(UUID id, String name, Category category, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public UUID uuid() {
        return this.id; // Lägg till denna metod om den inte finns
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductRecord that)) return false;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(category, that.category) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, category, price);
    }
}
