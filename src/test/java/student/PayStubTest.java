package student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class PayStubTest {

    private PayStub payStub;
    private IEmployee mockEmployee;

    @BeforeEach
    void setUp() {
        // Creating a mock employee
        mockEmployee = new HourlyEmployee("John Doe", "E123", new BigDecimal("25.00"),
                new BigDecimal("50000.00"), new BigDecimal("8000.00"), new BigDecimal("200.00"));

        // Initializing PayStub with sample values
        payStub = new PayStub(mockEmployee, new BigDecimal("2000.00"), new BigDecimal("500.00"),
                new BigDecimal("52000.00"), new BigDecimal("8500.00"));
    }

    /**
     * Tests getPay() method to ensure it returns the correct net pay amount.
     */
    @Test
    void getPay() {
        assertEquals(2000.00, payStub.getPay(), 0.01, "Net pay should be 2000.00");
    }

    /**
     * Tests getTaxesPaid() method to ensure it returns the correct tax amount.
     */
    @Test
    void getTaxesPaid() {
        assertEquals(500.00, payStub.getTaxesPaid(), 0.01, "Taxes paid should be 500.00");
    }

    /**
     * Tests toCSV() method to ensure correct formatting of pay stub details.
     */
    @Test
    void toCSV() {
        String expectedCSV = "John Doe,2000.00,500.00,50000.00,8000.00";
        assertEquals(expectedCSV, payStub.toCSV(), "CSV output is incorrect.");
    }
}
