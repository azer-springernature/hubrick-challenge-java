package service;

import csv.input.AgeCsv;
import csv.input.EmployeeCsv;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidationServiceTest {
    private ValidationService validationService = new ValidationService();

    @Test
    public void shouldImportValidAgeLine() {
        String line = "Julius Glover,44";

        Optional<AgeCsv> ageCsvOpt = validationService.validateAgeLine(line);

        assertTrue(ageCsvOpt.isPresent());
    }

    @Test
    public void shouldNotImportAgeLineWithEmptyAge() {
        String line = "Julius Glover,";

        Optional<AgeCsv> ageCsvOpt = validationService.validateAgeLine(line);

        assertFalse(ageCsvOpt.isPresent());
    }

    @Test
    public void shouldNotImportAgeLineWithAgeAsString() {
        String line = "Julius Glover,4O";

        Optional<AgeCsv> ageCsvOpt = validationService.validateAgeLine(line);

        assertFalse(ageCsvOpt.isPresent());
    }

    @Test
    public void shouldNotImportAgeLineWithThreeElements() {
        String line = "Julius Glover,34,56";

        Optional<AgeCsv> ageCsvOpt = validationService.validateAgeLine(line);

        assertFalse(ageCsvOpt.isPresent());
    }

    @Test
    public void shouldNotImportAgeLineWithDifferentDelimiter() {
        String line = "Julius Glover|34";

        Optional<AgeCsv> ageCsvOpt = validationService.validateAgeLine(line);

        assertFalse(ageCsvOpt.isPresent());
    }

    @Test
    public void shouldImportValidEmployeeLine() {
        String line = "1,Judith Ford,f,2820.00";

        Optional<EmployeeCsv> employeeCsv = validationService.validateEmployee(line);

        assertTrue(employeeCsv.isPresent());
    }

    @Test
    public void shouldImportEmployeeWithMissingGender() {
        String line = "1,Judith Ford,,2820.00";

        Optional<EmployeeCsv> employeeCsv = validationService.validateEmployee(line);

        assertTrue(employeeCsv.isPresent());
    }

    @Test
    public void shouldImportEmployeeWithUnknownGender() {
        String line = "1,Judith Ford,d,2820.00";

        Optional<EmployeeCsv> employeeCsv = validationService.validateEmployee(line);

        assertTrue(employeeCsv.isPresent());
    }

    @Test
    public void shouldNotImportEmployeeWithLessThen4Elements() {
        String line = "1,Judith Ford,2820.00";

        Optional<EmployeeCsv> employeeCsv = validationService.validateEmployee(line);

        assertFalse(employeeCsv.isPresent());
    }

    @Test
    public void shouldNotImportEmployeeWithMoreThen4Elements() {
        String line = "1,Judith Ford,d,2820.00,45";

        Optional<EmployeeCsv> employeeCsv = validationService.validateEmployee(line);

        assertFalse(employeeCsv.isPresent());
    }

    @Test
    public void shouldNotImportEmployeeLineWithDifferentDelimiter() {
        String line = "1|Judith Ford|d|2820.00|45";

        Optional<EmployeeCsv> employeeCsv = validationService.validateEmployee(line);

        assertFalse(employeeCsv.isPresent());
    }
}
