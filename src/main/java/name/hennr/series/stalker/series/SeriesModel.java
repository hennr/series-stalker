package name.hennr.series.stalker.series;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SeriesModel implements Comparable<SeriesModel> {
    public String imageUrl;

    public SeriesModel() { }

    public SeriesModel(final String imageUrl, final LocalDate nextAirDate) {
        this.imageUrl = imageUrl;
        this.nextAirDate = nextAirDate;
    }

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

        if (seriesModel.nextAirDate.isBefore(LocalDate.now()) && this.nextAirDate.isBefore(LocalDate.now())) {
            return seriesModel.nextAirDate.compareTo(nextAirDate);
        } else if (seriesModel.nextAirDate.isAfter(LocalDate.now()) && this.nextAirDate.isAfter(LocalDate.now())) {
            return -1 * seriesModel.nextAirDate.compareTo(nextAirDate);
        } else {
            return seriesModel.nextAirDate.compareTo(this.nextAirDate);
        }
    }
}
