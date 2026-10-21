package se.iths.viggo.webshop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import se.iths.viggo.webshop.model.Discount;
import se.iths.viggo.webshop.model.HalfPriceDiscount;
import se.iths.viggo.webshop.model.TenPercentDiscount;

public class DiscountTest {

    // Half price discount test
    @Test
    @DisplayName("Half price of 100 should be 50")
    void halfPriceTestWithHundred() {
        Discount discount = new HalfPriceDiscount();

        double result = discount.calculatePrice(100);

        Assertions.assertEquals(50, result);
    }

    @Test
    @DisplayName("Half price of 25 should be 12.5")
    void halfPriceTestWithTwentyFive() {
        Discount discount = new HalfPriceDiscount();

        double result = discount.calculatePrice(25);

        Assertions.assertEquals(12.5, result);
    }

    // 10% off discount test
    @Test
    @DisplayName("10% of 100 should be 90")
    void tenPercentDiscountTestWithHundred() {
        Discount discount = new TenPercentDiscount();

        double result = discount.calculatePrice(100);

        Assertions.assertEquals(90, result);
    }

    @Test
    @DisplayName("10% of 200 should be 180")
    void tenPercentDiscountTestWithTwoHundred() {
        Discount discount = new TenPercentDiscount();

        double result = discount.calculatePrice(200);

        Assertions.assertEquals(180, result);
    }
}
