package student;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Represents an hourly employee in the payroll system.
 * This class calculates pay based on hours worked, including overtime pay.
 */
public class HourlyEmployee extends EmployeeBigDecimal {

    /** Overtime pay multiplier (1.5 times the regular hourly rate). */
    private static final BigDecimal OVERTIME_RATE = new BigDecimal("1.5");

    /** Standard hours per week before overtime applies. */
    private static final BigDecimal OVERTIME_HOUR = new BigDecimal("40");

    /**
     * Creates an hourly employee with the specified details.
     *
     * @param name             Employee's name.
     * @param id               Employee's ID.
     * @param payRate          Hourly wage.
     * @param ytdEarnings      Year-to-date earnings.
     * @param ytdTaxesPaid     Year-to-date taxes paid.
     * @param pretaxDeductions Amount deducted before taxes.
     */
    public HourlyEmployee(
            String name,
            String id,
            BigDecimal payRate,
            BigDecimal ytdEarnings,
            BigDecimal ytdTaxesPaid,
            BigDecimal pretaxDeductions) {
        super(name, id, payRate, pretaxDeductions, ytdEarnings, ytdTaxesPaid, EmployeeType.HOURLY);
    }

    /**
     * Creates an hourly employee with primitive double values.
     * Automatically converts {@code double} values to {@code BigDecimal} for precision.
     *
     * @param name             Employee's name.
     * @param id               Employee's ID.
     * @param payRate          Hourly wage.
     * @param ytdEarnings      Year-to-date earnings.
     * @param ytdTaxesPaid     Year-to-date taxes paid.
     * @param pretaxDeductions Amount deducted before taxes.
     */
    public HourlyEmployee(
            String name,
            String id,
            double payRate,
            double ytdEarnings,
            double ytdTaxesPaid,
            double pretaxDeductions) {
        this(name, id, BigDecimal.valueOf(payRate), BigDecimal.valueOf(ytdEarnings),
                BigDecimal.valueOf(ytdTaxesPaid), BigDecimal.valueOf(pretaxDeductions));
    }

    /**
     * Calculates gross pay based on hours worked.
     * If hours exceed 40, overtime pay is applied at 1.5x the regular rate.
     *
     * @param hoursWorked Number of hours worked in the pay period.
     * @return Gross pay as a {@code BigDecimal}, rounded to two decimal places.
     * @throws IllegalArgumentException if hoursWorked is negative.
     */
    @Override
    public BigDecimal calculateGrossPay(double hoursWorked) {
        if (hoursWorked < 0) {
            return null;
        }

        BigDecimal hoursWorkedBD = BigDecimal.valueOf(hoursWorked);
        BigDecimal grossPay;

        if (hoursWorkedBD.compareTo(OVERTIME_HOUR) > 0) {
            // Calculate overtime pay
            BigDecimal overtimeHours = hoursWorkedBD.subtract(OVERTIME_HOUR);
            BigDecimal overtimePayRate = getPayRateBD().multiply(OVERTIME_RATE);
            BigDecimal overtimeSalary = overtimeHours.multiply(overtimePayRate);

            // Regular pay + overtime pay
            grossPay = getPayRateBD().multiply(OVERTIME_HOUR).add(overtimeSalary);
        } else {
            // Regular pay only
            grossPay = getPayRateBD().multiply(hoursWorkedBD);
        }

        return grossPay.setScale(2, RoundingMode.HALF_UP);
    }
}
