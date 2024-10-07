package org.example.warehouse;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Warehouse {

    private static Warehouse instance;
    private String name;
    private List<ProductRecord> products; //Sparar produkter
    private List<ProductRecord> changedProducts; //Spåra ändrade produkter

    // Privat konstruktor för att hindra publika instanser
    private Warehouse(String name) {
        this.name = name; // Sätt lagrets namn
        this.products = new ArrayList<>(); // Initiera produktlistan
        this.changedProducts = new ArrayList<>(); // Initiera ändringslistan
    }

    // Statisk metod för att skapa eller returnera en instans av Warehouse med ett namn
    public static Warehouse getInstance(String name) {
        if (instance == null) {
            instance = new Warehouse(name);  // Skapa instansen med namnet
        } else instance.clearProduct();
        return instance;
    }

    private void clearProduct() {
        products.clear();
        changedProducts.clear();
    }

    //Metod för att skapa en instans utan namn
    public static Warehouse getInstance() {
        return getInstance("MyStore");
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    public List<ProductRecord> getProducts() {
        return Collections.unmodifiableList(new ArrayList<>(products)); // Returnera en oföränderlig lista
    }

    public ProductRecord addProduct(UUID id, String name, Category category, BigDecimal price) {


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
            id = UUID.randomUUID(); // Generera ett nytt UUID
        }

        // Skapa en lokal variabel för att undvika "Variable used in lambda expression should be final or effectively final"
        UUID productId = id;
        // Kontrollera om produkten redan finns med samma ID
        if (products.stream().anyMatch(product -> product.uuid().equals(productId))) {
            throw new IllegalArgumentException("Product with that id already exists, use updateProduct for updates.");
        }

        ProductRecord productRecord = new ProductRecord(id, name, category, price);
        products.add(productRecord); // Lägg till produkten i listan
        return productRecord; // Returnera den tillagda produkten
    }

    public Optional<ProductRecord> getProductById(UUID id) {
        return products.stream()
                .filter(product -> product.uuid().equals(id))
                .findFirst(); // Returnera Optional<ProductRecord>
    }

    // Getter för att hämta namnet på lagret
    public String getName() {
        return this.name;
    }

    public List<ProductRecord> getProductsBy(Category category) {
        return products.stream()
                .filter(product -> product.category().equals(category))
                .collect(Collectors.toList());
    }


    public void updateProductPrice(UUID id, BigDecimal newPrice) {
        // Kontrollera om produkten med det angivna ID:t finns
        ProductRecord productToUpdate = products.stream()
                .filter(product -> product.uuid().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Product with that id doesn't exist."));

        // Om produkten finns, ändra dess pris
        productToUpdate.setPrice(newPrice);

        // Lägg till produkten till ändrade produkter
        changedProducts.add(productToUpdate);
    }

    public List<ProductRecord> getChangedProducts() {
        return new ArrayList<>(changedProducts); // Returnera en kopia av ändringslistan
    }

    public Map<Category, List<ProductRecord>> getProductsGroupedByCategories() {
        return products.stream()
                .collect(Collectors.groupingBy(ProductRecord::category));
    }

}
