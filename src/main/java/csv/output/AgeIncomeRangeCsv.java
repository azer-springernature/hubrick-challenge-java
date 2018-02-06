package csv.output;

import model.Range;

import java.util.Objects;

import static java.lang.String.format;

public class AgeIncomeRangeCsv implements CsvWritable {
    private Range range;
    private double income;

    public AgeIncomeRangeCsv(Range range, double income) {
        this.range = range;
        this.income = income;
    }

    @Override
    public String toCsvLine() {
        return format("[%s %s],%s", range.getBegin(), range.getEnd(), income);
    }

    @Override
    public String toString() {
        return format("Range([%s, %s] = %s)", range.getBegin(), range.getEnd(), income);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AgeIncomeRangeCsv that = (AgeIncomeRangeCsv) o;
        return Double.compare(that.income, income) == 0 &&
                Objects.equals(range, that.range);
    }

    @Override
    public int hashCode() {
        return Objects.hash(range, income);
    }
}
