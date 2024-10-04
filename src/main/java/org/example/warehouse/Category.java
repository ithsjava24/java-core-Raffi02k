package org.example.warehouse;

import java.util.Objects;

public class Category {

    private String category;

    // Privat konstruktor
    private Category(String name) {
        if (name.isEmpty() || name.equals("") || name.equals("null") || name == null) {
            throw new IllegalArgumentException("Category name can't be null");
        }
        String formattedName = name.substring(0, 1).toUpperCase() + name.substring(1);

        this.category = formattedName;
    }

    // Getter för att hämta namnet på kategorin
    public String getName() {
        return this.category;
    }

    // Statisk metod för att skapa eller återanvända en instans av Category
    public static Category of (String name) {
        return new Category(name);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category category1)) return false;
        return Objects.equals(category, category1.category);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(category);
    }

}
