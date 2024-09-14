// here will have Product:
//    - id (a unique identifier for each game)
//    - name (the name of the game)
//    - category (the genre of the game, using Category enum)
//    - rating (a score from 0 to 10)
//    - creationDate (the date the game was added)
//    - modificationDate (the last date the game was modified)
//
//    Method:
//        - Getters for all the fields (so we can access them).
//        - A way to set the modificationDate when changes are made.

package org.example.entities;
import java.time.LocalDate;
/*
public class Product {
    private final String id;
    private final String name;
    private final Category category;
    private final int rating;
    private final LocalDate creationDate;
    private LocalDate modificationDate;
    public Product(String id, String name, Category category, int rating, LocalDate creationDate, LocalDate modificationDate) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.rating = rating;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
    }
    // Getters for each field (no setters because we want immutability except for modificationDate)
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public int getRating() {
        return rating;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public LocalDate getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(LocalDate modificationDate) {
        this.modificationDate = modificationDate;
    }

    // Overriding the toString method to provide a meaningful output
    @Override
    public String toString() {
        return "Game ID:" + id + '\n' +
                "Name:" + name + '\n' +
                "Category:" + category + '\n' +
                "Rating:" + rating + '\n' +
                "Creation Date:" + creationDate + '\n' +
                "Last Modified:" + modificationDate;
    }

}

*/




public record Product(String id, String name, Category category, int rating, LocalDate creationDate, LocalDate modificationDate) {
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
