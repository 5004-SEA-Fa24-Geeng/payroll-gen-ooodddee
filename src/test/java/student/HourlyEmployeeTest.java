import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import student.*;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class HourlyEmployeeTest {
    private HourlyEmployee employee;

    @BeforeEach
    void setUp() {
        employee = new HourlyEmployee(
                "Test Employee",
                "NEU001",
                new BigDecimal("20.00"),
                new BigDecimal("1000.00"),
                new BigDecimal("200.00"),
                new BigDecimal("0.00"));
    }

    @Test
    void testRegularHoursPay() {
        IPayStub payStub = employee.runPayroll(40.0);
        assertEquals(618.8, payStub.getPay(), 0.01);
    }

    @Test
    void testOvertimePay() {
        IPayStub payStub = employee.runPayroll(45.0);
        assertEquals(734.825, payStub.getPay(), 0.01);
    }

    @Test
    void testNegativeHours() {
        IPayStub payStub = employee.runPayroll(-5.0);
        assertNull(payStub);
    }

    @Test
    void testZeroHours() {
        IPayStub payStub = employee.runPayroll(0.0);
        assertEquals(0.0, payStub.getPay(), 0.01);
    }

    @Test
    void testToCSV() {
        String expected = "HOURLY,Test Employee,NEU001,20.00,0.00,1000.00,200.00";
        assertEquals(expected, employee.toCSV());
        employee.runPayroll(40.0);
        expected = "HOURLY,Test Employee,NEU001,20.00,0.00,1618.80,381.20";
        assertEquals(expected, employee.toCSV());
    }

    @Test
    void testCalculateGrossPay() {
        assertTrue(new BigDecimal("800.00").compareTo(employee.calculateGrossPay(40.0)) == 0);
        assertTrue(new BigDecimal("950.00").compareTo(employee.calculateGrossPay(45.0)) == 0);
    }

    @Test
    void testGetName() {
        assertEquals("Test Employee", employee.getName());
    }

    @Test
    void testGetID() {
        assertEquals("NEU001", employee.getID());
    }

    @Test
    void testGetPayRate() {
        assertEquals(20.00, employee.getPayRate(), 0.01);
    }

    @Test
    void testGetEmployeeType() {
        assertEquals("HOURLY", employee.getEmployeeType());
    }

    @Test
    void testGetYTDEarnings() {
        assertEquals(1000.00, employee.getYTDEarnings(), 0.01);
        employee.runPayroll(40.0);
        assertEquals(1618.80, employee.getYTDEarnings(), 0.01);
    }

    @Test
    void testGetYTDTaxesPaid() {
        assertEquals(200.00, employee.getYTDTaxesPaid(), 0.01);
        employee.runPayroll(40.0);
        assertEquals(381.20, employee.getYTDTaxesPaid(), 0.01);
    }

    @Test
    void testGetPretaxDeductions() {
        assertEquals(0.00, employee.getPretaxDeductions(), 0.01);
    }
}