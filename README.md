# weather-temperature-java-analysis

## Global Weather Data Management System

## Description
This Java-based project offers a robust solution for managing and analyzing global weather data. It provides functionalities to read, process, and analyze weather readings from a CSV file, offering insights through linear regression analysis, filtering data by date, and generating statistics for specific locations.

## Features
- Importing weather data from a CSV file.
- Counting the total number of weather readings.
- Retrieving specific weather reading(s) by index.
- Filtering weather readings by date.
- Generating statistics for weather data in a specified city, including linear regression analysis to predict trends.
- Iterating through weather data.

## How to Run
1. Ensure Java (JDK 11 or newer) is installed on your system.
2. Compile the source code using a Java compiler. For example:
    ```sh
    javac GlobalWeatherManager.java WeatherReading.java CityListStats.java Main.java
    ```
    in the src folder
3. Run the compiled `Main` class:
    ```sh
    java Main
    ```

## File Structure
- `GlobalWeatherManagerInterface.java`: Interface outlining the functionalities of the weather data management system.
- `GlobalWeatherManager.java`: Implements the interface, providing logic to manage and analyze weather data.
- `WeatherReading.java`: Defines the structure of a weather reading.
- `CityListStats.java`: Holds statistics for a specific city's weather data.
- `Main.java`: Entry point of the application demonstrating the use of `GlobalWeatherManager`.
- `GlobalWeatherManagerTest.java`: Contains unit tests for the system.
- `city_temperature.csv`: The CSV file containing weather data (ensure this file is in the same directory as the executable code).
- UML Diagrams (`proj02.object.object.violet.html` and `proj02.class.class.violet.html`): Provide a visual overview of the project's structure.

## Diagrams Screenshots
![Class UML Diagram](https://github.com/haochenmiao/weather-temperature-java-analysis/blob/main/img/class_uml_diagram.png)

![Object UML Diagram](https://github.com/haochenmiao/weather-temperature-java-analysis/blob/main/img/object_uml_diagram.png)


## Dependencies
- Java Development Kit (JDK) 11 or newer.
- JUnit (for running unit tests in `GlobalWeatherManagerTest.java`).

## Contributing
To contribute to this project:
1. Fork the repository.
2. Create a new branch (`git checkout -b feature/AmazingFeature`).
3. Make your changes.
4. Commit your changes (`git commit -m 'Add some AmazingFeature'`).
5. Push to the branch (`git push origin feature/AmazingFeature`).
6. Open a pull request.

## License
MIT

## Contact
Contact me via linkedin (https://www.linkedin.com/in/uw-datascientist/)
