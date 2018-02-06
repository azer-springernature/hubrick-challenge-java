package factory;

import service.*;

public class ServiceFactory {
    public static CalculationService getCalculationService() {
        return new CalculationService(new StatisticsService());
    }

    public static CsvService getCsvService() {
        return new CsvService(new ValidationService());
    }

    public static DataStoreService getDataStoreService() {
        return new DataStoreService(getCsvService());
    }
}
