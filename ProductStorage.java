package se.iths.kooshin.webshop;

import se.iths.kooshin.webshop.model.Product;
import java.util.List;

public interface ProductStorage {
    void saveProduct(Product product);
    List<Product> getProducts();
    Product getProduct(String articleNumber);
}