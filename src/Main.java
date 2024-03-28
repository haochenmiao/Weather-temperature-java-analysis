import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Arrays;

/**
 * @version     1/30/2023
 * @author      Haochen Miao
 * this class puts different functions of weather management into practice with
 * capabilities
 *
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("city_temperature.csv");
        GlobalWeatherManager weatherManager = new GlobalWeatherManager(file);
        WeatherReading[] readingsWithMonthAndDay = weatherManager.getReadings(0, 2,
                1, 1);
        System.out.println("Readings with month 1 and day 1 in index 0: " +
                Arrays.toString(readingsWithMonthAndDay));
        int readingCount = weatherManager.getReadingCount();
        System.out.println("Reading count: " + readingCount);
        WeatherReading reading0 = weatherManager.getReading(0);
        System.out.println("Reading at index 0: " + reading0);
        WeatherReading[] readings = weatherManager.getReadings(0, 10);
        System.out.println("Readings from 0 to 10: " + Arrays.toString(readings));
        CityListStats cityListStats = weatherManager.getCityListStats("Albania", "", "Tirana");
        System.out.println("City list stats: " + cityListStats);
        Iterator<WeatherReading> iterator = weatherManager.iterator();
        int count = 7;
        System.out.println("Iterating through readings:");
        while (iterator.hasNext() && count > 0) {
            System.out.println(iterator.next());
            count--;
        }
        WeatherReading[] readingsForRegression = weatherManager.getReadings(0, 10, 4, 23);
        double temperatureLinearRegressionSlope = weatherManager.
                getTemperatureLinearRegressionSlope(readingsForRegression);
        System.out.println("Temperature linear regression slope: " +
                temperatureLinearRegressionSlope);
        Integer[] x = new Integer[]{2000, 2001, 2002};
        Double[] y = new Double[]{33.8, 42.6, 36.2};
        double linearRegressionSlope = weatherManager.calcLinearRegressionSlope(x, y);
        System.out.println("Linear regression slope: " + linearRegressionSlope);




    }


}
