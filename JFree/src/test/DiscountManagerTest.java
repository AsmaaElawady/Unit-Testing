package test;
import JFree.DiscountManager;
import JFree.IDiscountCalculator;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;
import static org.junit.Assert.*;

public class DiscountManagerTest {

    @Test
    public void testCalculatePriceWhenDiscountsSeasonIsFalse() throws Exception {
        // Arrange
        boolean isDiscountsSeason = false;
        double originalPrice = 100.0;
        double expectedPrice = 100.0;

        Mockery mockingContext = new Mockery();
        IDiscountCalculator mockedDependency = mockingContext.mock(IDiscountCalculator.class);
        mockingContext.checking(new Expectations(){
            {
                // make sure that none of the functions are called
            }
        });
        DiscountManager discountManager = new DiscountManager(isDiscountsSeason, mockedDependency);
        // Act
        double actualPrice = discountManager.calculatePriceAfterDiscount(originalPrice);

        // Assert
        // make sure that mocking Expectations Is Satisfied
        // make sure that the actual value exactly equals the expected value
        assertEquals(expectedPrice, actualPrice, 0.01);
        mockingContext.assertIsSatisfied();
    }

    @Test
    public void testCalculatePriceWhenDiscountsSeasonIsTrueAndSpecialWeekIsTrue() throws Exception {
        // Arrange
        boolean isDiscountsSeason = true;
        double originalPrice = 100.0;
        double expectedPrice = 80.0; // 20% discount

        Mockery mockingContext = new Mockery();
        IDiscountCalculator mockedDependency = mockingContext.mock(IDiscountCalculator.class);

        mockingContext.checking(new Expectations() {{
            oneOf(mockedDependency).isTheSpecialWeek();
            will(returnValue(true));
        }});

        DiscountManager discountManager = new DiscountManager(isDiscountsSeason, mockedDependency);

        // Act
        double actualPrice = discountManager.calculatePriceAfterDiscount(originalPrice);

        // Assert
        assertEquals(expectedPrice, actualPrice, 0.01);
        mockingContext.assertIsSatisfied();
    }

    @Test
    public void testCalculatePriceWithEvenWeekDiscount() {
        // Arrange
        boolean isDiscountsSeason = true;
        double originalPrice = 100.0;
        double expectedPrice = 0.93 * originalPrice; // discount is 7% for even weeks

        Mockery mockingContext = new Mockery();
        IDiscountCalculator mockedDependency = mockingContext.mock(IDiscountCalculator.class);

        mockingContext.checking(new Expectations() {{
            oneOf(mockedDependency).isTheSpecialWeek();
            will(returnValue(false));
            oneOf(mockedDependency).getDiscountPercentage();
            will(returnValue(7));
        }});

        DiscountManager discountManager = new DiscountManager(isDiscountsSeason, mockedDependency);

        // Act
        double actualPrice = discountManager.calculatePriceAfterDiscount(originalPrice);

        // Assert
        assertEquals(expectedPrice, actualPrice, 0.01);
        mockingContext.assertIsSatisfied();
    }

    @Test
    public void testCalculatePriceWithOddWeekDiscount() {
        boolean isDiscountsSeason = true;
        double originalPrice = 100.0;
        double expectedPrice = originalPrice * 0.95; // 5% discount for odd weeks

        Mockery mockingContext = new Mockery();
        IDiscountCalculator mockedDependency = mockingContext.mock(IDiscountCalculator.class);

        mockingContext.checking(new Expectations() {{
            oneOf(mockedDependency).isTheSpecialWeek();
            will(returnValue(false));
            oneOf(mockedDependency).getDiscountPercentage();
            will(returnValue(5));
        }});

        DiscountManager discountManager = new DiscountManager(isDiscountsSeason, mockedDependency);
        double actualPrice = discountManager.calculatePriceAfterDiscount(originalPrice);

        assertEquals(expectedPrice, actualPrice, 0.01);
        mockingContext.assertIsSatisfied();
    }

    @Test
    public void testCalculatePriceWithZeroOriginalPrice() {
        boolean isDiscountsSeason = true;
        double originalPrice = 0.0;
        double expectedPrice = 0.0;

        Mockery mockingContext = new Mockery();
        IDiscountCalculator mockedDependency = mockingContext.mock(IDiscountCalculator.class);

        mockingContext.checking(new Expectations() {{
            oneOf(mockedDependency).isTheSpecialWeek();
            will(returnValue(false));
            oneOf(mockedDependency).getDiscountPercentage();
            will(returnValue(7));
        }});

        DiscountManager discountManager = new DiscountManager(isDiscountsSeason, mockedDependency);
        double actualPrice = discountManager.calculatePriceAfterDiscount(originalPrice);

        assertEquals(expectedPrice, actualPrice, 0.01);
        mockingContext.assertIsSatisfied();
    }

    @Test
    public void testCalculatePriceWithNegativeOriginalPrice() {
        boolean isDiscountsSeason = true;
        double originalPrice = -100.0;
        double expectedPrice = 0.0; // Assuming discount doesn't apply to negative values

        Mockery mockingContext = new Mockery();
        IDiscountCalculator mockedDependency = mockingContext.mock(IDiscountCalculator.class);

        mockingContext.checking(new Expectations() {{
            oneOf(mockedDependency).isTheSpecialWeek();
            will(returnValue(false));
            oneOf(mockedDependency).getDiscountPercentage();
            will(returnValue(7));
        }});

        DiscountManager discountManager = new DiscountManager(isDiscountsSeason, mockedDependency);
        double actualPrice = discountManager.calculatePriceAfterDiscount(originalPrice);

        assertEquals(expectedPrice, actualPrice, 0.01);
        mockingContext.assertIsSatisfied();
    }
}
