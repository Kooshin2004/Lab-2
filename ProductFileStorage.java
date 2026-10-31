package se.iths.kooshin.webshop;

import se.iths.kooshin.webshop.model.Product;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class ProductFileStorage implements se.iths.kooshin.webshop.ProductStorage {
    private Path file = Path.of("product.text");

    @Override
    public void saveProduct(Product product) {
        String line = product.toFileLine() + "\n";
        try {
            Files.writeString(file, line,
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            IO.println("Kunde inte spara produkten: " + e.getMessage());
        }
    }

    @Override
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();

        if (!Files.exists(file)) {
            return products;
        }

        try {
            List<String> lines = Files.readAllLines(file);
            for (String line : lines) {
                if (!line.isBlank()) {
                    products.add(Product.fromFileLine(line));
                }
            }
        } catch (IOException e) {
            IO.println("Kunde inte läsa filen: " + e.getMessage());
        }

        return products;
    }

    @Override
    public Product getProduct(String articleNumber) {
        List<Product> products = getProducts();
        for (Product product : products) {
            if (product.getArticleNumber().equals(articleNumber)) {
                return product;
            }
        }
        return null;
    }
}