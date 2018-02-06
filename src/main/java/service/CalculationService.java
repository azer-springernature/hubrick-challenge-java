package service;

import csv.output.AgeIncomeRangeCsv;
import csv.output.CsvWritable;
import csv.output.DepartmentOutputCsv;
import model.Department;
import model.Employee;
import model.Range;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import static builder.RangeBuilder.buildRanges;
import static config.Configuration.PERCENTILE_FOR_CALCULATION;
import static config.Configuration.RANGE_FACTOR;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public final class CalculationService {
    private StatisticsService statisticsService;

    public CalculationService(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    public List<CsvWritable> medianIncomeByDepartment(List<Employee> employees) {
        return getDepartmentToEmployeesMap(employees)
                .entrySet().stream()
                .map(entry -> calculateMedian(entry, mapToSalaries(entry)))
                .collect(toList());
    }

    public List<CsvWritable> medianAgeByDepartment(List<Employee> employees) {
        return getDepartmentToEmployeesMap(employees)
                .entrySet().stream()
                .map(entry -> calculateMedian(entry, mapToAges(entry)))
                .collect(toList());
    }

    public List<CsvWritable> percentile95ByDepartment(List<Employee> employees) {
        return getDepartmentToEmployeesMap(employees)
                .entrySet().stream()
                .map(this::calculatePercentile)
                .collect(toList());
    }

    public List<CsvWritable> averageIncomeByAgeRange(List<Employee> employees) {
        Optional<Integer> max = employees.stream().map(Employee::getAge).max(Integer::compareTo);
        Optional<Integer> min = employees.stream().map(Employee::getAge).min(Integer::compareTo);

        if (max.isPresent() && min.isPresent()) {
            return buildRanges(min.get(), max.get(), RANGE_FACTOR)
                    .map(range -> calculateAverage(range, employees))
                    .collect(toList());
        } else {
            return emptyList();
        }
    }

    private Map<Department, List<Employee>> getDepartmentToEmployeesMap(List<Employee> employees) {
        return employees.stream().collect(groupingBy(Employee::getDepartment));
    }

    private AgeIncomeRangeCsv calculateAverage(Range range, List<Employee> employees) {
        List<Double> salaries = employees.stream()
                .filter(employee -> range.elements().contains(employee.getAge()))
                .map(Employee::getSalary)
                .collect(toList());

        return new AgeIncomeRangeCsv(range, statisticsService.calculateAverage(salaries));
    }

    private DepartmentOutputCsv calculatePercentile(Entry<Department, List<Employee>> entry) {
        Number percentile = statisticsService.calculatePercentile(mapToSalaries(entry), PERCENTILE_FOR_CALCULATION);
        return new DepartmentOutputCsv(entry.getKey().getName(), percentile);
    }

    private DepartmentOutputCsv calculateMedian(Entry<Department, List<Employee>> entry, List<Number> numbers) {
        Number median = statisticsService.calculateMedian(numbers);
        return new DepartmentOutputCsv(entry.getKey().getName(), median);
    }

    private List<Number> mapToSalaries(Entry<Department, List<Employee>> entry) {
        return entry.getValue().stream().map(Employee::getSalary).collect(toList());
    }

    private List<Number> mapToAges(Entry<Department, List<Employee>> entry) {
        return entry.getValue().stream().map(Employee::getAge).collect(toList());
    }
}
