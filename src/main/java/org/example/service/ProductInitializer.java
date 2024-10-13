package org.example.service;

import org.example.entities.Product;
import org.example.entities.Category;
import java.time.LocalDate;

public class ProductInitializer {

    public static void addInitialProducts(Warehouse warehouse) {
        // Create some sample Xbox games as products
        Product product1 = new Product("1", "Halo Infinite", Category.FPS, 5, LocalDate.now().minusDays(10), LocalDate.now());
        Product product2 = new Product("2", "Forza Horizon 5", Category.RACING, 4, LocalDate.now().minusDays(20), LocalDate.now().minusDays(15));
        Product product3 = new Product("3", "Gears 5", Category.ACTION, 4, LocalDate.now().minusMonths(1), LocalDate.now().minusWeeks(2));
        Product product4 = new Product("4", "Sea of Thieves", Category.ADVENTURE, 4, LocalDate.now().minusMonths(2), LocalDate.now().minusWeeks(3));
        Product product5 = new Product("5", "FIFA 21", Category.SPORTS, 3, LocalDate.now().minusMonths(3), LocalDate.now().minusWeeks(1));

        // Add these products to the Warehouse
        warehouse.addProduct(product1);
        warehouse.addProduct(product2);
        warehouse.addProduct(product3);
        warehouse.addProduct(product4);
        warehouse.addProduct(product5);
    }
}
