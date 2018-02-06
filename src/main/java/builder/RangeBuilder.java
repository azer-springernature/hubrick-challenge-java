package builder;

import model.Range;

import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import static java.lang.System.out;
import static java.util.stream.Stream.empty;
import static utils.NumberUtils.roundToInt;

public class RangeBuilder {
    public static Stream<Range> buildRanges(int min, int max, Integer rangeFactor) {
        int size = ((max - min) + 1);
        double step = size / rangeFactor.doubleValue();

        if (size < rangeFactor) {
            out.println("Range factor bigger then maximum range");
            return empty();
        }

        return DoubleStream
                .iterate(min, begin -> begin + step)
                .limit(rangeFactor)
                .mapToObj(begin -> new Range(roundToInt(begin), roundToInt(begin + step - 1)));
    }
}