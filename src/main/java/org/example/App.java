/*package org.example;

import org.example.entities.Product;
import org.example.entities.Category;
import org.example.service.Warehouse;
import java.time.LocalDate;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        // Create a new Warehouse
        Warehouse warehouse = new Warehouse();
Scanner scanner =new Scanner(System.in);
boolean running =true;

        // Create a new product (game)
        Product haloInfinite = new Product(
                "1",                          // ID
                "Halo Infinite",               // Name
                Category.FPS,                  // Category (genre)
                9,                             // Rating
                LocalDate.of(2023, 9, 1),      // Creation date
                LocalDate.of(2023, 9, 1)       // Modification date
        );

        // Add the product to the warehouse

        warehouse.addProduct(haloInfinite);

        // Try adding a product with an empty name (this should throw an error)
        Product callOfDuty= new Product(
                "2",                          // ID
                "Call of Duty 6",                           // Invalid Name (empty)
                Category.FPS,              // Category (genre)
                8,                            // Rating
                LocalDate.of(2023, 9, 1),     // Creation date
                LocalDate.of(2023, 9, 1)      // Modification date
        );

        // This will throw an IllegalArgumentException
        warehouse.addProduct(callOfDuty);
    }
}*/

package org.example;

import org.example.entities.Product;
import org.example.entities.Category;
import org.example.service.Warehouse;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        // Create a new Warehouse
        Warehouse warehouse = new Warehouse();

        // Initialize the warehouse with predefined products
        initializeWarehouse(warehouse);
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        // Main loop for UI
        while (running) {
            // Display the menu
            System.out.println("\n===== Warehouse Management Menu =====");
            System.out.println("1. Add a new product");
            System.out.println("2. List all products");
            System.out.println("3. Find product by ID");
            System.out.println("4. Find products by category");
            System.out.println("5. Find products by creation date");
            System.out.println("6. Find products by modification date");
            System.out.println("7. Modify an existing product");

            System.out.println("8. Exit");
            System.out.print("Choose an option: ");

            // Read the user's choice
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    // Add a product
                    addProductUI(warehouse, scanner);
                    break;
                case 2:
                    // List all products
                    listAllProducts(warehouse);
                    break;
                case 3:
                    // Find a product by ID
                    findProductByIdUI(warehouse, scanner);
                    break;
                case 4:
                    // Find products by category
                    findProductsByCategoryUI(warehouse, scanner);
                    break;

                case 5:
                    findProductsCreatedAfterUI(warehouse, scanner);
                    break;
                case 6:
                    findProductsModifiedAfterCreationUI(warehouse);
                    break;

                case 7:
                    // Modify an existing product
                    modifyProductUI(warehouse, scanner);
                    break;
                case 8:
                    // Exit the program
                    running = false;
                    System.out.println("Exiting the program...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
    // Method to add a product
    static void addProductUI(Warehouse warehouse, Scanner scanner) {
        System.out.print("Enter product ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter product name: ");
        String name = scanner.nextLine();

        System.out.print("Enter product category (ACTION, SPORTS, FPS, RACING, ADVENTURE): ");
        String categoryInput = scanner.nextLine();
        Category category;
        try {
            category = Category.valueOf(categoryInput.toUpperCase());  // Convert input to enum
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid category. Product not added.");
            return;
        }

        System.out.print("Enter product rating (0-10): ");
        int rating = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        LocalDate creationDate = LocalDate.now();  // Set current date as the creation date
        LocalDate modificationDate = creationDate;  // Set the same date for modification initially

        // Create the product and add it to the warehouse
        Product product = new Product(id, name, category, rating, creationDate, modificationDate);
        warehouse.addProduct(product);

        System.out.println("Product added successfully!");
    }

    // Method to list all products
    static void listAllProducts(Warehouse warehouse) {
        List<Product> products = warehouse.getAllProducts();
        if (products.isEmpty()) {
            System.out.println("No products found.");
        } else {
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }

    // Method to find a product by its ID
    static void findProductByIdUI(Warehouse warehouse, Scanner scanner) {
        System.out.print("Enter product ID: ");
        String id = scanner.nextLine();

        // Use the Optional to handle the presence or absence of the product
        Optional<Product> productOpt = warehouse.getProductById(id);

        if (productOpt.isPresent()) {
            System.out.println("Product found: " + productOpt.get());
        } else {
            System.out.println("Product not found.");
        }
    }

    // Method to find products by category
    private static void findProductsByCategoryUI(Warehouse warehouse, Scanner scanner) {
        System.out.print("Enter category (ACTION, RPG, SPORTS, FPS, RACING, ADVENTURE): ");
        String categoryInput = scanner.nextLine();
        Category category;
        try {
            category = Category.valueOf(categoryInput.toUpperCase());  // Convert input to enum
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid category.");
            return;
        }

        // Retrieve the list of products in the specified category
        List<Product> products = warehouse.getProductsByCategory(category);
        if (products.isEmpty()) {
            System.out.println("No products found in this category.");
        } else {
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }

    // Method to find products created after a specific date
    private static void findProductsCreatedAfterUI(Warehouse warehouse, Scanner scanner) {
        System.out.print("Enter the date (yyyy-mm-dd): ");
        String dateString = scanner.nextLine();
        LocalDate date;
        try {
            date = LocalDate.parse(dateString);  // Parse the input to LocalDate
        } catch (Exception e) {
            System.out.println("Invalid date format. Please use yyyy-mm-dd.");
            return;
        }

        // Retrieve the list of products created after the specified date
        List<Product> products = warehouse.getProductsCreatedAfter(date);
        if (products.isEmpty()) {
            System.out.println("No products found created after this date.");
        } else {
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }

    // Method to find products modified after creation
    private static void findProductsModifiedAfterCreationUI(Warehouse warehouse) {
        // Retrieve the list of products modified after creation
        List<Product> products = warehouse.getProductsModifiedAfterCreation();
        if (products.isEmpty()) {
            System.out.println("No products have been modified after creation.");
        } else {
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }

    // Method to modify an existing product
    static void modifyProductUI(Warehouse warehouse, Scanner scanner) {
        System.out.print("Enter product ID to modify: ");
        String id = scanner.nextLine();

        // Check if product exists
        Optional<Product> productOpt = warehouse.getProductById(id);
        if (productOpt.isEmpty()) {
            System.out.println("Product not found.");
            return;
        }

        // Retrieve the existing product from the Optional
        Product product = productOpt.get();

        System.out.print("Enter new product name: ");
        String newName = scanner.nextLine();

        System.out.print("Enter new product category (ACTION, SPORTS, FPS, RACING, ADVENTURE): ");
        String categoryInput = scanner.nextLine();
        Category newCategory;
        try {
            newCategory = Category.valueOf(categoryInput.toUpperCase());  // Convert input to enum
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid category. Product not modified.");
            return;
        }

        System.out.print("Enter new product rating (0-10): ");
        int newRating;
        try {
            newRating = Integer.parseInt(scanner.nextLine());
            if (newRating < 0 || newRating > 10) {
                System.out.println("Rating must be between 0 and 10. Product not modified.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid rating format. Product not modified.");
            return;
        }

        boolean success = warehouse.updateProduct(id, newName, newCategory, newRating);
        if (success) {
            System.out.println("Product modified successfully!");
        } else {
            System.out.println("Failed to modify product.");
        }
    }




    private static void initializeWarehouse(Warehouse warehouse) {
        // Create and add 6 predefined products
        warehouse.addProduct(new Product("1", "Halo Infinite", Category.FPS, 9, LocalDate.of(2023, 9, 1), LocalDate.of(2023, 9, 1)));
        warehouse.addProduct(new Product("2", "Forza Horizon 5", Category.RACING, 8, LocalDate.of(2023, 10, 1), LocalDate.of(2023, 10, 1)));
        warehouse.addProduct(new Product("3", "FIFA 23", Category.SPORTS, 7, LocalDate.of(2023, 11, 1), LocalDate.of(2023, 11, 1)));
        warehouse.addProduct(new Product("5", "Call of Duty: Modern Warfare II", Category.ACTION, 8, LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 1)));
        warehouse.addProduct(new Product("6", "The Legend of Zelda: Breath of the Wild", Category.ADVENTURE, 10, LocalDate.of(2024, 2, 1), LocalDate.of(2024, 2, 1)));
    }



}


