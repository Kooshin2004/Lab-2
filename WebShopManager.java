package se.iths.kooshin.webshop.model;

import se.iths.kooshin.webshop.model.Discount;
import se.iths.kooshin.webshop.model.HalfPriceDiscount;
import se.iths.kooshin.webshop.model.Product;
import se.iths.kooshin.webshop.model.TenPercentDiscount;

import java.io.IOException;
import java.util.List;



public class WebShopManager {
    private se.iths.kooshin.webshop.ProductStorage productStorage;

    public WebShopManager(se.iths.kooshin.webshop.ProductStorage productStorage) {
        this.productStorage = productStorage;
    }


    void saveProductToStorage(Product product) {
        productStorage.saveProduct(product);
    }

    List<Product> getProductsFromStorage() {
        return productStorage.getProducts();
    }

    Product getProductFromStorage(String articleNumber) {
        return productStorage.getProduct(articleNumber);
    }

    void printAllTitels() {
        List<Product> products = getProductsFromStorage();
        for(Product product : products)
        {
            IO.println(product.getTitle());
        }
    }





    public void startWebshop() {
        String code = IO.readln("Ange rabattkod: ");

        Discount discount;
        if (code.equals("halfprice")) {
            discount = new HalfPriceDiscount();
        } else {
            discount = new TenPercentDiscount();
        }

        boolean running = true;
        while (running) {
            IO.println("1. Lägg till produkt");
            IO.println("2. Lista alla produkter");
            IO.println("3. Visa information om en produkt");
            IO.println("4. Avsluta");
            String choice = IO.readln("Ditt val: ");

            if (choice.equals("1")) {
                addProduct();
            } else if (choice.equals("2")) {
                listProducts(discount);
            } else if (choice.equals("3")) {
                showProduct(discount);
            } else if (choice.equals("4")) {
                IO.println("asvlustar!");
                running = false;
            } else {
                IO.println("Ogiltigt val, försök igen.");
            }
        }
    }

    private void addProduct() {
        String articleNumber = IO.readln("Mata in artikelnummer: ");
        String title = IO.readln("Mata in titel: ");
        double price = Double.parseDouble(IO.readln("Mata in pris: "));
        String description = IO.readln("Mata in beskrivning: ");

        Product product = new Product(articleNumber, title, price, description);
        saveProductToStorage(product);
        IO.println("Produkten har sparats.");
    }

    private void listProducts(Discount discount) {
        List<Product> products = getProductsFromStorage();

        if (products.isEmpty()) {
            IO.println("Det finns inga produkter än.");
            return;
        }

        for (Product product : products) {
            printProduct(product, discount);

        }
    }

    private void showProduct(Discount discount) {
        String articleNumber = IO.readln("Mata in artikelnummer: ");
        Product product = getProductFromStorage(articleNumber);

        if (product == null) {
            IO.println("Ingen produkt hittades med artikelnummer " + articleNumber);
        } else {
            printProduct(product, discount);
        }
    }

    public void printProduct(Product product, Discount discount) {
        double discountedPrice = discount.calculatePrice(product.getPrice());
        IO.println("Artikelnummer: " + product.getArticleNumber());
        IO.println("Titel: " + product.getTitle());
        IO.println("Pris: " + product.getPrice());
        IO.println("Rabatterat pris: " + discountedPrice);
        IO.println("Beskrivning: " + product.getDescription());
    }
}