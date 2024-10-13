package org.example.entities;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record Product(
        @NotNull(message = "Product ID cannot be null") String id,
        @NotNull(message = "Product name cannot be null") String name,
        @NotNull(message = "Category cannot be null") Category category,
        @Min(value = 1, message = "Rating must be at least 1")
        @Max(value = 5, message = "Rating must not exceed 5") int rating,
        @NotNull(message = "Creation date cannot be null") LocalDate creationDate,
        @NotNull(message = "Modification date cannot be null") LocalDate modificationDate
) {
    @Override
    public String toString() {
        return String.format(
                "Product Details:\n" +
                        "ID: %s\n" +
                        "Name: %s\n" +
                        "Category: %s\n" +
                        "Rating: %d\n" +
                        "Creation Date: %s\n" +
                        "Modification Date: %s\n",
                id, name, category, rating, creationDate, modificationDate
        );
    }
}

// Constructor, getters, toString(), equals(), and hashCode() methods are automatically generated
