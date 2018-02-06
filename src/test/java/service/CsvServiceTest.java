package service;

import csv.input.AgeCsv;
import csv.input.DepartmentCsv;
import csv.input.EmployeeCsv;
import factory.ServiceFactory;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

public class CsvServiceTest {
    private static final String BASE_PATH = System.getProperty("user.dir") + "/src/test/resources/input";
    private static final String EMPTY_FILES_BASE_PATH = System.getProperty("user.dir") + "/src/test/resources/emptyfiles";

    private CsvService csvService = ServiceFactory.getCsvService();

    @Test
    public void shouldReadAllEmployees() throws IOException {
        List<EmployeeCsv> employees = csvService.readEmployees(BASE_PATH);

        assertEquals(99, employees.size());
    }

    @Test
    public void shouldReadAllAges() throws IOException {
        List<AgeCsv> ages = csvService.readAges(BASE_PATH);

        assertEquals(101, ages.size());
    }

    @Test
    public void shouldReadAllDepartments() throws IOException {
        List<DepartmentCsv> departments = csvService.readDepartmentsSorted(BASE_PATH);

        assertEquals(7, departments.size());
    }

    @Test
    public void shouldReadAndSortDepartments() throws IOException {
        List<DepartmentCsv> departments = csvService.readDepartmentsSorted(BASE_PATH);

        List<String> sortedDepartments =
                asList("Accounting", "Business Development", "Human Resources", "Information Technology", "Marketing", "Public Relations", "Sales");

        assertEquals(sortedDepartments, departments.stream().map(DepartmentCsv::getName).collect(toList()));
    }

    @Test(expected = IOException.class)
    public void shouldThrowExceptionIfFileDoesNotExistForDepartments() throws IOException {
        csvService.readDepartmentsSorted("invalidPath");
    }

    @Test(expected = IOException.class)
    public void shouldThrowExceptionIfFileDoesNotExistForAges() throws IOException {
        csvService.readAges("invalidPath");
    }

    @Test(expected = IOException.class)
    public void shouldThrowExceptionIfFileDoesNotExistForEmployees() throws IOException {
        csvService.readEmployees("invalidPath");
    }

    @Test(expected = IOException.class)
    public void shouldThrowExceptionIfFileIsEmptyForEmployees() throws IOException {
        csvService.readEmployees(EMPTY_FILES_BASE_PATH);
    }

    @Test(expected = IOException.class)
    public void shouldThrowExceptionIfFileIsEmptyForAges() throws IOException {
        csvService.readEmployees(EMPTY_FILES_BASE_PATH);
    }

    @Test(expected = IOException.class)
    public void shouldThrowExceptionIfFileIsEmptyForDepartments() throws IOException {
        csvService.readEmployees(EMPTY_FILES_BASE_PATH);
    }
}
