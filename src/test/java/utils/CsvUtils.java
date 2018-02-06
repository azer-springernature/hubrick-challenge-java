package utils;

import csv.output.AgeIncomeRangeCsv;
import csv.output.CsvWritable;
import csv.output.DepartmentOutputCsv;
import model.Range;

import java.io.IOException;
import java.util.List;

import static java.nio.file.Files.lines;
import static java.nio.file.Paths.get;
import static java.util.stream.Collectors.toList;

public class CsvUtils {
    public static List<CsvWritable> readAgeRangeIncome(String path) throws IOException {
        return lines(get(path))
                .map(CsvUtils::parseIncomeByAgeRange)
                .collect(toList());
    }

    public static List<CsvWritable> readDepartmentsOutput(String path) throws IOException {
        return lines(get(path))
                .map(CsvUtils::createIncomeDepartment)
                .collect(toList());
    }

    private static DepartmentOutputCsv createIncomeDepartment(String line) {
        String[] elements = line.split(",");

        String departmentName = elements[0];
        String income = elements[1];

        return new DepartmentOutputCsv(departmentName, Double.valueOf(income));
    }

    private static AgeIncomeRangeCsv parseIncomeByAgeRange(String line) {
        String[] firstSplit = line.split("],");

        String[] secondSplit = firstSplit[0].replace("[", "").split(" ");

        Integer begin = Integer.valueOf(secondSplit[0]);
        Integer end = Integer.valueOf(secondSplit[1]);
        Double income = Double.valueOf(firstSplit[1]);

        return new AgeIncomeRangeCsv(new Range(begin, end), income);
    }
}
