package student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class HourlyEmployeeTest {
    private HourlyEmployee employee;

    @BeforeEach
    void setUp() {
        employee = new HourlyEmployee(
                "Test Hourly Employee",
                "1",
                new BigDecimal("30.00"),  // Hourly wage
                new BigDecimal("8000.00"), // Year-to-date earnings
                new BigDecimal("1500.00"), // Year-to-date taxes paid
                new BigDecimal("75.00"));  // Pretax deductions
    }

    @Test
    void testRegularHoursPay() {
        IPayStub payStub = employee.runPayroll(40.0);
        assertEquals(870.1875, payStub.getPay(), 0.01);
    }

    @Test
    void testOvertimePay() {
        IPayStub payStub = employee.runPayroll(45.0);
        assertEquals(1044.225, payStub.getPay(), 0.01);
    }

    @Test
    void testZeroHours() {
        IPayStub payStub = employee.runPayroll(0.0);
        assertEquals(0.0, payStub.getPay(), 0.01);
    }

    @Test
    void testToCSV() {
        String expected = "HOURLY,Test Hourly Employee,1,30.00,75.00,8000.00,1500.00";
        assertEquals(expected, employee.toCSV());
        employee.runPayroll(40.0);
        expected = "HOURLY,Test Hourly Employee,1,30.00,75.00,8870.19,1754.81";
        assertEquals(expected, employee.toCSV());
    }

    @Test
    void testCalculateGrossPay() {
        assertTrue(new BigDecimal("1200.00").compareTo(employee.calculateGrossPay(40.0)) == 0);
        assertTrue(new BigDecimal("1425.00").compareTo(employee.calculateGrossPay(45.0)) == 0);
    }

    @Test
    void testGetName() {
        assertEquals("Test Hourly Employee", employee.getName());
    }

    @Test
    void testGetID() {
        assertEquals("1", employee.getID());
    }

    @Test
    void testGetPayRate() {
        assertEquals(30.00, employee.getPayRate(), 0.01);
    }

    @Test
    void testGetEmployeeType() {
        assertEquals("HOURLY", employee.getEmployeeType());
    }

    @Test
    void testGetYTDEarnings() {
        assertEquals(8000.00, employee.getYTDEarnings(), 0.01);
        employee.runPayroll(40.0);
        assertEquals(8870.1875, employee.getYTDEarnings(), 0.01);
    }

    @Test
    void testGetYTDTaxesPaid() {
        assertEquals(1500.00, employee.getYTDTaxesPaid(), 0.01);
        employee.runPayroll(40.0);
        assertEquals(1754.8125, employee.getYTDTaxesPaid(), 0.01);
    }

    @Test
    void testGetPretaxDeductions() {
        assertEquals(75.00, employee.getPretaxDeductions(), 0.01);
    }
}
