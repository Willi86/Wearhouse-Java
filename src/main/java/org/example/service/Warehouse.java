
package org.example.service;
// Class managing products
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import org.example.entities.Product;
import org.example.entities.Category;

import java.time.LocalDate;

public class Warehouse {
    // A map to store Product objects by their ID
    private final Map<String, Product> productsById;
    // Constructor: Initialize the map
    public Warehouse() {
        this.productsById = new ConcurrentHashMap<>();

    }

    // Method to add a new product
    public void addProduct(Product product) {
        // Check if the product ID already exists
        if (productsById.containsKey(product.id())) {
            throw new IllegalArgumentException("Product ID already exists.");
        }



        // Log the product being added
        System.out.println("Adding product: " + product);

        // Add the product to the map
        productsById.put(product.id(), product);
    }

    // Method to get all products
    public List<Product> getAllProducts() {
        // Return a list of all products from the map
        return new CopyOnWriteArrayList<>(productsById.values());
    }

    // Method to get a product by ID
    public Optional<Product> getProductById(String id) {
        return Optional.ofNullable(productsById.get(id));
    }

    // Method to get all products by category (genre)
    public List<Product> getProductsByCategory(Category category) {
        return productsById.values().stream()
                .filter(product -> product.category().equals(category))
                .collect(Collectors.toUnmodifiableList());
    }

    // Method to get all products created after a specific date
    public List<Product> getProductsCreatedAfter(LocalDate date) {
        return productsById.values().stream()
                .filter(product -> product.creationDate().isAfter(date))
                .collect(Collectors.toUnmodifiableList());
    }

    // Method to get all products modified after they were created
    public List<Product> getProductsModifiedAfterCreation() {
        return productsById.values().stream()
                .filter(product -> product.modificationDate().isAfter(product.creationDate()))
                .collect(Collectors.toUnmodifiableList());
    }





    // Method to update an existing product
    public boolean updateProduct(String id, String newName, Category newCategory, int newRating) {
        Product existingProduct = productsById.get(id);

        if (existingProduct != null) {
            Product updatedProduct = new Product(
                    existingProduct.id(),
                    newName,
                    newCategory,
                    newRating,
                    existingProduct.creationDate(),
                    LocalDate.now()  // Update the modification date to current date
            );
            productsById.put(id, updatedProduct); // Replace the old product with the updated one
            return true;
        } else {
            return false;
        }
    }
    }

