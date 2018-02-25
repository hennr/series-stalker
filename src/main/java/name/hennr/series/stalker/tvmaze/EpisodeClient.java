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

    public String getNextAirDateForSeriesWithId(String id) {
        String url = "http://api.tvmaze.com/shows/" + id + "/episodes";
        List<Episode> episodes = Arrays.asList(restTemplate.getForObject(url, Episode[].class));

        String latestKnownEpisode = "unknown";

        for (Episode episode : episodes) {
            LocalDate airdate;
            try {
                airdate = LocalDate.from(DateTimeFormatter.ISO_DATE.parse(episode.airdate));
                if (airdate.isAfter(LocalDate.now())) {
                    return "next episode: " + airdate.toString();
                } else {
                    latestKnownEpisode = airdate.toString();
                }
            } catch (Exception e) {
                break;
            }
        }

        return "last episode: " + latestKnownEpisode;
    }
}
