package service;

import csv.input.AgeCsv;
import csv.input.DepartmentCsv;
import csv.input.EmployeeCsv;
import model.Department;
import model.Employee;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.lang.String.format;
import static java.lang.System.out;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static model.Gender.from;

public final class DataStoreService {
    private CsvService csvService;

    public DataStoreService(CsvService csvService) {
        this.csvService = csvService;
    }

    public List<Employee> loadEmployees(String dataPath) throws IOException {
        Map<String, Integer> employeeNameToAge = csvService.readAges(dataPath).stream().collect(toMap(AgeCsv::getName, AgeCsv::getAge));
        Map<Integer, String> sortIdToDepartment = sortIdToDepartmentName(dataPath);

        return csvService.readEmployees(dataPath).stream()
                .map(employeeCsv -> mapToEmployee(employeeCsv, employeeNameToAge, sortIdToDepartment))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(toList());
    }

    private Map<Integer, String> sortIdToDepartmentName(String dataPath) throws IOException {
        List<DepartmentCsv> sortedDepartments = csvService.readDepartmentsSorted(dataPath);

        return sortedDepartments.stream().collect(toMap(dep -> sortedDepartments.indexOf(dep) + 1, DepartmentCsv::getName));
    }

    private Optional<Employee> mapToEmployee(EmployeeCsv e, Map<String, Integer> employeeNameToAge, Map<Integer, String> sortIdToDepartment) {
        if (employeeNameToAge.containsKey(e.getName())) {
            int age = employeeNameToAge.get(e.getName());
            if (sortIdToDepartment.containsKey(e.getDepartmentId())) {
                String departmentName = sortIdToDepartment.get(e.getDepartmentId());
                return of(new Employee(new Department(e.getDepartmentId(), departmentName), e.getName(), from(e.getGender()), e.getSalary(), age));
            } else {
                out.println(String.format("Employee %s will not be loaded because department with ID %s does not exist", e.getName(), e.getDepartmentId()));
                return empty();
            }

        } else {
            out.println(format("Employee %s will not be loaded, because an entry in ages.csv can not be found", e.getName()));
            return empty();
        }
    }
}