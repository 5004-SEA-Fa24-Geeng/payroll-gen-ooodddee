package student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BuilderTest {

    @BeforeEach
    void setUp() {
        // No setup needed since Builder is a static utility class.
    }

    /**
     * Tests the buildEmployeeFromCSV method with valid inputs.
     */
    @Test
    void buildEmployeeFromCSV_ValidHourlyEmployee() {
        String csv = "HOURLY,John Doe,E123,25.50,15000.00,2500.00,200.00";
        IEmployee employee = Builder.buildEmployeeFromCSV(csv);

        assertNotNull(employee);
        assertTrue(employee instanceof HourlyEmployee);
        assertEquals("John Doe", employee.getName());
        assertEquals("E123", employee.getID());
        assertEquals(25.50, employee.getPayRate());
        assertEquals(2500.0, employee.getYTDEarnings());
        assertEquals(200.0, employee.getYTDTaxesPaid());
        assertEquals(15000.0, employee.getPretaxDeductions());
    }

    @Test
    void buildEmployeeFromCSV_ValidSalaryEmployee() {
        String csv = "SALARY,Jane Smith,E456,72000.00,30000.00,5000.00,300.00";
        IEmployee employee = Builder.buildEmployeeFromCSV(csv);

        assertNotNull(employee);
        assertTrue(employee instanceof SalaryEmployee);
        assertEquals("Jane Smith", employee.getName());
        assertEquals("E456", employee.getID());
        assertEquals(72000.00, employee.getPayRate());
        assertEquals(5000.0, employee.getYTDEarnings());
        assertEquals(300.0, employee.getYTDTaxesPaid());
        assertEquals(30000.0, employee.getPretaxDeductions());
    }

    /**
     * Tests invalid CSV input cases for buildEmployeeFromCSV.
     */
    @Test
    void buildEmployeeFromCSV_InvalidFormat() {
        String invalidCsv = "HOURLY,John Doe,E123,25.50,15000.00"; // Missing fields

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Builder.buildEmployeeFromCSV(invalidCsv);
        });

        assertTrue(exception.getMessage().contains("Invalid employee CSV format"));
    }

    @Test
    void buildEmployeeFromCSV_UnknownEmployeeType() {
        String invalidCsv = "PART_TIME,John Doe,E123,25.50,15000.00,2500.00,200.00"; // Invalid type

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Builder.buildEmployeeFromCSV(invalidCsv);
        });

        assertTrue(exception.getMessage().contains("Unknown employee type"));
    }

    @Test
    void buildEmployeeFromCSV_InvalidNumberFormat() {
        String invalidCsv = "HOURLY,John Doe,E123,INVALID,15000.00,2500.00,200.00"; // Invalid pay rate

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Builder.buildEmployeeFromCSV(invalidCsv);
        });

        assertTrue(exception.getMessage().contains("Invalid number format"));
    }

    /**
     * Tests the buildTimeCardFromCSV method with valid inputs.
     */
    @Test
    void buildTimeCardFromCSV_Valid() {
        String csv = "E123,38.5";
        ITimeCard timeCard = Builder.buildTimeCardFromCSV(csv);

        assertNotNull(timeCard);
        assertEquals("E123", timeCard.getEmployeeID());
        assertEquals(38.5, timeCard.getHoursWorked());
    }

    /**
     * Tests invalid CSV input cases for buildTimeCardFromCSV.
     */
    @Test
    void buildTimeCardFromCSV_InvalidFormat() {
        String invalidCsv = "E123"; // Missing hours worked

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Builder.buildTimeCardFromCSV(invalidCsv);
        });

        assertTrue(exception.getMessage().contains("Invalid time card CSV format"));
    }

    @Test
    void buildTimeCardFromCSV_InvalidNumberFormat() {
        String invalidCsv = "E123,INVALID"; // Invalid number format

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Builder.buildTimeCardFromCSV(invalidCsv);
        });

        assertTrue(exception.getMessage().contains("Invalid number format"));
    }
}
