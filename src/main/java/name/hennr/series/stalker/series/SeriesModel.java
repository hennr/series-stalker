package name.hennr.series.stalker.series;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SeriesModel implements Comparable<SeriesModel> {
    public String imageUrl;
    public LocalDate nextAirDate;

    public String getNextAirDate() {
        if (nextAirDate.isBefore(LocalDate.now())) {
            return "last: " + nextAirDate.format(DateTimeFormatter.ofPattern("dd.MM.yy"));
        } else {
            return "next: " + nextAirDate.format(DateTimeFormatter.ofPattern("dd.MM.yy"));
        }
    }

    @Override
    public int compareTo(SeriesModel seriesModel) {
        return seriesModel.nextAirDate.compareTo(nextAirDate);
    }
}
