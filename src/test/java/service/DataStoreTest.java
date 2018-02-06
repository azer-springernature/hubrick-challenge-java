package service;

import factory.ServiceFactory;
import model.Employee;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;

public class DataStoreTest {
    private static final String BASE_PATH = System.getProperty("user.dir") + "/src/test/resources/data-store-tests";

    private DataStoreService dataStoreService = ServiceFactory.getDataStoreService();

    @Test
    public void shouldReadAllEmployees() throws IOException {
        List<Employee> employees = dataStoreService.loadEmployees(format("%s/case-1", BASE_PATH));

        assertEquals(1, employees.size());
        assertEquals("Opal Ballard", employees.get(0).getName());
    }

    @Test
    public void shouldReadAllAges() throws IOException {
        List<Employee> employees = dataStoreService.loadEmployees(format("%s/case-2", BASE_PATH));

        assertEquals(1, employees.size());
        assertEquals("Otis Bell", employees.get(0).getName());
    }
}
