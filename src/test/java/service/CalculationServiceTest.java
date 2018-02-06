package service;

import csv.output.CsvWritable;
import factory.ServiceFactory;
import model.Employee;
import org.junit.Test;
import service.CalculationService;
import service.DataStoreService;
import utils.CsvUtils;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculationServiceTest {
    private CalculationService calculationService = ServiceFactory.getCalculationService();
    private DataStoreService dataStoreService = ServiceFactory.getDataStoreService();

    private static final String BASE_PATH = System.getProperty("user.dir") + "/src/test/resources/input";
    private static final String OUTPUT_BASE_PATH = System.getProperty("user.dir") + "/src/test/resources/output";
    private static final String INCOME_DEPARTMENTS_PATH = OUTPUT_BASE_PATH + "/income-by-department.csv";
    private static final String PERCENTILE_DEPARTMENTS_PATH = OUTPUT_BASE_PATH + "/income-95-by-department.csv";
    private static final String AVERAGE_INCOME_AGE_PATH = OUTPUT_BASE_PATH + "/income-average-by-age-range.csv";
    private static final String MEDIAN_AGE_DEPARTMENTS_PATH = OUTPUT_BASE_PATH + "/employee-age-by-department.csv";

    private List<Employee> employees = dataStoreService.loadEmployees(BASE_PATH);

    public CalculationServiceTest() throws IOException {
    }

    @Test
    public void testIncomeByDepartment() throws IOException {
        List<CsvWritable> expected = CsvUtils.readDepartmentsOutput(INCOME_DEPARTMENTS_PATH);
        List<CsvWritable> actual = calculationService.medianIncomeByDepartment(employees);

        assertThat(expected).hasSameElementsAs(actual);
    }

    @Test
    public void test95PercentileByDepartment() throws IOException {
        List<CsvWritable> expected = CsvUtils.readDepartmentsOutput(PERCENTILE_DEPARTMENTS_PATH);
        List<CsvWritable> actual = calculationService.percentile95ByDepartment(employees);

        assertThat(expected).hasSameElementsAs(actual);
    }

    @Test
    public void testAverageIncomeByAgeRange() throws IOException {
        List<CsvWritable> expected = CsvUtils.readAgeRangeIncome(AVERAGE_INCOME_AGE_PATH);
        List<CsvWritable> actual = calculationService.averageIncomeByAgeRange(employees);

        assertThat(expected).hasSameElementsAs(actual);
    }

    @Test
    public void testMedianAgeByDepartment() throws IOException {
        List<CsvWritable> expected = CsvUtils.readDepartmentsOutput(MEDIAN_AGE_DEPARTMENTS_PATH);
        List<CsvWritable> actual = calculationService.medianAgeByDepartment(employees);

        assertThat(expected).hasSameElementsAs(actual);
    }
}
