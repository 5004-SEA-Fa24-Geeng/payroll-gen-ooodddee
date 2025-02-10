package student;

import java.math.BigDecimal;

/**
 * A utility class for creating employee and time card objects from CSV strings.
 * This class separates object creation from business logic.
 */
public final class Builder {

    /** Private constructor to prevent instantiation of this utility class. */
    private Builder() {
    }

    /**
     * Creates an {@code IEmployee} object from a CSV string.
     * <p>
     * Expected CSV format:
     * <pre>
     *     HOURLY,Name,ID,PayRate,YTDEarnings,YTDTaxesPaid,PretaxDeductions
     *     SALARY,Name,ID,AnnualSalary,YTDEarnings,YTDTaxesPaid,PretaxDeductions
     * </pre>
     * </p>
     *
     * @param csv The CSV string containing employee data.
     * @return The corresponding {@code IEmployee} object.
     * @throws IllegalArgumentException if the CSV format is invalid.
     */
    public static IEmployee buildEmployeeFromCSV(String csv) {
        if (csv == null || csv.trim().isEmpty()) {
            throw new IllegalArgumentException("CSV string cannot be empty.");
        }

        String[] parts = csv.split(",");
        if (parts.length != 7) {
            throw new IllegalArgumentException("Invalid employee CSV format. Expected 7 fields.");
        }

        String type = parts[0].trim().toUpperCase();
        String name = parts[1].trim();
        String id = parts[2].trim();

        try {
            BigDecimal payRate = new BigDecimal(parts[3].trim());
            BigDecimal pretaxDeductions = new BigDecimal(parts[4].trim());
            BigDecimal ytdEarnings = new BigDecimal(parts[5].trim());
            BigDecimal ytdTaxesPaid = new BigDecimal(parts[6].trim());

            if ("HOURLY".equals(type)) {
                return new HourlyEmployee(name, id, payRate, ytdEarnings, ytdTaxesPaid, pretaxDeductions);
            } else if ("SALARY".equals(type)) {
                return new SalaryEmployee(name, id, payRate, ytdEarnings, ytdTaxesPaid, pretaxDeductions);
            } else {
                throw new IllegalArgumentException("Unknown employee type: " + type);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format in employee CSV: " + csv, e);
        }
    }

    /**
     * Creates a {@code ITimeCard} object from a CSV string.
     * <p>
     * Expected CSV format:
     * <pre>
     *     EmployeeID,HoursWorked
     * </pre>
     * </p>
     *
     * @param csv The CSV string containing time card data.
     * @return A {@code TimeCard} object representing the hours worked.
     * @throws IllegalArgumentException if the CSV format is invalid.
     */
    public static ITimeCard buildTimeCardFromCSV(String csv) {
        if (csv == null || csv.trim().isEmpty()) {
            throw new IllegalArgumentException("CSV string cannot be empty.");
        }

        String[] parts = csv.split(",");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid time card CSV format. Expected 2 fields.");
        }

        String employeeID = parts[0].trim();
        try {
            BigDecimal hoursWorked = new BigDecimal(parts[1].trim());
            return new TimeCard(employeeID, hoursWorked);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format in time card CSV: " + csv, e);
        }
    }
}
