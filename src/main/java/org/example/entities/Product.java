
package org.example.entities;
import java.time.LocalDate;
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
