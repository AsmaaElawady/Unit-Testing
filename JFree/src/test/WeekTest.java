package test;

import org.jfree.data.time.Week;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import org.jfree.data.time.Year;

public class WeekTest {
    Week week;

    private void arrange() {
        week = new Week();
    }
    @Test
    public void testWeekDefaultCtor() {
        arrange();
        assertEquals(2025, week.getYear().getYear());
        assertEquals(12, week.getWeek()); // to be updated with the current week
    }

    @Test
    public void testGetYear() {
        // Arrange
        Week week = new Week(15, 2026);

        // Act
        Year year = week.getYear();

        // Assert
        assertEquals(2026, year.getYear());
    }

    @Test
    public void testGetWeek() {
        // Arrange
        Week week = new Week(22, 2027);

        // Act
        int weekNumber = week.getWeek();

        // Assert
        assertEquals(22, weekNumber);
    }


}
