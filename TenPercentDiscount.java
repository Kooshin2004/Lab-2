package se.iths.kooshin.webshop.model;

public class TenPercentDiscount extends se.iths.kooshin.webshop.model.Discount {

    public TenPercentDiscount() {
        super("10 procent rabatt");
    }

    @Override
    public double calculatePrice(double originalPrice) {
        return originalPrice * 0.9;
    }
}