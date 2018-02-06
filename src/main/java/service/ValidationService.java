package service;

import csv.input.AgeCsv;
import csv.input.EmployeeCsv;

import java.util.Optional;

import static java.lang.Integer.parseInt;
import static java.lang.String.format;
import static java.lang.System.out;
import static java.util.Optional.empty;

public final class ValidationService {
    public Optional<AgeCsv> validateAgeLine(String line) {
        try {
            return convertAgeLine(line);
        } catch (Exception e) {
            out.println(format("Not able to parse line: \"%s\"", line));
            return empty();
        }
    }

    public Optional<EmployeeCsv> validateEmployee(String line) {
        try {
            return convertEmployeeLine(line);
        } catch (Exception e) {
            out.println(format("Not able to parse line: \"%s\"", line));
            return empty();
        }
    }

    private Optional<AgeCsv> convertAgeLine(String line) {
        String[] elements = line.split(",");

        if (elements.length != 2) {
            out.println(format("Csv line in ages.csv expected with 2 elements. Line: \"%s\"", line));
            return empty();
        }

        String name = elements[0];
        Integer age = Integer.valueOf(elements[1]);

        return Optional.of(new AgeCsv(name, age));
    }

    private Optional<EmployeeCsv> convertEmployeeLine(String line) {
        String[] elements = line.split(",");

        if (elements.length != 4) {
            out.println(format("Csv line in employees.csv expected with 4 elements. Line: \"%s\"", line));
            return empty();
        }

        int departmentId = parseInt(elements[0]);
        String name = elements[1];
        String gender = elements[2];
        Double salary = Double.valueOf(elements[3]);

        return Optional.of(new EmployeeCsv(departmentId, name, gender, salary));
    }

}