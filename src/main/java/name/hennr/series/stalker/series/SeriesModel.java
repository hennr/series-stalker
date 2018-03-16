package name.hennr.series.stalker.series;

import java.time.LocalDate;

public class SeriesModel implements Comparable<SeriesModel> {
    public String imageUrl;
    public LocalDate nextAirDate;

    public String getNextAirDate() {
        if (nextAirDate.isBefore(LocalDate.now())) {
            return "last episode: " + nextAirDate;
        } else {
            return "next episode: " + nextAirDate;
        }
    }

    @Override
    public int compareTo(SeriesModel seriesModel) {
        return seriesModel.nextAirDate.compareTo(nextAirDate);
    }
}
