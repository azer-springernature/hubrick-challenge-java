package csv.output;

import java.util.Objects;

import static java.lang.String.format;

public class DepartmentOutputCsv implements CsvWritable {
    private String departmentName;
    private Number value;

    public DepartmentOutputCsv(String departmentName, Number value) {
        this.departmentName = departmentName;
        this.value = value;
    }

    @Override
    public String toCsvLine() {
        return format("%s,%s", departmentName, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        DepartmentOutputCsv that = (DepartmentOutputCsv) o;
        return Objects.equals(departmentName, that.departmentName) &&
                Objects.equals(value.toString(), that.value.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(departmentName, value);
    }

    @Override
    public String toString() {
        return String.format("Dep(%s, %s)", departmentName, value);
    }
}
