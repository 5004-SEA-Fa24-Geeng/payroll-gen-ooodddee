package student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

class SalaryEmployeeTest {

    private SalaryEmployee salaryEmployee;

    @BeforeEach
    void setUp() {
        // Initialize a SalaryEmployee object with an annual salary of $72,000
        salaryEmployee = new SalaryEmployee("Jane Doe", "E456", 72000.00, 30000.00, 5000.00, 200.00);
    }

    /**
     * Tests calculateGrossPay() for a salaried employee.
     * Expected pay per period: Annual Salary / 24 pay periods.
     */
    @Test
    void calculateGrossPay_ValidSalary() {
        BigDecimal expectedPay = BigDecimal.valueOf(72000.00 / 24)
                .setScale(2, RoundingMode.HALF_UP);  // FIXED: Use RoundingMode.HALF_UP instead of deprecated API
        BigDecimal actualPay = salaryEmployee.calculateGrossPay(40); // hoursWorked is ignored

        assertEquals(expectedPay, actualPay, "Salaried employee pay calculation failed.");
    }

    /**
     * Tests calculateGrossPay() when 0 hours are provided (should not affect salaried pay).
     */
    @Test
    void calculateGrossPay_ZeroHours() {
        BigDecimal expectedPay = BigDecimal.valueOf(72000.00 / 24)
                .setScale(2, RoundingMode.HALF_UP);  // FIXED
        BigDecimal actualPay = salaryEmployee.calculateGrossPay(0);

        assertEquals(expectedPay, actualPay, "Salaried pay should not change with 0 hours worked.");
    }

    /**
     * Tests calculateGrossPay() when negative hours are provided (should still return fixed salary).
     */
    @Test
    void calculateGrossPay_NegativeHours() {
        BigDecimal expectedPay = BigDecimal.valueOf(72000.00 / 24)
                .setScale(2, RoundingMode.HALF_UP);  // FIXED
        BigDecimal actualPay = salaryEmployee.calculateGrossPay(-10);

        assertEquals(expectedPay, actualPay, "Salaried pay should not be affected by negative hours worked.");
    }
}
