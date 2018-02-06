package service;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static utils.NumberUtils.roundToDouble;

public class StatisticsService {
    public double calculateAverage(List<Double> list) {
        double average;
        if (!list.isEmpty()) {
            average = list.stream().mapToDouble(Number::doubleValue).sum() / list.size();
        } else {
            average = 0.;
        }

        return roundToDouble(average);
    }

    public Number calculateMedian(List<Number> list) {
        List<Number> sortedList = list.stream().sorted().collect(toList());

        if (sortedList.size() % 2 == 1) {
            return sortedList.get(list.size() / 2).doubleValue();
        } else {
            int half = sortedList.size() / 2;
            Number left = sortedList.get(half - 1);
            Number right = sortedList.get(half);
            return (left.doubleValue() + right.doubleValue()) / 2.;
        }
    }

    public Number calculatePercentile(List<Number> list, int nthPercentile) {
        List<Number> sortedList = list.stream().sorted().collect(toList());
        return sortedList.get((int) (nthPercentile / 100. * sortedList.size()));
    }
}