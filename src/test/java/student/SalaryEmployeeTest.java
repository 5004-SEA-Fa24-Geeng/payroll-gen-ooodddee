import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import student.*;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class SalaryEmployeeTest {
    private SalaryEmployee employee;

    @BeforeEach
    void setUp() {
        employee = new SalaryEmployee(
                "Test Salary",
                "NEU002",
                new BigDecimal("120000.00"),
                new BigDecimal("50000.00"),
                new BigDecimal("10000.00"),
                new BigDecimal("200.00"));
    }

    @Test
    void testCalculateGrossPay() {
        assertEquals(new BigDecimal("5000.00"), employee.calculateGrossPay(40.0));
        assertEquals(new BigDecimal("5000.00"), employee.calculateGrossPay(50.0));
    }

    @Test
    void testRegularHoursPay() {
        IPayStub payStub = employee.runPayroll(40.0);
        assertEquals(3712.80, payStub.getPay(), 0.01);
    }

    @Test
    void testNegativeHours() {
        IPayStub payStub = employee.runPayroll(-5.0);
        assertNull(payStub);
    }

    @Test
    void testZeroHours() {
        IPayStub payStub = employee.runPayroll(0.0);
        assertTrue(payStub.getPay() > 0); // Salary employees get paid even for 0 hours
    }

    @Test
    void testToCSV() {
        String expected = "SALARY,Test Salary,NEU002,120000.00,200.00,50000.00,10000.00";
        assertEquals(expected, employee.toCSV());
    }

    @Test
    void testGetName() {
        assertEquals("Test Salary", employee.getName());
    }

    @Test
    void testGetID() {
        assertEquals("NEU002", employee.getID());
    }

    @Test
    void testGetPayRate() {
        assertEquals(120000.00, employee.getPayRate(), 0.01);
    }

    @Test
    void testGetEmployeeType() {
        assertEquals("SALARY", employee.getEmployeeType());
    }

    @Test
    void testGetYTDEarnings() {
        assertEquals(50000.00, employee.getYTDEarnings(), 0.01);
        employee.runPayroll(40.0);
        assertEquals(50000 + 3712.8, employee.getYTDEarnings(), 0.01);
    }

    @Test
    void testGetYTDTaxesPaid() {
        assertEquals(10000.00, employee.getYTDTaxesPaid(), 0.01);
        employee.runPayroll(40.0);
        assertEquals(10000 + 1087.2, employee.getYTDTaxesPaid(), 0.01);
    }

    @Test
    void testGetPretaxDeductions() {
        assertEquals(200.00, employee.getPretaxDeductions(), 0.01);
    }
}