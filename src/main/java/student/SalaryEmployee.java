package student;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Represents a salaried employee in the payroll system.
 * Salaried employees receive a fixed amount per pay period,
 * regardless of the hours worked.
 */
public class SalaryEmployee extends EmployeeBigDecimal {

    /** Number of pay periods per year (typically 24 for bi-monthly pay). */
    private static final BigDecimal PAY_PERIODS_PER_YEAR = new BigDecimal("24");

    /**
     * Creates a salaried employee with the specified details.
     *
     * @param name             Employee's name.
     * @param id               Employee's ID.
     * @param annualSalary     Annual salary.
     * @param ytdEarnings      Year-to-date earnings.
     * @param ytdTaxesPaid     Year-to-date taxes paid.
     * @param pretaxDeductions Amount deducted before taxes.
     */
    public SalaryEmployee(
            String name,
            String id,
            BigDecimal annualSalary,
            BigDecimal ytdEarnings,
            BigDecimal ytdTaxesPaid,
            BigDecimal pretaxDeductions) {
        super(name, id, annualSalary, pretaxDeductions, ytdEarnings, ytdTaxesPaid, EmployeeType.SALARY);
    }

    /**
     * Creates a salaried employee with primitive double values.
     * Converts {@code double} values to {@code BigDecimal} for precision.
     *
     * @param name             Employee's name.
     * @param id               Employee's ID.
     * @param annualSalary     Annual salary.
     * @param ytdEarnings      Year-to-date earnings.
     * @param ytdTaxesPaid     Year-to-date taxes paid.
     * @param pretaxDeductions Amount deducted before taxes.
     */
    public SalaryEmployee(
            String name,
            String id,
            double annualSalary,
            double ytdEarnings,
            double ytdTaxesPaid,
            double pretaxDeductions) {
        this(name, id, BigDecimal.valueOf(annualSalary), BigDecimal.valueOf(ytdEarnings),
                BigDecimal.valueOf(ytdTaxesPaid), BigDecimal.valueOf(pretaxDeductions));
    }

    /**
     * Calculates gross pay for a salaried employee.
     * The annual salary is divided by the number of pay periods per year.
     *
     * @param hoursWorked Not used for salaried employees.
     * @return The gross pay for one pay period, rounded to two decimal places.
     */
    @Override
    public BigDecimal calculateGrossPay(double hoursWorked) {
        return getPayRateBD().divide(PAY_PERIODS_PER_YEAR, 2, RoundingMode.HALF_UP);
    }
}
