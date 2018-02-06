package model;

import java.util.List;
import java.util.Objects;

import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.rangeClosed;

public class Range {
    private Integer begin;
    private Integer end;

    public Range(Integer begin, Integer end) {
        this.begin = begin;
        this.end = end;
    }

    public Integer getBegin() {
        return begin;
    }

    public Integer getEnd() {
        return end;
    }

    public List<Integer> elements() {
        return rangeClosed(begin, end).boxed().collect(toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Range range = (Range) o;
        return Objects.equals(begin, range.begin) &&
                Objects.equals(end, range.end);
    }

    @Override
    public int hashCode() {

        return Objects.hash(begin, end);
    }
}
