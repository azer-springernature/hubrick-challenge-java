import factory.ServiceFactory;
import model.Employee;
import service.CalculationService;
import service.CsvService;

import java.io.IOException;
import java.util.List;

import static config.Configuration.*;
import static java.lang.System.exit;
import static java.lang.System.out;

public class HubrickStatisticsApplication {
    public static void main(final String[] args) {
        if (args.length != 1) {
            out.println("Application excepts only one argument.");
            exit(-1);
        }

        String dataPath = args[0];
        try {
            CalculationService calculationService = ServiceFactory.getCalculationService();
            CsvService csvService = ServiceFactory.getCsvService();

            List<Employee> employees = ServiceFactory.getDataStoreService().loadEmployees(dataPath);

            csvService.writeAsCsv(AVERAGE_INCOME_BY_RANGE_FILE, AVERAGE_INCOME_BY_RANGE_HEADER, calculationService.averageIncomeByAgeRange(employees));
            csvService.writeAsCsv(EMPLOYEE_AGE_BY_DEPARTMENT_FILE, EMPLOYEE_AGE_BY_DEPARTMENT_HEADER, calculationService.medianAgeByDepartment(employees));
            csvService.writeAsCsv(INCOME_BY_DEPARTMENT_FILE, INCOME_BY_DEPARTMENT_HEADER, calculationService.medianIncomeByDepartment(employees));
            csvService.writeAsCsv(INCOME_95_BY_DEPARTMENT_FILE, INCOME_95_BY_DEPARTMENT_HEADER, calculationService.percentile95ByDepartment(employees));
        } catch (IOException e) {
            out.println("Error while reading data. Application will terminate.");
            exit(-1);
        }
    }
}
