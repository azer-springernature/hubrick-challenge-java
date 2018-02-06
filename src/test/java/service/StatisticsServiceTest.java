package service;

import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;

public class StatisticsServiceTest {

    private StatisticsService statisticsService = new StatisticsService();

    @Test
    public void shouldCalculateCorrectMedianForOddList() {
        List<Number> list = asList(9, 1, 5, 3, 10, 7, 11, 20, 21);
        assertEquals(9, statisticsService.calculateMedian(list).intValue());
    }

    @Test
    public void shouldCalculateCorrectMedianForEvenList() {
        List<Number> list = asList(21, 3, 8, 5, 1, 9, 11, 20);
        assertEquals(8.5, statisticsService.calculateMedian(list).doubleValue(), 0.001);
    }

    @Test
    public void shouldCalculateCorrectMedianForListWithOneElement() {
        assertEquals(4, statisticsService.calculateMedian(singletonList(4)).doubleValue(), 0.001);
    }

    @Test
    public void shouldCalculateCorrectMedianForListWithTwoElements() {
        assertEquals(5, statisticsService.calculateMedian(asList(4, 6)).doubleValue(), 0.001);
    }

    @Test
    public void shouldCalculateCorrectMedianForListWithTreeElements() {
        assertEquals(5, statisticsService.calculateMedian(asList(4, 6, 5)).doubleValue(), 0.001);
    }

    @Test
    public void shouldCalculateCorrectPercentile() {
        assertEquals(20, statisticsService.calculatePercentile(asList(9, 1, 5, 3, 10, 7, 11, 20, 21, 45, 23, 24, 32, 1, 24, 8), 60).doubleValue(), 0.001);
        assertEquals(45, statisticsService.calculatePercentile(asList(9, 1, 5, 3, 10, 7, 11, 20, 21, 45, 23, 24, 32, 1, 24, 8), 95).doubleValue(), 0.001);
    }

    @Test
    public void shouldCalculateCorrectPercentileForTwoElementList() {
        assertEquals(1, statisticsService.calculatePercentile(asList(1, 9), 10).doubleValue(), 0.001);
        assertEquals(9, statisticsService.calculatePercentile(asList(9, 1), 60).doubleValue(), 0.001);
    }

    @Test
    public void shouldCalculateCorrectPercentileForOneElementList() {
        assertEquals(9, statisticsService.calculatePercentile(singletonList(9), 60).doubleValue(), 0.001);
    }

    @Test
    public void shouldCalculateCorrectCorrectAverage() {
        assertEquals(6.2, statisticsService.calculateAverage(asList(9., 7., 6., 5., 4.)), 0.001);
    }

    @Test
    public void shouldCalculateCorrectCorrectAverageForEmptyList() {
        assertEquals(0, statisticsService.calculateAverage(emptyList()), 0.001);
    }
}