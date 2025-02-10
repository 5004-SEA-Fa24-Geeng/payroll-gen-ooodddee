package student;

import java.math.BigDecimal;

/**
 * Represents a pay stub that contains payment information for an employee.
 * Includes details about net pay, taxes, and provides methods to access and
 * format this information.
 */
public class PayStub implements IPayStub {
    /** The employee associated with this pay stub. */
    private final IEmployee employee;
    /** The net pay amount after taxes and deductions. */
    private final BigDecimal netPay;
    /** The amount of taxes withheld. */
    private final BigDecimal taxes;
    /** The year-to-date (YTD) earnings of the employee. */
    private final BigDecimal ytdEarnings;
    /** The year-to-date (YTD) taxes paid by the employee. */
    private final BigDecimal ytdTaxesPaid;

    /**
     * Constructs a new PayStub with the specified parameters.
     *
     * @param employee      The employee associated with this pay stub
     * @param netPay        The net pay amount after taxes and deductions
     * @param taxes         The amount of taxes withheld
     * @param ytdEarnings   The total year-to-date earnings of the employee
     * @param ytdTaxesPaid  The total year-to-date taxes paid by the employee
     * @throws IllegalArgumentException if any arguments are null or negative
     */
    public PayStub(
            IEmployee employee,
            BigDecimal netPay,
            BigDecimal taxes,
            BigDecimal ytdEarnings,
            BigDecimal ytdTaxesPaid) {

        if (employee == null) {
            throw new IllegalArgumentException("Employee cannot be null.");
        }
        if (netPay == null || netPay.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Net pay cannot be null or negative.");
        }
        if (taxes == null || taxes.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Taxes cannot be null or negative.");
        }
        if (ytdEarnings == null || ytdEarnings.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Year-to-date earnings cannot be null or negative.");
        }
        if (ytdTaxesPaid == null || ytdTaxesPaid.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Year-to-date taxes paid cannot be null or negative.");
        }

        this.employee = employee;
        this.netPay = netPay;
        this.taxes = taxes;
        this.ytdEarnings = ytdEarnings;
        this.ytdTaxesPaid = ytdTaxesPaid;
    }

    /**
     * Returns the net pay amount as a double.
     *
     * @return The net pay amount
     */
    @Override
    public double getPay() {
        return netPay.doubleValue();
    }

    /**
     * Returns the taxes paid amount as a double.
     *
     * @return The taxes paid amount
     */
    @Override
    public double getTaxesPaid() {
        return taxes.doubleValue();
    }

    /**
     * Converts the pay stub information to a CSV format string.
     *
     * @return A CSV string containing employee name, pay amount, taxes paid, YTD earnings, and YTD taxes
     */
    @Override
    public String toCSV() {
        return String.format(
                "%s,%.2f,%.2f,%.2f,%.2f",
                employee.getName(),
                getPay(),
                getTaxesPaid(),
                employee.getYTDEarnings(),
                employee.getYTDTaxesPaid()
        );
    }
}
