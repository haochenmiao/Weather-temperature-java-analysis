public record WeatherReading(String region, String country, String state,
                             String city, int month, int day, int year,
                             double avgTemperature) implements
        Comparable<WeatherReading> {

    public String region() {
        return region;
    }

    public String country() {
        return country;
    }

    public String state() {
        return state;
    }

    public String city() {
        return city;
    }

    public int month() {
        return month;
    }

    public int day() {
        return day;
    }

    public int year() {
        return year;
    }

    public double avgTemperature(){
        return avgTemperature;
    }

    @Override
    public int compareTo(WeatherReading other) {
        if (this.country.compareTo(other.country) != 0) {
            return this.country.compareTo(other.country);
        } else {
            if (this.state.compareTo(other.state) != 0) {
                return this.state.compareTo(other.state);
            } else {
                if (this.city.compareTo(other.city) != 0) {
                    return this.city.compareTo(other.city);
                } else {
                    if (Integer.compare(this.year, other.year) != 0) {
                        return Integer.compare(this.year, other.year);
                    } else {
                        if (Integer.compare(this.month, other.month) != 0) {
                            return Integer.compare(this.month, other.month);
                        } else {
                            return Integer.compare(this.day, other.day);
                        }
                    }
                }
            }
        }
    }

    public int compareCiyStats (String country, String state, String city) {
        if (this.country.compareTo(country) != 0) {
            return this.country.compareTo(country);
        } else {
            if (this.state.compareTo(state) != 0) {
                return this.state.compareTo(state);
            } else {
                return this.city.compareTo(city);
            }

        }
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof WeatherReading)) {
            return false;
        }
        WeatherReading weatherreading = (WeatherReading) other;
        return this.country.equals(weatherreading.country)
                && this.state.equals(weatherreading.state)
                && this.city.equals(weatherreading.city)
                && this.year == weatherreading.year
                && this.month == weatherreading.month
                && this.day == weatherreading.day
                && this.avgTemperature == weatherreading.avgTemperature;
    }

    @Override
    public String toString() {
        return "WeatherReading{" + "country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", month=" + month +
                ", day=" + day +
                ", year=" + year + '}' +
                ", avgTemperature=" + avgTemperature + '}';
    }
}
