package name.hennr.series.stalker.tvmaze;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Service
public class EpisodeClient {

    @Autowired
    private RestTemplate restTemplate;

    public LocalDate getNextAirDateForSeriesWithId(String id) {
        String url = "http://api.tvmaze.com/shows/" + id + "/episodes";
        List<Episode> episodes = Arrays.asList(restTemplate.getForObject(url, Episode[].class));

        LocalDate latestKnownEpisode = LocalDate.of(1666, 1, 1);

        for (Episode episode : episodes) {
            LocalDate airdate;
            try {
                airdate = LocalDate.from(DateTimeFormatter.ISO_DATE.parse(episode.airdate));
                if (airdate.isAfter(LocalDate.now())) {
                    return airdate;
                } else {
                    latestKnownEpisode = airdate;
                }
            } catch (Exception e) {
                break;
            }
        }

        return latestKnownEpisode;
    }
}
