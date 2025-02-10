package student;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class HourlyEmployeeTest {

    private HourlyEmployee hourlyEmployee;

    @BeforeEach
    void setUp() {
        // Initialize a standard HourlyEmployee object with an hourly wage of $20.00
        hourlyEmployee = new HourlyEmployee("John Doe", "E123", 20.00, 5000.00, 1000.00, 50.00);
    }

    /**
     * Tests calculateGrossPay() with regular hours (≤ 40).
     */
    @Test
    void calculateGrossPay_RegularHours() {
        BigDecimal expectedPay = BigDecimal.valueOf(20.00 * 40).setScale(2);
        BigDecimal actualPay = hourlyEmployee.calculateGrossPay(40);

        assertEquals(expectedPay, actualPay, "Regular pay calculation failed.");
    }

    /**
     * Tests calculateGrossPay() with overtime hours (> 40).
     */
    @Test
    void calculateGrossPay_OvertimeHours() {
        // 45 hours: 40 hours at regular pay + 5 hours overtime (1.5x hourly rate)
        BigDecimal regularPay = BigDecimal.valueOf(20.00 * 40);
        BigDecimal overtimePay = BigDecimal.valueOf(5 * (20.00 * 1.5));
        BigDecimal expectedPay = regularPay.add(overtimePay).setScale(2);

        BigDecimal actualPay = hourlyEmployee.calculateGrossPay(45);

        assertEquals(expectedPay, actualPay, "Overtime pay calculation failed.");
    }

    /**
     * Tests calculateGrossPay() with zero hours worked.
     */
    @Test
    void calculateGrossPay_ZeroHours() {
        BigDecimal expectedPay = BigDecimal.ZERO.setScale(2);
        BigDecimal actualPay = hourlyEmployee.calculateGrossPay(0);

        assertEquals(expectedPay, actualPay, "Zero hours worked should result in zero pay.");
    }

//    /**
//     * Tests calculateGrossPay() with negative hours worked, expecting an exception.
//     */
//    @Test
//    void calculateGrossPay_NegativeHours() {
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//            hourlyEmployee.calculateGrossPay(-5);
//        });
//
//        assertTrue(exception.getMessage().contains("Hours worked cannot be negative"));
//    }
}
