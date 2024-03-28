import java.util.Arrays;
public record CityListStats(int startingIndex, int count, int[] years) {

    public int startingIndex(int startingIndex) {
        return startingIndex;
    }

    public int count(int count) {
        return count;
    }

    public int[] years(int[] years) {
        return years;
    }

    @Override
    public String toString() {
        return "CityListStats{startingIndex = " + startingIndex + ", count = " + count
                + ", years = " + Arrays.toString(years) + '}';
    }
}
