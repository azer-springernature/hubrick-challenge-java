package config;

public class Configuration {
    public static final int PERCENTILE_FOR_CALCULATION = 95;
    public static final Integer RANGE_FACTOR = 10;

    private static final String REPORTS_PATH = System.getProperty("user.dir") + "/reports";

    public static final String EMPLOYEES_FILE = "/employees.csv";
    public static final String AGES_FILE = "/ages.csv";
    public static final String DEPARTMENTS_FILE = "/departments.csv";

    public static final String AVERAGE_INCOME_BY_RANGE_FILE = REPORTS_PATH + "/income-average-by-age-range.csv";
    public static final String AVERAGE_INCOME_BY_RANGE_HEADER = "Age Range;Average Income";
    public static final String EMPLOYEE_AGE_BY_DEPARTMENT_FILE = REPORTS_PATH + "/employee-age-by-department.csv";
    public static final String EMPLOYEE_AGE_BY_DEPARTMENT_HEADER = "Department;Median Age";
    public static final String INCOME_BY_DEPARTMENT_FILE = REPORTS_PATH + "/income-by-department.csv";
    public static final String INCOME_BY_DEPARTMENT_HEADER = "Department;Median Income";
    public static final String INCOME_95_BY_DEPARTMENT_FILE = REPORTS_PATH + "/income-95-by-department.csv";
    public static final String INCOME_95_BY_DEPARTMENT_HEADER = "Department;95 Percentile Income";
}
