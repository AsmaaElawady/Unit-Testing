package test;

import JFree.DiscountCalculator;
import org.jfree.data.time.Week;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.*;
public class DiscountCalculatorTest {

    @Test
    public void testIsTheSpecialWeekWhenFalse() throws Exception {
        // Arrange
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.MARCH, 22);  // March 22, 2025
        Date date = calendar.getTime();
        Week week = new Week(date);

        // Act
        DiscountCalculator discountCalculator = new DiscountCalculator(week);

        // Assert
        assertFalse(discountCalculator.isTheSpecialWeek());
    }

    // Test missing cases ( JUNE, 23 is a date in week 26 )

    @Test
    public void testIsTheSpecialWeekWhenTrue() {
        // Arrange
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.JUNE, 23);  // June 23, 2025 (Week 26)
        Date date = calendar.getTime();
        Week week = new Week(date);
        DiscountCalculator discountCalculator = new DiscountCalculator(week);

        // Act & Assert
        assertTrue(discountCalculator.isTheSpecialWeek());
    }

    @Test
    public void testGetDiscountPercentageEvenWeek() {
        // Arrange
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.MARCH, 18);  // Assuming this is an even-numbered week
        Date date = calendar.getTime();
        Week week = new Week(date);
        DiscountCalculator discountCalculator = new DiscountCalculator(week);

        // Act & Assert
        assertEquals(7, discountCalculator.getDiscountPercentage());
    }

    @Test
    public void testGetDiscountPercentageOddWeek() {
        // Arrange
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.MARCH, 15);  // Assuming this is an odd-numbered week
        Date date = calendar.getTime();
        Week week = new Week(date);
        DiscountCalculator discountCalculator = new DiscountCalculator(week);

        // Act & Assert
        assertEquals(5, discountCalculator.getDiscountPercentage());
    }
}
