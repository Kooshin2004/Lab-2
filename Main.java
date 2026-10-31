package se.iths.kooshin.webshop; 

public class Main {
    public static void main(String[] args) {
    
        ProductStorage storage = new ProductFileStorage();

      
        WebShopManager manager = new WebShopManager(storage);

       
        manager.startWebshop();
    }
}
