package student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class TimeCardTest {

    private TimeCard timeCard;

    @BeforeEach
    void setUp() {
        // Initialize a TimeCard object with valid employee ID and hours worked
        timeCard = new TimeCard("E123", new BigDecimal("38.5"));
    }

    /**
     * Tests getEmployeeID() to ensure it returns the correct employee ID.
     */
    @Test
    void getEmployeeID() {
        assertEquals("E123", timeCard.getEmployeeID(), "Employee ID should be 'E123'.");
    }

    /**
     * Tests getHoursWorked() to ensure it returns the correct hours worked.
     */
    @Test
    void getHoursWorked() {
        assertEquals(38.5, timeCard.getHoursWorked(), 0.01, "Hours worked should be 38.5.");
    }

    /**
     * Tests constructor with a null employee ID, expecting an exception.
     */
    @Test
    void constructor_NullEmployeeID() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new TimeCard(null, new BigDecimal("38.5"));
        });

        assertTrue(exception.getMessage().contains("Employee ID cannot be null or empty"));
    }

    /**
     * Tests constructor with an empty employee ID, expecting an exception.
     */
    @Test
    void constructor_EmptyEmployeeID() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new TimeCard("", new BigDecimal("38.5"));
        });

        assertTrue(exception.getMessage().contains("Employee ID cannot be null or empty"));
    }

    /**
     * Tests constructor with a negative hoursWorked value, expecting an exception.
     */
    @Test
    void constructor_NegativeHoursWorked() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new TimeCard("E123", new BigDecimal("-5.0"));
        });

        assertTrue(exception.getMessage().contains("Hours worked cannot be null or negative"));
    }

    /**
     * Tests constructor with a null hoursWorked value, expecting an exception.
     */
    @Test
    void constructor_NullHoursWorked() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new TimeCard("E123", null);
        });

        assertTrue(exception.getMessage().contains("Hours worked cannot be null or negative"));
    }
}
