package service;

import csv.input.AgeCsv;
import csv.input.DepartmentCsv;
import csv.input.EmployeeCsv;
import csv.output.CsvWritable;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

import static config.Configuration.*;
import static java.lang.String.format;
import static java.lang.System.out;
import static java.nio.file.Files.write;
import static java.nio.file.Paths.get;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public final class CsvService {
    private ValidationService validationService;

    public CsvService(ValidationService validationService) {
        this.validationService = validationService;
    }

    public List<DepartmentCsv> readDepartmentsSorted(String dataPath) throws IOException {
        return readAllLines(dataPath + DEPARTMENTS_FILE).stream()
                .map(DepartmentCsv::new)
                .sorted(comparing(DepartmentCsv::getName))
                .collect(toList());
    }

    public List<AgeCsv> readAges(String dataPath) throws IOException {
        return readAllLines(dataPath + AGES_FILE).stream()
                .map(line -> validationService.validateAgeLine(line))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(toList());
    }

    public List<EmployeeCsv> readEmployees(String dataPath) throws IOException {
        return readAllLines(dataPath + EMPLOYEES_FILE).stream()
                .map(line -> validationService.validateEmployee(line))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(toList());
    }

    public void writeAsCsv(String fileName, String header, List<CsvWritable> elements) {
        if (!elements.isEmpty()) {
            String headerLine = format("%s\n", header);
            String output = headerLine.concat(elements.stream().map(CsvWritable::toCsvLine).collect(joining("\n")));
            writeTextToFile(fileName, output);
        }
    }

    private void writeTextToFile(String fileName, String output) {
        try {
            write(get(fileName), output.getBytes());
        } catch (IOException e) {
            out.println(format("Unable to write to file: %s", fileName));
        }
    }

    private List<String> readAllLines(String path) throws IOException {
        try {
            List<String> lines = Files.readAllLines(get(path));

            if (lines.isEmpty()) {
                String errorMessage = "Trying to open empty text file";
                out.println(errorMessage);
                throw new IOException(errorMessage);
            }

            return lines;
        } catch (IOException e) {
            out.println(format("Unable to read from file: %s", path));
            throw e;
        }
    }
}