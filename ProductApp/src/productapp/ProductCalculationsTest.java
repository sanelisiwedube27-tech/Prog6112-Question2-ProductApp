package productapp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProductCalculationsTest {


    @Test
    public void GetVat_WithValidData_ReturnsCorrectValue() {

        ProductCalculations calculation =
                new ProductCalculations(3500);

        double expected = 525.0;

        double actual = calculation.GetVat();

        assertEquals(expected, actual, 0.001);
    }


    @Test
    public void GetTotal_WithValidData_ReturnsCorrectValue() {

        ProductCalculations calculation =
                new ProductCalculations(3500);

        double expected = 4200.0;

        double actual = calculation.GetTotal();

        assertEquals(expected, actual, 0.001);
    }
}
