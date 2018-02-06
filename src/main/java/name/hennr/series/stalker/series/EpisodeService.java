package name.hennr.series.stalker.series;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class EpisodeService {

    @Autowired
    private RestTemplate restTemplate;

    public String getNextAirDateForTheWalkingDead() {
        Episode[] episodes = restTemplate.getForObject("http://api.tvmaze.com/shows/73/episodes", Episode[].class);

        String latestKnownEpisode = episodes[episodes.length - 1].airdate;

        LocalDate latestKnowAirDate = LocalDate.from(DateTimeFormatter.ISO_DATE.parse(latestKnownEpisode));

        if (LocalDate.now().isBefore(latestKnowAirDate)) {
            return "latest known episode available: " + latestKnowAirDate;
        } else {
            return "last episode aired on: " + latestKnowAirDate;
        }
    }
}
