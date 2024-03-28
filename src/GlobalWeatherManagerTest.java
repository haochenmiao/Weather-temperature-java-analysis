import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This test out many functions weather manager can do and accomplish
 * @author      Haochen Miao
 * @version     1/30/2023
 */
class GlobalWeatherManagerTest {
    private GlobalWeatherManager manager;
    private File temperatureFile;

    @BeforeEach
    void setUp() throws FileNotFoundException {
        temperatureFile = new File("city_temperature.csv");
        manager = new GlobalWeatherManager(temperatureFile);
    }

    @AfterEach
    void tearDown() {
        manager = null;
        temperatureFile = null;
    }

    @Test
    void getReadingCountTest() {
        int expected = 2885064;
        int actual = manager.getReadingCount();
        assertEquals(expected, actual);
    }

    @Test
    void getReadingTest() {
        int index = 0;
        WeatherReading expected = new WeatherReading("", "Albania", "", "Tirana", 1, 1,
                1995, -99.0);
        WeatherReading actual = manager.getReading(index);
        assertEquals(expected, actual);
    }

    @Test
    void getReadingsTest() {
        int index = 0;
        int count = 2;
        WeatherReading[] expected = {
                new WeatherReading("", "Albania", "", "Tirana", 1, 1, 1995, -99.0),
                new WeatherReading("", "Albania", "", "Tirana", 1, 2, 1995, -99.0)
        };
        WeatherReading[] actual = manager.getReadings(index, count);
        assertArrayEquals(expected, actual);
    }

    @Test
    void getReadingsWithFilterTest() {
        int index = 0;
        int count = 2;
        int month = 1;
        int day = 1;
        WeatherReading[] expected = {
                new WeatherReading("", "Albania", "", "Tirana", 1, 1, 1995, -99.0),
                new WeatherReading("", "Albania", "", "Tirana", 1, 1, 1996, -99.0)
        };
        WeatherReading[] actual = manager.getReadings(index, count, month, day);
        assertArrayEquals(expected, actual);
    }

    @Test
    void getCityListStatsTest() {
        String country = "Albania";
        String state = "";
        String city = "Tirana";
        int[] years = new int[]{2010, 2009, 2008, 2007, 2006, 2005, 2004, 2003, 2002,
                2001, 2000, 1999, 1998, 1997, 1996,
                1995, 2011, 2012, 2013, 2014, 2015, 2016, 2017, 2018, 2019, 2020};
        CityListStats expected = new CityListStats(0, 3632, years);
        CityListStats actual = manager.getCityListStats(country, state, city);
        assertEquals(expected, actual);
    }

    @Test
    void iteratorTest() {
        Iterator<WeatherReading> iterator = manager.iterator();
        WeatherReading expected = new WeatherReading("", "Albania", "", "Tirana",
                1, 1, 1995, -99.0);
        WeatherReading actual = iterator.next();
        assertEquals(expected, actual);
    }

    @Test
    void getTemperatureLinearRegressionSlopeTest() {
        WeatherReading[] readings = {
                new WeatherReading("", "Albania","","Tirana", 4, 23, 1995, -99.0),
                new WeatherReading("","Albania","","Tirana",4,23,1996,-99.0),
                new WeatherReading("","Albania","","Tirana",4,23,1997,-99.0),
                new WeatherReading("","Albania","","Tirana",4,23,1998,-99.0),
                new WeatherReading("","Albania","","Tirana",4,23,1999,60.2),
                new WeatherReading("","Albania","","Tirana",4,23,2000,65.9),
                new WeatherReading("","Albania","","Tirana",4,23,2001,52.3),
                new WeatherReading("","Albania","","Tirana",4,23,2002,58.4),
                new WeatherReading("","Albania","","Tirana",4,23,2003,58.0),
                new WeatherReading("","Albania","","Tirana",4,23,2004,63.9),};


        //Test getTemperatureLinearRegressionSlope
        double expectedLinearRegressionSlope = 0.025714285707189925;
        double actualLinearRegressionSlope = manager.getTemperatureLinearRegressionSlope(readings);
        assertEquals(expectedLinearRegressionSlope, actualLinearRegressionSlope, "getTemperatureLinearRegressionSlope returns incorrect slope");

        //Test calcLinearRegressionSlope
        Integer[] x = new Integer[] {2000, 2001, 2002};
        Double[] y = new Double[] {33.8, 42.6, 36.2};
        expectedLinearRegressionSlope = 1.199999999992239;
        actualLinearRegressionSlope = manager.calcLinearRegressionSlope(x, y);
        //Check if the actual linear regression slope matches the expected linear regression slope
        assertEquals(expectedLinearRegressionSlope, actualLinearRegressionSlope);
    }
}