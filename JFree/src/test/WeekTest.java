package test;
import org.jfree.data.time.Week;
import org.jfree.data.time.Year;
import org.junit.Test;

import static org.junit.Assert.*;
import org.junit.jupiter.api.Assertions;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


public class WeekTest {

    @Test
    public void testDefaultConstructor() {
        // Arrange
        Week week = new Week();
        Calendar calendar = Calendar.getInstance();
        int expectedYear = calendar.get(Calendar.YEAR);
        int expectedWeek = calendar.get(Calendar.WEEK_OF_YEAR);

        // Act & Assert
        assertEquals(expectedYear, week.getYear().getYear());
        assertEquals(expectedWeek, week.getWeek());
    }

    @Test
    public void testConstructorWithWeekAndYear() {
        // Arrange & Act
        Week week = new Week(15, 2025);

        // Assert
        assertEquals(15, week.getWeek());
        assertEquals(2025, week.getYear().getYear());
    }

    @Test
    public void testConstructorWithInvalidWeek() {
        // Assert & Act
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Week(53, 2021); // 2021 only has 52 weeks, so this should fail
        });
    }

    @Test
    public void testConstructorWithWeek0() {
        // Assert & Act
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Week(0, 2021); // weeks started from 1, so this should fail
        });
    }

    @Test
    public void testConstructorWithInvalidYear() {
        // Assert & Act
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Week(1, -1); // invalid year
        });
    }

    @Test
    public void testConstructorWithDate() {
        // Arrange
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.MARCH, 15);
        Date date = calendar.getTime();

        // Act
        Week week = new Week(date);

        // Assert
        assertEquals(2025, week.getYear().getYear());
        assertEquals(calendar.get(Calendar.WEEK_OF_YEAR), week.getWeek());
    }

    @Test
    public void testConstructorWithEndOfYear() {
        // Arrange
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.DECEMBER, 31); // Last day of 2025
        Date date = calendar.getTime();

        // Act
        Week week = new Week(date);

        // Assert
        assertEquals(2025, week.getYear().getYear());
        assertEquals(calendar.get(Calendar.WEEK_OF_YEAR), week.getWeek());
    }

    @Test
    public void testConstructorWithStartOfYear() {
        // Arrange
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.JANUARY, 1); // First day of the year
        Date date = calendar.getTime();

        // Act
        Week week = new Week(date);

        // Assert
        assertEquals(2025, week.getYear().getYear());
        assertEquals(calendar.get(Calendar.WEEK_OF_YEAR), week.getWeek());
    }

    @Test
    public void testConstructorWithWeekAndYearObject() {
        // Arrange & Act
        Year year = new Year(2025);
        Week week = new Week(15, year);

        // Assert
        assertEquals(15, week.getWeek());
        assertEquals(2025, week.getYear().getYear());
    }

    @Test
    public void testConstructorWithInvalidWeekAndYearObject() {
        // Arrange
        Year year = new Year(2021);

        // Assert & Act
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Week(53, year); // 2021 has only 52 weeks
        });
    }

    @Test
    public void testConstructorWithNullYear() {
        // Assert & Act
        Assertions.assertThrows(NullPointerException.class, () -> {
            new Week(10, null); // Year object is null
        });
    }

    @Test
    public void testConstructorWithDateAndTimeZone() {
        // Arrange
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.set(2025, Calendar.MARCH, 15);
        Date date = calendar.getTime();

        // Act
        Week week = new Week(date, TimeZone.getTimeZone("UTC"));

        // Assert
        assertEquals(2025, week.getYear().getYear());
        assertEquals(calendar.get(Calendar.WEEK_OF_YEAR), week.getWeek());
    }

    @Test
    public void testConstructorWithNullDate() {
        // Assert & Act
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Week(null, TimeZone.getTimeZone("UTC"));
        });
    }

    @Test
    public void testConstructorWithNullTimeZone() {
        // Arrange
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.JANUARY, 1);
        Date date = calendar.getTime();

        // Assert & Act
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Week(date, null);
        });
    }

    @Test
    public void testConstructorWithDateTimeZoneAndLocale() {
        // Arrange
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"), Locale.US);
        calendar.set(2025, Calendar.JUNE, 10);
        Date date = calendar.getTime();

        // Act
        Week week = new Week(date, TimeZone.getTimeZone("UTC"), Locale.US);

        // Assert
        assertEquals(2025, week.getYear().getYear());
        assertEquals(calendar.get(Calendar.WEEK_OF_YEAR), week.getWeek());
    }

    @Test
    public void testConstructorWithNullDateInTimeZoneAndLocale() {
        // Assert & Act
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Week(null, TimeZone.getTimeZone("UTC"), Locale.US);
        });
    }

    @Test
    public void testConstructorWithNullTimeZoneAndLocale() {
        // Arrange
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.APRIL, 5);
        Date date = calendar.getTime();

        // Assert & Act
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Week(date, null, Locale.US);
        });
    }

    @Test
    public void testConstructorWithNullLocale() {
        // Arrange
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.NOVEMBER, 30);
        Date date = calendar.getTime();

        // Assert & Act
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Week(date, TimeZone.getTimeZone("UTC"), null);
        });
    }

    @Test
    public void testGetYearForWeek22() {
        // Arrange
        Week week = new Week(22, 2027);

        // Act & Assert
        assertEquals(2027, week.getYear().getYear());
    }

    @Test
    public void testGetYearForWeek53() {
        // Arrange
        Week week = new Week(53, 2011);

        // Act & Assert
        assertEquals(2011, week.getYear().getYear());
    }

    @Test
    public void testGetWeek() {
        // Arrange
        Week week = new Week(10, 2028);

        // Act & Assert
        assertEquals(10, week.getWeek());
    }

    @Test
    public void testGetWeekFirstWeekOfYear() {
        // Arrange
        Week week = new Week(1, 2025);

        // Act & Assert
        assertEquals(1, week.getWeek());
    }

    @Test
    public void testGetWeekLastWeekOfYear() {
        // Arrange
        Week week = new Week(53, 2011);

        // Act & Assert
        assertEquals(53, week.getWeek());
    }

    @Test
    public void testPreviousMethod() {
        // Arrange
        Week week = new Week(2, 2025);

        // Act
        Week previousWeek = (Week) week.previous();

        // Assert
        assertNotNull(previousWeek);
        assertEquals(1, previousWeek.getWeek());
        assertEquals(2025, previousWeek.getYear().getYear());
    }

    @Test
    public void testPreviousMethodAtWeek1() {
        // Arrange
        Week week = new Week(1, 2025);

        // Act
        Week previousWeek = (Week) week.previous();

        // Assert
        assertNotNull(previousWeek);
        assertEquals(52, previousWeek.getWeek()); // Assumes previous year has 52 weeks
        assertEquals(2024, previousWeek.getYear().getYear());
    }

    @Test
    public void testPreviousMethodAtWeek1Year2012() {
        // Arrange (2011 had 53 weeks)
        Week week = new Week(1, 2012);

        // Act
        Week previousWeek = (Week) week.previous();

        // Assert
        assertNotNull(previousWeek);
        assertEquals(53, previousWeek.getWeek()); // 2011 had 53 weeks
        assertEquals(2011, previousWeek.getYear().getYear());
    }

    @Test
    public void testNextMethod() {
        // Arrange
        Week week = new Week(10, 2025);

        // Act
        Week nextWeek = (Week) week.next();

        // Assert
        assertNotNull(nextWeek);
        assertEquals(11, nextWeek.getWeek());
        assertEquals(2025, nextWeek.getYear().getYear());
    }

    @Test
    public void testNextMethodAtWeek52() {
        // Arrange
        Week week = new Week(52, 2025);

        // Act
        Week nextWeek = (Week) week.next();

        // Assert
        assertNotNull(nextWeek);
        assertEquals(1, nextWeek.getWeek());
        assertEquals(2026, nextWeek.getYear().getYear());
    }

    @Test
    public void testNextMethodAtWeek52Year2011() {
        // Arrange
        Week week = new Week(52, 2011);

        // Act
        Week nextWeek = (Week) week.next();

        // Assert
        assertNotNull(nextWeek);
        assertEquals(53, nextWeek.getWeek());
        assertEquals(2011, nextWeek.getYear().getYear());
    }

    @Test
    public void testGetFirstMillisecond() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.MARCH, 10); // Arbitrary date
        Week week = new Week(10, 2025);
        long firstMillisecond = week.getFirstMillisecond(calendar);

        assertTrue(firstMillisecond > 0); // Ensure it returns a valid millisecond timestamp
    }

    @Test
    public void testGetFirstMillisecondNoCalendar() {
        Week week = new Week(10, 2025);
        long firstMillisecond = week.getFirstMillisecond();

        assertTrue(firstMillisecond > 0); // Ensure it returns a valid millisecond timestamp
    }

    @Test
    public void testGetLastMillisecond() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.MARCH, 10); // Arbitrary date
        Week week = new Week(10, 2025);
        long lastMillisecond = week.getLastMillisecond(calendar);

        assertTrue(lastMillisecond > 0); // Ensure it returns a valid millisecond timestamp
    }

    @Test
    public void testGetLastMillisecondNoCalendar() {
        Week week = new Week(10, 2025);
        long lastMillisecond = week.getLastMillisecond();

        assertTrue(lastMillisecond > 0); // Ensure it returns a valid millisecond timestamp
    }

    @Test
    public void testToString() {
        Week week = new Week(10, 2025);
        String weekString = week.toString();

        assertNotNull(weekString);
        assertTrue(weekString.contains("2025")); // Assuming it contains year
        assertTrue(weekString.contains("10")); // Assuming it contains week number
    }

    @Test
    public void testEquals() {
        Week week1 = new Week(10, 2025);
        Week week2 = new Week(10, 2025);
        Week week3 = new Week(11, 2025);

        assertTrue(week1.equals(week2)); // Should be equal
        assertFalse(week1.equals(week3)); // Should not be equal
        assertFalse(week1.equals(null)); // Should return false for null
    }

    @Test
    public void testHashCode() {
        Week week1 = new Week(10, 2025);
        Week week2 = new Week(10, 2025);
        Week week3 = new Week(11, 2025);

        assertEquals(week1.hashCode(), week2.hashCode()); // Same week should have same hash code
        assertNotEquals(week1.hashCode(), week3.hashCode()); // Different weeks should have different hash codes
    }

    @Test
    public void testCompareTo() {
        Week week1 = new Week(10, 2025);
        Week week2 = new Week(10, 2025);
        Week week3 = new Week(11, 2025);

        assertEquals(0, week1.compareTo(week2)); // Same weeks should be equal
        assertTrue(week1.compareTo(week3) < 0); // week1 should be less than week3
        assertTrue(week3.compareTo(week1) > 0); // week3 should be greater than week1
    }

    @Test
    public void testParseWeek() {
        Week week = Week.parseWeek("2025-W10");
        assertNotNull(week);
        assertEquals(10, week.getWeek());
        assertEquals(2025, week.getYear().getYear());
    }

    @Test
    public void testParseWeekWithInvalidWeekNumber() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Week.parseWeek("2025-W0")); // Week number 0 is invalid
        Assertions.assertThrows(IllegalArgumentException.class, () -> Week.parseWeek("2025-W54")); // Week number 54 exceeds valid range
        Assertions.assertThrows(IllegalArgumentException.class, () -> Week.parseWeek("2025-W100")); // Week number too large
    }

    @Test
    public void testParseWeekWithLeadingZeroes() {
        Week week = Week.parseWeek("2025-W01"); // Leading zero in week number
        assertNotNull(week);
        assertEquals(1, week.getWeek());
        assertEquals(2025, week.getYear().getYear());
    }

    @Test
    public void testParseWeekWithDay53() {
        Week week = Week.parseWeek("2011-W53"); // 2011 has 53 week
        assertNotNull(week);
        assertEquals(53, week.getWeek());
        assertEquals(2011, week.getYear().getYear());
    }

    @Test
    public void testParseWeekWithEmptyInput() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Week.parseWeek("")); // Empty string should throw exception
    }

    @Test
    public void testParseWeekWithInvalidStringFormat() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Week.parseWeek("2025/10")); // Invalid separator
        Assertions.assertThrows(IllegalArgumentException.class, () -> Week.parseWeek("2025-10")); // Invalid separator
        Assertions.assertThrows(IllegalArgumentException.class, () -> Week.parseWeek("W10")); // Missing year
    }

    @Test
    public void testPeg() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.MARCH, 10); // Arbitrary date
        Week week = new Week(10, 2025);

        week.peg(calendar);

        assertNotNull(week); // Assuming peg should update or adjust the week object
    }

    @Test
    public void testGetYearValue() {
        Week week = new Week(10, 2025);
        assertEquals(2025, week.getYearValue());
    }
}