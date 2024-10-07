package org.example.warehouse;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Category {

    private static final Map<String, Category> instances = new HashMap<>(); // Caching av instanser
    private String category;

    // Privat konstruktor
    private Category(String name) {
        // Namnet formateras för att börja med en versal
        this.category = name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    // Getter för att hämta namnet på kategorin
    public String getName() {
        return this.category;
    }

    // Statisk metod för att skapa eller återanvända en instans av Category
    public static Category of(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Category name can't be null");
        }
        // Kolla om en instans med samma namn redan finns
        return instances.computeIfAbsent(name, Category::new);// Skapa en ny instans om den inte finns
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category category1 = (Category) o;
        return Objects.equals(category, category1.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category);
    }
}
