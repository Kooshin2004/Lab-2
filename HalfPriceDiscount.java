package se.iths.kooshin.webshop.model;

public class HalfPriceDiscount extends se.iths.kooshin.webshop.model.Discount {

    public HalfPriceDiscount() {
        super("Halfprice");
    }

    @Override
    public double calculatePrice(double originalPrice) {
        return originalPrice / 2;
    }
}