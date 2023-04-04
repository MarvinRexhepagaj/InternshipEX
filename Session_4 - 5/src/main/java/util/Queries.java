package main.java.util;

public final class Queries {

    private Queries() {}

    public static final String FIND_ALL_EMPLOYEES = "SELECT * FROM employees;";

    public static final String FIND_EMPLOYEE_BY_ID = "SELECT * FROM employees WHERE employeeNumber = ?;";

    public static final String UPDATE_EMPLOYEE = "UPDATE employees SET lastName = ?, firstName = ?, extension = ?, email = ?, officeCode = ?, reportsTo = ?, jobTitle = ? WHERE employeeNumber = ?";


    public static final String INSERT_EMPLOYEE = "INSERT INTO employees "
            + "(employeeNumber, lastName, firstName, extension, email, officeCode, reportsTo,  jobTitle) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";


}
