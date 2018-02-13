package name.hennr.series.stalker.tvmaze;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class EpisodeClient {

    @Autowired
    private RestTemplate restTemplate;

    public String getNextAirDateForSeriesWithId(String id) {
        String url = "http://api.tvmaze.com/shows/" + id + "/episodes";
        Episode[] episodes = restTemplate.getForObject(url, Episode[].class);

        String latestKnownEpisode = episodes[episodes.length - 1].airdate;

        LocalDate latestKnowAirDate;
        try {
            latestKnowAirDate = LocalDate.from(DateTimeFormatter.ISO_DATE.parse(latestKnownEpisode));
        } catch (Exception e) {
            latestKnowAirDate = LocalDate.of(1666, 1, 1);
        }

        if (LocalDate.now().isBefore(latestKnowAirDate)) {
            return "latest known episode available: " + latestKnowAirDate;
        } else {
            return "last episode aired on: " + latestKnowAirDate;
        }
    }
}
