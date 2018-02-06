package utils;

import java.text.DecimalFormat;

import static java.math.RoundingMode.HALF_UP;

public final class NumberUtils {
    private NumberUtils() {}

    public static Double roundToDouble(Number number) {
        DecimalFormat formatter = new DecimalFormat("#0.00");
        formatter.setRoundingMode(HALF_UP);

        return Double.valueOf(formatter.format(number));
    }

    public static Integer roundToInt(Number number) {
        DecimalFormat formatter = new DecimalFormat("#0");
        formatter.setRoundingMode(HALF_UP);

        return Integer.valueOf(formatter.format(number));
    }
}