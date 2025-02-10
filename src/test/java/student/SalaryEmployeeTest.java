package student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class SalaryEmployeeTest {
    private SalaryEmployee employee;

    @BeforeEach
    void setUp() {
        employee = new SalaryEmployee(
                "Test Salary Employee",
                "1",
                new BigDecimal("60000.00"), // Annual Salary
                new BigDecimal("5000.00"),  // YTD Earnings
                new BigDecimal("1000.00"),  // YTD Taxes Paid
                new BigDecimal("150.00"));  // Pretax Deductions
    }

    @Test
    void testRegularPay() {
        IPayStub payStub = employee.runPayroll(40.0);
        assertEquals(1817.725, payStub.getPay(), 0.01);
    }

    @Test
    void testZeroHoursPay() {
        IPayStub payStub = employee.runPayroll(0.0);
        assertEquals(1817.725, payStub.getPay(), 0.01);
    }

    @Test
    void testNegativeHours() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            employee.runPayroll(-5.0);
        });
        assertEquals("Hours worked cannot be negative.", exception.getMessage());
    }

    @Test
    void testToCSV() {
        String expected = "SALARY,Test Salary Employee,1,60000.00,150.00,5000.00,1000.00";
        assertEquals(expected, employee.toCSV());
        employee.runPayroll(40.0);
        expected = "SALARY,Test Salary Employee,1,60000.00,150.00,6817.73,1532.28";
        assertEquals(expected, employee.toCSV());
    }

    @Test
    void testCalculateGrossPay() {
        assertTrue(new BigDecimal("2500.00").compareTo(employee.calculateGrossPay(40.0)) == 0);
        assertTrue(new BigDecimal("2500.00").compareTo(employee.calculateGrossPay(50.0)) == 0);
    }

    @Test
    void testGetName() {
        assertEquals("Test Salary Employee", employee.getName());
    }

    @Test
    void testGetID() {
        assertEquals("1", employee.getID());
    }

    @Test
    void testGetPayRate() {
        assertEquals(60000.00, employee.getPayRate(), 0.01);
    }

    @Test
    void testGetEmployeeType() {
        assertEquals("SALARY", employee.getEmployeeType());
    }

    @Test
    void testGetYTDEarnings() {
        assertEquals(5000.00, employee.getYTDEarnings(), 0.01);
        employee.runPayroll(40.0);
        assertEquals(6817.725, employee.getYTDEarnings(), 0.01);
    }

    @Test
    void testGetYTDTaxesPaid() {
        assertEquals(1000.00, employee.getYTDTaxesPaid(), 0.01);
        employee.runPayroll(40.0);
        assertEquals(1532.275, employee.getYTDTaxesPaid(), 0.01);
    }

    @Test
    void testGetPretaxDeductions() {
        assertEquals(150.00, employee.getPretaxDeductions(), 0.01);
    }
}
