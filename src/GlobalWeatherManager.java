import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * stores and retrieves a collections of weather readings
 * provides methods to retrieve statistics about weather readings
 * for a given country, state and city
 * @author      Haochen Miao
 * @version     1/30/2023
 *
 */
public class GlobalWeatherManager implements GlobalWeatherManagerInterface {
    /** Arraylist of weather readings*/
    private ArrayList<WeatherReading> readings;

    /**
     * read the weather data file and sort them into the array list
     * @param temperatureFile       file that stores data for the weather in different
     *                              countries
     * @throws FileNotFoundException    if the file is not found or accessed, throw
     * file not found.
     */
    public GlobalWeatherManager(File temperatureFile) throws FileNotFoundException {
        this.readings = new ArrayList<>();
        //List<WeatherReading> noRegionReading = new ArrayList<WeatherReading>();

        Scanner scan = new Scanner(temperatureFile);
        scan.nextLine();

        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] data = line.split(",");
            String region = data[0];
            String country = data[1];
            String state = data[2];
            String city = data[3];
            int month = Integer.parseInt(data[4]);
            int day = Integer.parseInt(data[5]);
            int year = Integer.parseInt(data[6]);
            double avgTemperature = Double.parseDouble(data[7]);

            WeatherReading weatherReading = new WeatherReading(region, country,
                    state, city, month, day, year, avgTemperature);
            this.readings.add(weatherReading);
        }
        scan.close();
        Collections.sort(readings);

    }

    /**
     * Retrieves a count of readings
     *
     * @return count of readings
     */
    @Override
    public int getReadingCount() {
        return readings.size();
    }

    /**
     * Retrieves the weather reading at the specified index.
     *
     * @param index the index for the desired reading; must be a valid element index.
     * @return the reading at the specified index.
     */
    @Override
    public WeatherReading getReading(int index) {
        return readings.get(index);
    }

    /**
     * Retrieves a set of weather readings.
     *
     * @param index the index of the first reading; must be a valid index.
     * @param count the count of readings to potentially include.  Must be at least 1.
     *              Must imply a valid range;
     *              index + count must be less than the total reading count.
     * @return an array of readings.
     */
    @Override
    public WeatherReading[] getReadings(int index, int count) {
        if (index < 0 || index >= readings.size()) {
            throw new IllegalArgumentException("Invalid index");
        }
        if (count < 1) {
            throw new IllegalArgumentException("Invalid count");
        }
        if (index + count > readings.size()) {
            throw new IllegalArgumentException("Index + count is greater than the total readings count");
        }
        WeatherReading[] result = new WeatherReading[count];
        for (int i = 0; i < count; i++) {
            result[i] = readings.get(index + i);
        }
        return result;
    }

    /**
     * Retrieves a set of weather readings.
     *
     * @param index the index of the first reading; must be a valid index.
     * @param count the count of readings to potentially include.  Must be at least 1.
     *            Must imply a valid range;
     *              index + count must be less than the total reading count.
     * @param month the month to filter; must be a valid month (1 to 12).
     * @param day   the day to filter; must be a valid day (1 to 31).
     * @return an array of readings matching the specified criteria.
     */
    @Override
    public WeatherReading[] getReadings(int index, int count, int month, int day) {
        int i = index;
        int c = 0;
        WeatherReading[] filteredReadings = new WeatherReading[count];
        for (WeatherReading wr : readings) {
            if (wr.month() == month && wr.day() == day) {
                filteredReadings[c++] = wr;
                if (c == count) {
                    break;
                }
            }
        }
        return Arrays.copyOf(filteredReadings, c);

    }

    /**
     * Retrieves key list statistics for the specified country/state/city.
     * Student note:  okay to use an additional ArrayList in this method.
     *
     * @param country the country of interest; must not be null or blank.
     * @param state   the state of interest; must not be null or blank.
     * @param city    the city of interest; must not be null or blank.
     * @return the list stats for the specified city, or null if not found.
     */
    @Override

    public CityListStats getCityListStats(String country, String state, String city) {
        //System.out.println("Input: country = " + country + ", state = " + state + ", city = " + city); //debug
        int index = binarySearchCity(readings, country, state, city);
        //System.out.println("Binary search result: index = " + index); //debug
        if (index == -1) {
            return null;
        }
        int startingIndex = index;
        int count = 0;
        ArrayList<Integer> years = new ArrayList<>();
        while (startingIndex >= 0 && readings.get(startingIndex).country().equals(country)
                && readings.get(startingIndex).state().equals(state)
                && readings.get(startingIndex).city().equals(city)) {
            int year = readings.get(startingIndex).year();
            if (!years.contains(year)) {
                years.add(year);
            }
            startingIndex--;
        }
        startingIndex++;
        System.out.println("starting index: " + startingIndex);
        int endIndex = index;
        while (endIndex < readings.size() && readings.get(endIndex).country().equals(country)
                && readings.get(endIndex).state().equals(state)
                && readings.get(endIndex).city().equals(city)) {
            int year = readings.get(endIndex).year();
            if (!years.contains(year)) {
                years.add(year);
            }
            count++;
            endIndex++;
        }
        System.out.println("count: " + count);

        int[] yearsArray = new int[years.size()];
        for (int i = 0; i < yearsArray.length; i++) {
            yearsArray[i] = years.get(i);
        }

        return new CityListStats(startingIndex, count, yearsArray);
    }

    /**
     * Helps to find the binary search for the city
     * @param readings  The arrayList of the weather data in file
     * @param country   The name(s) of the country in weather data
     * @param state     The name(s) of the state in weather data
     * @param city      The name(s) of the city in weather data
     * @return mid  if the city matches, otherwise, return -1
     */
    private int binarySearchCity(List<WeatherReading> readings, String country,
                                 String state, String city) {
        int min = 0;
        int max = readings.size() - 1;
        while (min <= max) {
            int mid = (min + max) / 2;
            int compare = readings.get(mid).compareCiyStats(country, state, city);
            if (compare == 0) {
                return mid;
            } else if (compare < 0) {
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }
        return -1;
    }

    /**
     * Retrieves an iterator over all weather readings.
     *
     * @return strongly typed iterator for.
     */
    @Override
    public Iterator<WeatherReading> iterator() {
        return readings.iterator();
    }

    /**
     * Does a linear regression analysis on the data, using x = year and y = temperature.
     * Calculates the slope of a best-fit line using the Least Squares method.
     * For more information
     * on that method, see <a href="https://www.youtube.com/watch?v=P8hT5nDai6A">...</a>
     * Student note:  okay to use two additional ArrayLists in this method.
     *
     * @param readings array of readings to analyze.  Should typically be readings
     *                 for a single day over
     *                 a number of years; larger data sets will likely yield
     *                 better results.  Ignores
     *                 temperature data of -99.0, a default value indicating no
     *                 temperature data was present.
     *                 Must not be null and must contain at least two readings.
     * @return slope of best-fit line; positive slope indicates increasing temperatures.
     */
    @Override
    public double getTemperatureLinearRegressionSlope(WeatherReading[] readings) {
        ArrayList<Double> year = new ArrayList<>();
        ArrayList<Double> temp = new ArrayList<>();
        for (WeatherReading reading : readings) {
            if(reading.avgTemperature() != -99.0) {
                year.add((double)reading.year());
                temp.add(reading.avgTemperature());
            }
        }
        double sumYear = 0.0;
        double sumTemp = 0.0;
        double sumYearSquared = 0.0;
        double sumYearTemp = 0.0;
        int n = year.size();

        for (int i = 0; i < n; i++) {
            sumYear = sumYear + year.get(i);
            sumTemp = sumTemp + temp.get(i);
            sumYearSquared = sumYearSquared + Math.pow(year.get(i), 2);
            sumYearTemp = sumYearTemp + year.get(i) * temp.get(i);
        }

        double numerator = (n * sumYearTemp) - (sumYear * sumTemp);
        double denominator = (n * sumYearSquared) - (Math.pow(sumYear, 2));

        if (denominator == 0.0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return numerator/denominator;
    }

    /**
     * Calculates the slope of the best-fit line calculated using the Least Squares
     * method.
     * For more information
     * on that method, see <a href="https://www.youtube.com/watch?v=P8hT5nDai6A">...</a>
     *
     * @param x an array of x values; must not be null and must contain at least
     *          two elements.
     * @param y an array of y values; must be the same length as the x array
     *          and must not be null.
     * @return the slope of the best-fit line
     */
    @Override
    public double calcLinearRegressionSlope(Integer[] x, Double[] y) {
        if (x == null || y == null || x.length < 2 || x.length != y.length) {
            throw new IllegalArgumentException("The input arrays must not be null" +
                    "and must have at least two elements with the same length");
        }

        double xSum = 0;
        double ySum = 0;
        double xSquaredSum = 0;
        double xySum = 0;
        int n = x.length;

        for (int i = 0; i < n; i++) {
            xSum += x[i];
            ySum += y[i];
            xSquaredSum += Math.pow(x[i], 2);
            xySum += x[i] * y[i];
        }

        double slope = (n * xySum - xSum * ySum) / (n * xSquaredSum - Math.pow(xSum, 2));
        return slope;
    }
}
