package student;

import java.math.BigDecimal;

/**
 * A class representing a time card for an employee.
 */
public class TimeCard implements ITimeCard {
    /** The ID of the employee associated with this time card. */
    private final String employeeID;
    /** The number of hours worked in the pay period. */
    private final BigDecimal hoursWorked;

    /**
     * Constructs a TimeCard with the specified employee ID and hours worked.
     *
     * @param employeeID   the ID of the employee
     * @param hoursWorked  the number of hours worked
     * @throws IllegalArgumentException if employeeID is null or empty, or if hoursWorked is negative
     */
    public TimeCard(String employeeID, BigDecimal hoursWorked) {
        if (employeeID == null || employeeID.trim().isEmpty()) {
            throw new IllegalArgumentException("Employee ID cannot be null or empty");
        }
        if (hoursWorked == null || hoursWorked.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Hours worked cannot be null or negative");
        }
        this.employeeID = employeeID;
        this.hoursWorked = hoursWorked;
    }

    /**
     * Gets the employee ID.
     *
     * @return the employee ID
     */
    @Override
    public String getEmployeeID() {
        return employeeID;
    }

    /**
     * Gets the hours worked by the employee.
     *
     * @return the hours worked as a double
     */
    @Override
    public double getHoursWorked() {
        return hoursWorked.doubleValue();
    }
}
