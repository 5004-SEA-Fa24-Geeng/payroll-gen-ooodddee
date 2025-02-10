package student;

import java.math.BigDecimal;

/**
 * A base class for different types of employees in the payroll system.
 * This class stores common employee information and handles payroll calculations.
 */
public abstract class EmployeeBigDecimal implements IEmployee {
    /** Employee's name. */
    private final String name;
    /** Employee's unique ID. */
    private final String id;
    /** Employee's pay rate (hourly wage or salary). */
    private final BigDecimal payRate;
    /** Amount deducted before taxes. */
    private final BigDecimal pretaxDeductions;
    /** Year-to-date (YTD) earnings. */
    private BigDecimal ytdEarnings;
    /** Year-to-date (YTD) taxes paid. */
    private BigDecimal ytdTaxesPaid;
    /** Employee type (HOURLY or SALARY). */
    private final EmployeeType employeeType;

    /** The fixed tax rate used in payroll calculations. */
    private static final BigDecimal TAX_RATE = new BigDecimal("0.2265");

    /**
     * Creates an employee with the given details.
     *
     * @param name Employee's name.
     * @param id Employee's ID.
     * @param payRate Employee's pay rate (hourly wage or salary).
     * @param pretaxDeductions Amount deducted before taxes.
     * @param ytdEarnings Employee's earnings for the year so far.
     * @param ytdTaxesPaid Taxes paid by the employee for the year so far.
     * @param employeeType Type of employee (HOURLY or SALARY).
     * @throws IllegalArgumentException if any value is invalid.
     */
    protected EmployeeBigDecimal(
            String name,
            String id,
            BigDecimal payRate,
            BigDecimal pretaxDeductions,
            BigDecimal ytdEarnings,
            BigDecimal ytdTaxesPaid,
            EmployeeType employeeType) {

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Employee name cannot be empty.");
        }
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Employee ID cannot be empty.");
        }
        if (payRate == null || payRate.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Pay rate must be positive.");
        }
        if (pretaxDeductions == null || pretaxDeductions.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Pretax deductions cannot be negative.");
        }
        if (ytdEarnings == null || ytdEarnings.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("YTD earnings cannot be negative.");
        }
        if (ytdTaxesPaid == null || ytdTaxesPaid.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("YTD taxes paid cannot be negative.");
        }
        if (employeeType == null) {
            throw new IllegalArgumentException("Employee type must be specified.");
        }

        this.name = name;
        this.id = id;
        this.payRate = payRate;
        this.pretaxDeductions = pretaxDeductions;
        this.ytdEarnings = ytdEarnings;
        this.ytdTaxesPaid = ytdTaxesPaid;
        this.employeeType = employeeType;
    }

    /** @return Employee's name. */
    @Override
    public String getName() {
        return name;
    }

    /** @return Employee's ID. */
    @Override
    public String getID() {
        return id;
    }

    /** @return Employee's pay rate as a double. */
    @Override
    public double getPayRate() {
        return payRate.doubleValue();
    }

    /** @return Employee's pay rate as a BigDecimal. */
    public BigDecimal getPayRateBD() {
        return payRate;
    }

    /** @return Employee type (HOURLY or SALARY). */
    @Override
    public String getEmployeeType() {
        return employeeType.name();
    }

    /** @return Year-to-date earnings as a double. */
    @Override
    public double getYTDEarnings() {
        return ytdEarnings.doubleValue();
    }

    /** @return Year-to-date taxes paid as a double. */
    @Override
    public double getYTDTaxesPaid() {
        return ytdTaxesPaid.doubleValue();
    }

    /** @return Pretax deductions as a double. */
    @Override
    public double getPretaxDeductions() {
        return pretaxDeductions.doubleValue();
    }

    /**
     * Calculates the gross pay based on hours worked.
     *
     * @param hoursWorked The number of hours worked.
     * @return Gross pay as a BigDecimal.
     */
    protected abstract BigDecimal calculateGrossPay(double hoursWorked);

    /**
     * Runs payroll for the employee and calculates net pay.
     *
     * @param hoursWorked The number of hours worked.
     * @return The employee's pay stub.
     */
    @Override
    public IPayStub runPayroll(double hoursWorked) {
        if (hoursWorked < 0) {
            throw new IllegalArgumentException("Hours worked cannot be negative.");
        }

        BigDecimal grossPay = calculateGrossPay(hoursWorked);
        BigDecimal taxableAmount = grossPay.subtract(pretaxDeductions);

        // Ensure taxable amount is never negative
        if (taxableAmount.compareTo(BigDecimal.ZERO) < 0) {
            taxableAmount = BigDecimal.ZERO;
        }

        BigDecimal taxes = taxableAmount.multiply(TAX_RATE);
        BigDecimal netPay = taxableAmount.subtract(taxes);

        // Ensure net pay is never negative
        if (netPay.compareTo(BigDecimal.ZERO) < 0) {
            netPay = BigDecimal.ZERO;
        }

        ytdEarnings = ytdEarnings.add(netPay);
        ytdTaxesPaid = ytdTaxesPaid.add(taxes);

        return new PayStub(this, netPay, taxes, ytdEarnings, ytdTaxesPaid);
    }


    /**
     * Converts employee data to a CSV string.
     * Format: "employeeType,name,id,payRate,pretaxDeductions,ytdEarnings,ytdTaxesPaid"
     *
     * @return CSV representation of the employee.
     */
    @Override
    public String toCSV() {
        return String.format("%s,%s,%s,%.2f,%.2f,%.2f,%.2f",
                employeeType, name, id,
                getPayRate(),
                getPretaxDeductions(),
                getYTDEarnings(),
                getYTDTaxesPaid());
    }
}
